<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

import ${basepackage}.model.Model;
import javax.validation.constraints.NotNull;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.format.annotation.DateTimeFormat;
<#include "/java_imports.include">

@JsonIgnoreProperties(ignoreUnknown = true)
public class ${className}Model implements Model{
	
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
<#if table.compositeId>
	private ${className}Id id;
	<#list table.columns as column>
		<#if !column.pk>
	private ${column.javaType} ${column.columnNameLower};
		</#if>
	</#list>
<#else>
	//columns START
	<#list table.columns as column>
	<#if column.isDateTimeColumn>
		<#if column.jdbcType == "DATE">
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date ${column.columnNameLower};
		
		<#else>
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ${column.columnNameLower};
		
		</#if>
	<#else>
	
	<#if column.pk>
	private ${column.javaType} ${column.columnNameLower};

	<#else>
	private ${column.javaType} ${column.columnNameLower};
	</#if>
	</#if>
	</#list>
	//columns END
	//开始生成字典的String属性
	<#list table.columns as column>
	<#if column.columnNameLower == "jsbh">
	private ${column.javaType} ${column.columnNameLower}String;
	
	</#if>
		<#assign zd_start = column.columnAlias?index_of("(",0)>
		<#assign zd_end = column.columnAlias?index_of(")",0)>
		<#if zd_start != -1 && zd_end != -1>
		
	private ${column.javaType} ${column.columnNameLower}String;
		</#if>
	</#list>
	//字典的get方法生成结束
</#if>

</#macro>

<#macro generateCompositeIdConstructorIfis>

	<#if table.compositeId>
	public ${className}Model(){
	}
	public ${className}Model(${className}Id id) {
		this.id = id;
	}
	<#else>
	public ${className}Model(){
	}
	public ${className}Model(String id) {
		this.id = id;
	}
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
	//开始生成字典的getString方法
	<#list table.columns as column>
		<#if column.columnNameLower == "jsbh">
	public String getJsbhString() {
		return this.${column.columnNameLower}String;
	}
		</#if>
		<#assign zd_start = column.columnAlias?index_of("(",0)>
		<#assign zd_end = column.columnAlias?index_of(")",0)>
		<#if zd_start != -1 && zd_end != -1>
		<#assign zd_code = column.columnAlias?substring(zd_start + 1,zd_end)?upper_case>
		
	public ${column.javaType} get${column.columnName}String() {
		return this.${column.columnNameLower}String;
	}
		</#if>
	</#list>
	//字典的get方法生成结束
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



