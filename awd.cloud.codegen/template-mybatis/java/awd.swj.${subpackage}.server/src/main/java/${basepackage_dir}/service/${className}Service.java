<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

import awd.framework.common.service.api.CrudService;
import ${basepackage}.entity.${className}Entity;

public interface ${className}Service extends CrudService<${className}Entity, String> {

}
