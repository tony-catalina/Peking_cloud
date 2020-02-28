package awd.cloud.internet.app.smsy.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;

@Component
public class ScheduleTask {
	
	@Value("${awd.ftp.ftphost}")
	private  String ftphost;
	
	@Autowired
	private AwdApi awdApi;
	  /**
	   * 定时将ftp数据更新到房间数量表
	   * @param request
	   */
	    //@Scheduled(cron = "0 0/5 * * * ?")
	    public void saveFjsl(){
	    	System.out.println("这是定时任务，执行 readFtpFileToEntity() 方法 ，目前时间: " + LocalDateTime.now());
	    	System.out.println("**********开始主动下载数据并更新*****************");
	    	String content = httpGetRequest("yysyfjs");
	    	List<Map<String, Object>> list = (List<Map<String, Object>>) parseStringToList(content);
	    	System.err.println("list:="+JSON.toJSONString(list));
	    	//循环获取到的list
	    	for (Map<String, Object> map : list) {
	    		Set<Entry<String, Object>> entries = map.entrySet();
	    		Iterator<Entry<String, Object>> it = entries.iterator();
	    		while(it.hasNext()) {
	    			Entry<String, Object> next = it.next();
	    			//判断数据库中是否有该监所的记录
	    			 ResponseMessage<String> re= awdApi.updateFjByJsbh(next.getValue().toString(), next.getKey().toString());
	    		}
			}
	    	System.out.println("**********监所房间数量已同步更新完毕*****************");
	    }
	    public  String httpGetRequest(String fileName) {
	    	String context = "";
	    	String targetURL = ftphost+"/public/public_getFtpFileByName?fileName="+fileName;
	    	try {
	    		URL restServiceURL = new URL(targetURL);
	            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
	            httpConnection.setRequestMethod("GET");
	            httpConnection.setRequestProperty("Accept", "application/json");
	            if (httpConnection.getResponseCode() != 200) {
	                   throw new RuntimeException("HTTP GET Request Failed with Error code : "
	                                 + httpConnection.getResponseCode());
	            }
	            StringBuffer strbuftmp = new StringBuffer("");
	            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
	                   (httpConnection.getInputStream())));
	            String output;
	            while ((output = responseBuffer.readLine()) != null) {
	            	strbuftmp.append(output);
	            }
	            context = strbuftmp.toString();
	            httpConnection.disconnect();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return context;
	    }
	    
	    public List<? extends Map> parseStringToList(String jsonString) {
	    	String listStr = jsonString.replace("[", "{").replace("]", "}")
	    			.replace("{{", "[{").replace("}}", "}]")
	    			.replace(",", ":").replace("}:{", "},{");
	    	Map<String, Object> map = Maps.newHashMap();
	    	List<? extends Map> jsonList = JSONArray.parseArray(listStr, map.getClass());
			return jsonList;
		}
	    
}