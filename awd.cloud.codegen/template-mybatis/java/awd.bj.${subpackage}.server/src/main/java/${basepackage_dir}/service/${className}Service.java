<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.api.CrudService;

import java.util.Map;

import ${basepackage}.entity.${className}Entity;

public interface ${className}Service extends CrudService<${className}Entity, String> {
	/**
	 * 循环查找表字段是否含有rybh，有的话就自动生成jbxxList 方法，没有就不生成
	 */
	<#list table.columns as column>
	<#if column.columnNameLower == "rybh">
	ResponseMessage<PagerResult<Map<String, Object>>> jbxxlist(Entity param);
	</#if>
	</#list>
}
