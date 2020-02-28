<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package awd.cloud.platform.api.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.model.DictionaryModel;
import awd.cloud.platform.model.logs.AnandlbModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;

@Service("managerService")
public class KssFallBackFactory implements FallbackFactory<KssService> {
	public static final Logger logger = LoggerFactory.getLogger(KssService.class);
	
	@Override
	public KssService create(Throwable cause) {
		if(cause.getMessage()!=null) {
			cause.printStackTrace();
			logger.info("熔断错误的具体信息: {} " ,cause.getMessage());
		}
		return new ManagerService() {
			
			@Override
			public ResponseMessage<String> ${classNameLower}_save(${className}Model data) {
				logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "${classNameLower}_save");
				return ResponseMessage.error(400, "服务异常,保存失败");
			}

			@Override
			public ResponseMessage<String> ${classNameLower}_updateByKey(String id, ${className}Model data) {
				logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "${classNameLower}_updateByKey");
				return ResponseMessage.error(400, "服务异常,更新失败");
			}

			@Override
			public ResponseMessage<Integer> ${classNameLower}_deleteByKey(String id) {
				logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "${classNameLower}_deleteByKey");
				return ResponseMessage.error(400, "服务异常,删除失败");
			}

			@Override
			public ResponseMessage<${className}Model> ${classNameLower}_getByKey(String id) {
				logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "${classNameLower}_getByKey");
				return ResponseMessage.error(400, "服务异常,获取失败");
			}

			@Override
			public ResponseMessage<PagerResult<${className}Model>> ${classNameLower}_query(QueryParam queryParam) {
				logger.info("熔断错误的具体信息: {}     方法名称：{} ", cause.getMessage(), "${classNameLower}_query");
				return ResponseMessage.error(400, "服务异常,查询失败");
			}
			
		};
	}

}
