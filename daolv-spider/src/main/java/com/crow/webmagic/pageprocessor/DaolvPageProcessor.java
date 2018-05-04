package com.crow.webmagic.pageprocessor;

import java.util.Random;
import com.crow.webmagic.pipeline.DaolvSpiderPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

public class DaolvPageProcessor implements PageProcessor{

	 private static String BASEURI = "http://api.didatravel.com/";
	 
	 //抓取网站的相关配置，包括编码、抓取间隔、重试次数、代理、UserAgent等
     private Site site = Site.me()
    		 //添加头部信息与否都可以
    		 //.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0")
    		.setDisableCookieManagement(true)
            .setCharset("UTF-8")
            .setTimeOut(30000)
            .setRetryTimes(3)
            .setSleepTime(new Random().nextInt(20)*100);
    
	@Override
	public void process(Page page) {
		page.putField("jsons",page.getJson().toString());
	}

	@Override
	public Site getSite() {
		return site;
	}
	
	public static void main(String[] args) {
		Request request = new Request();
		request.setMethod(HttpConstant.Method.POST);
		request.setUrl(BASEURI+"api/staticdata/GetCountryList?$format=json");
		String jparam ="{\"Header\":{\"ClientID\":\"DidaApiTestID\",\"LicenseKey\":\"TestKey\"}}";
		request.setRequestBody(HttpRequestBody.json(jparam,"utf-8"));
		
		Spider.create(new DaolvPageProcessor())
		.addPipeline(new DaolvSpiderPipeline())
		.addRequest(request)
        .run();
	}
}