package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.PersonalInformationDTO;
import com.simple.blog.entity.PersonalInformation;
import com.simple.blog.repository.PersonalInformationRepository;
import com.simple.blog.service.PersonalInformationService;
import com.simple.blog.util.DateUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.PersonalInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by songning on 2019/8/25 2:50 PM
 */
@Service
public class PersonalInformationServiceImpl implements PersonalInformationService {

    @Autowired
    private PersonalInformationRepository personalInformationRepository;

    @Override
    public <T> CommonDTO<T> savePersonalInfo(CommonVO<List<PersonalInformationVO>> commonVO) {
        List<PersonalInformationVO> list = commonVO.getCondition();
        list.forEach(item -> {
            String infoOwner = item.getInfoOwner();
            String infoType = item.getInfoType();
            String mechanism = item.getMechanism();
            String position = item.getPosition();
            String introduction = item.getIntroduction();
            String startTime = item.getStartTime();
            String endTime = item.getEndTime();
            Timestamp startTimeStamp = DateUtil.strToSqlDate(startTime, CommonConstant.YEAR_DATETIME_PATTERN);
            Timestamp endTimeStamp = DateUtil.strToSqlDate(endTime, CommonConstant.YEAR_DATETIME_PATTERN);
            PersonalInformation personalInformation = PersonalInformation.builder().infoOwner(infoOwner).infoType(infoType).mechanism(mechanism).position(position).introduction(introduction).startTime(startTimeStamp).endTime(endTimeStamp).build();
            personalInformationRepository.save(personalInformation);
        });
        return new CommonDTO<>();
    }

    @Override
    public CommonDTO<PersonalInformationDTO> getPersonalInfo(CommonVO<PersonalInformationVO> commonVO) {
        CommonDTO<PersonalInformationDTO> commonDTO = new CommonDTO<>();
        String infoOwner = commonVO.getCondition().getInfoOwner();
        Map<String, Object> dataExt = new HashMap<>(10);
        List<String> types = personalInformationRepository.findInfoTypeByInfoOwnerNative(infoOwner);
        types.forEach(type -> {
            List<Map<String, Object>> personalInformations = personalInformationRepository.findByInfoOwnerAndInfoTypeNative(infoOwner, type);
            List<Map<String, Object>> result = new ArrayList<>();
            personalInformations.forEach(item -> {
                Map<String, Object> map = new HashMap<>(10);
                Timestamp startTime = (Timestamp) item.get("startTime");
                Timestamp endTime = (Timestamp) item.get("endTime");
                map.put("startTime", DateUtil.dateToStr(startTime, CommonConstant.YEAR_DATETIME_PATTERN));
                map.put("endTime", DateUtil.dateToStr(endTime, CommonConstant.YEAR_DATETIME_PATTERN));
                map.put("mechanism", item.get("mechanism"));
                map.put("position", item.get("position"));
                result.add(map);
            });
            dataExt.put(type, result);
        });
        commonDTO.setDataExt(dataExt);
        commonDTO.setTotal((long) types.size());
        return commonDTO;
    }
}
