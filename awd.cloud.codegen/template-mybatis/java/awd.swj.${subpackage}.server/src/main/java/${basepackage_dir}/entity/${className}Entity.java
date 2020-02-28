<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import awd.framework.common.entity.SimpleGenericEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;
<#include "/java_imports.include">

public class ${className}Entity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "${table.tableAlias}";
	<#list table.columns as column>
	public static final String ALIAS_${column.constantName} = "${column.columnAlias}";
	</#list>

	<@generateFields/>
	<@generateCompositeIdConstructorIfis/>
	<@generatePkProperties/>
	<@generateNotPkProperties/>
	<@generateJavaOneToMany/>
	<@generateJavaManyToOne/>
}

<#macro generateFields>
	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

<#if table.compositeId>
	@NotNull(message="ID不能为空" ,groups=UpdateGroup.class)
	@Length(max=${column.size} ,message="ID最大长度"+${column.size}+"位", groups=UpdateGroup.class)
	private ${className}Id id;
	<#list table.columns as column>
		<#if !column.pk>

		@NotNull(message="${column.columnAlias}不能为空")
	    @Length(max=${column.size},message="${column.columnAlias}最大长度21位")
	private ${column.javaType} ${column.columnNameLower};
		</#if>
	</#list>
<#else>
	//columns START
	<#list table.columns as column>
	<#if column.isDateTimeColumn>

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ${column.columnNameLower};

	<#else>
	<#if column.pk>

	@NotNull(message="${column.columnAlias}不能为空",groups=CreateGroup.class)
    @Length(max=${column.size},message="${column.columnAlias}最大长度${column.size}位" ,groups=CreateGroup.class)
	private ${column.javaType} ${column.columnNameLower};

	<#else>
	<#if !column.nullable>
	@NotNull(message="${column.columnAlias}不能为空",groups=CreateGroup.class)
	</#if>
    @Length(max=${column.size},message="${column.columnAlias}最大长度${column.size}位" ,groups=CreateGroup.class)
	private ${column.javaType} ${column.columnNameLower};

	</#if>
	</#if>
	</#list>
	//columns END
</#if>

</#macro>

<#macro generateCompositeIdConstructorIfis>

	<#if table.compositeId>
	public ${className}Entity(){
	}
	public ${className}Entity(${className}Id id) {
		this.id = id;
	}
	<#else>
	<@generateConstructor className/>
	</#if>

</#macro>

<#macro generatePkProperties>
	<#if table.compositeId>
	public ${className}Id getId() {
		return this.id;
	}

	public void setId(${className}Id id) {
		this.id = id;
	}
	<#else>
		<#list table.columns as column>
			<#if column.pk>

	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}

	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
			</#if>
		</#list>
	</#if>

</#macro>

<#macro generateNotPkProperties>
	<#list table.columns as column>
		<#if !column.pk>
			<#if column.isDateTimeColumn>
	public java.util.Date get${column.columnName}() {
			return this.${column.columnNameLower};
			}

	public void set${column.columnName}(java.util.Date value) {
			this.${column.columnNameLower} = value;
			}
			<#else>

	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}

	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
		</#if>
		</#if>
	</#list>
</#macro>


<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>

	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}

	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>

	private ${fkPojoClass} ${fkPojoClassVar};

	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}

	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>



