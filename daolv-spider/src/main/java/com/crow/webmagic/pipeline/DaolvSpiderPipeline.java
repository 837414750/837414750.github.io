package com.crow.webmagic.pipeline;

import java.util.Map;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class DaolvSpiderPipeline implements Pipeline {

	 @Override
	 public void process(ResultItems resultItems, Task task) {
        for(Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
        	System.out.println("测试Start");
        	if(entry.getKey().equals("jsons")) {
        		System.out.println(entry.getValue());
        	}
        }
	 }
}