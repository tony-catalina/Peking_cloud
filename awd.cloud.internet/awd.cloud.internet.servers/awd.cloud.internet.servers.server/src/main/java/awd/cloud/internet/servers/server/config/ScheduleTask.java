package awd.cloud.internet.servers.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {
	
	@Autowired
	private CacheConfig cacheConfig;
	
	/**
	 * 	定时将组织信息同步到缓存中
	 */
	//@Scheduled(cron = "0 0 2 1/5 * ? *")
	//@Scheduled(cron = "0 0 1 1/1 * ? ")
	@Scheduled(cron = "0 0 1 * * ? ")	//每天凌晨 1 点执行定时任务
	public void syncOrgToCache() {
		cacheConfig.setOrgCache();
	}
	    
}