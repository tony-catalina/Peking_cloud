<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.dao;

import java.util.List;
import java.util.Map;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import ${basepackage}.entity.${className}Entity;

public interface ${className}Dao extends CrudDao<${className}Entity, String> {
	
	List<${className}Entity> query(Entity queryEntity);

    int count(Entity queryEntity);
    
	/**
	 * 循环查找表字段是否含有rybh，有的话就自动生成jbxxList 方法，没有就不生成
	 */
	<#list table.columns as column>
	<#if column.columnNameLower == "rybh">
	
    List<Map<String, Object>> jbxxlist(Entity queryEntity);
    
    int jbxxcount(Entity queryEntity);
	</#if>
	</#list>

}
