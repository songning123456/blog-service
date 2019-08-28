package com.simple.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.StatisticDTO;
import com.simple.blog.service.StatisticService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.StatisticVO;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import io.searchbox.core.search.sort.Sort;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author songning
 * @date 2019/8/27
 * description
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private JestClient jestClient;

    @Override
    public CommonDTO<StatisticDTO> getStatisticResult(CommonVO<StatisticVO> commonVO) {
        CommonDTO<StatisticDTO> commonDTO = new CommonDTO<>();
        String type = commonVO.getCondition().getType();
        String startTime = commonVO.getCondition().getStartTime();
        String endTime = commonVO.getCondition().getEndTime();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        AggregationBuilder termsBuilder = AggregationBuilders.terms("by_" + type).field(type + ".keyword");
        List<QueryBuilder> queryBuilderList = new ArrayList<>();
        queryBuilderList.add(QueryBuilders.rangeQuery("updateTime").from(startTime).to(endTime));
        boolQueryBuilder.must().addAll(queryBuilderList);
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.size(0);
        searchSourceBuilder.aggregation(termsBuilder);
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(CommonConstant.INDEX_NAME).addType(CommonConstant.TYEP_NAME).build();
        JestResult jestResult = null;
        try {
            jestResult = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<StatisticDTO> statisticDTOList = JSON.parseObject(JSON.toJSONString(jestResult.getValue("aggregations"))).getJSONObject("by_" + type).getJSONArray("buckets").stream().map(item -> {
            StatisticDTO statisticDTO = new StatisticDTO();
            JSONObject agg = JSON.parseObject(item.toString());
            statisticDTO.setXAxis(agg.getString("key"));
            statisticDTO.setYAxis(String.valueOf(agg.getBigDecimal("doc_count").longValue()));
            return statisticDTO;
        }).collect(Collectors.toList());
        commonDTO.setData(statisticDTOList);
        return commonDTO;
    }
}
