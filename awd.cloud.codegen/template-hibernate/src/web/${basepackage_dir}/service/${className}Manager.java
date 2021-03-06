<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

<#include "/java_imports.include">
@Component
public class ${className}Manager extends BaseManager<${className},${table.idColumn.javaType}>{

	private ${className}Dao ${classNameLower}Dao;
	/**通过spring注入${className}Dao*/
	public void set${className}Dao(${className}Dao dao) {
		this.${classNameLower}Dao = dao;
	}
	public EntityDao getEntityDao() {
		return this.${classNameLower}Dao;
	}
	public Page findByPageRequest(PageRequest pr) {
		return ${classNameLower}Dao.findByPageRequest(pr);
	}
	
	/**
	 * 更新state 
	 * @param id
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(String id) {
		${classNameLower}Dao.updateState("${classNameLower}", id);
	}
	
<#list table.columns as column>
	<#if column.unique && !column.pk>
	public ${className} getBy${column.columnName}(${column.javaType} v) {
		return ${classNameLower}Dao.getBy${column.columnName}(v);
	}	
	</#if>
</#list>
}
