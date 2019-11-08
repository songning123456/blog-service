package com.simple.blog.webmagic.processor;

import com.simple.blog.webmagic.pipeline.ConsolePipelineNative;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author songning
 * @date 2019/11/8
 * description
 */
public class BokelaProcessor implements PageProcessor {

    private static final String URL_LIST = "https://www\\.boke\\.la/wenzhang/\\d+/";

    private static final String URL_POST = "https://www\\.boke\\.la/\\d+\\.html";

    private Site site = Site.me().setRetryTimes(3)
            .setSleepTime(1000).setTimeOut(10000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"news\"]").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
        } else {
            page.putField("title", page.getHtml().xpath("//div[@class=\"zwtit2\"]/h3"));
            page.putField("content", page.getHtml().xpath("//div[@class=\"zhengwen\"]/p"));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BokelaProcessor()).addUrl("https://www.boke.la/wenzhang/2/").addPipeline(new ConsolePipelineNative()).run();
    }
}
