<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package awd.cloud.platform.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import awd.cloud.platform.api.hystrix.ManagerFallBackFactory;
import awd.cloud.platform.model.*;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;


@FeignClient(name = "${r"${awd.api:AWD-MIS-GATEWAY-SERVER}"}",fallbackFactory=ManagerFallBackFactory.class)
public interface ManagerService {
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/manager/${classNameLower}")
	ResponseMessage<PagerResult<${className}Model>> ${classNameLower}_query(QueryParam queryParam);
	
	@PostMapping("/manager/${classNameLower}")
	ResponseMessage<String> ${classNameLower}_save(@RequestBody ${className}Model data);

	@PatchMapping("/manager/${classNameLower}/{id}")
	ResponseMessage<String> ${classNameLower}_updateByKey(@PathVariable(value = "id") String id, @RequestBody ${className}Model data);

	@DeleteMapping("/manager/${classNameLower}/{id}")
	ResponseMessage<Integer> ${classNameLower}_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/manager/${classNameLower}/{id}")
	ResponseMessage<${className}Model> ${classNameLower}_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
}


