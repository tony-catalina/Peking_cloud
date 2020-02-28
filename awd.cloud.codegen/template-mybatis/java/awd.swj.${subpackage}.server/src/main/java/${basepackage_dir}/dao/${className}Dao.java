<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.dao;

import java.util.List;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import ${basepackage}.entity.${className}Entity;

public interface ${className}Dao extends CrudDao<${className}Entity, String> {
	
	List<${className}Entity> query(Entity queryEntity);

    int count(Entity queryEntity);

}
