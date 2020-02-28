<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package ${basepackage}.action;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.awd.common.util.ListRange;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import cn.org.rapid_framework.generator.provider.java.model.JavaClass;
import cn.org.rapid_framework.generator.provider.java.model.JavaField;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.awd.kss.action.WpjsAction;
import com.awd.kss.model.${className};
import com.awd.kss.model.Users;
import com.awd.kss.service.LogManager;
import com.ga.isl.util.IslLog;
import com.ga.isl.util.IslLoggerFactory;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

<#include "/java_imports.include">

public class ${className}Action extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	private static final Logger logger = Logger.getLogger(${className}Action.class);
	
	private ${className}Manager ${classNameLower}Manager;
	private LogManager logManager; 
	
	private ${className} ${classNameLower};
	<#list table.compositeIdColumns as column>
	${column.javaType} id = null;
	</#list>
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			${classNameLower} = new ${className}();
		} else {
			${classNameLower} = (${className})${classNameLower}Manager.getById(id);
		}
	}
	
	/** 通过spring自动注入 */
	public void set${className}Manager(${className}Manager manager) {
		this.${classNameLower}Manager = manager;
	}	
	public void setLogManager(LogManager logManager){
		this.logManager = logManager;
	}
	public Object getModel() {
		return ${classNameLower};
	}
	
	<#list table.compositeIdColumns as column>
	public void set${column.columnName}(${column.javaType} val) {
		this.id = val;
	}
	</#list>	

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/**
	 * ExtGrid使用
	 * 列表
	 * @throws IOException
	 */
	public void extlist() throws IOException{
		int DEFAULT_PAGE_SIZE = 10;
		String strStart = getRequest().getParameter("start");
		String strLimit = getRequest().getParameter("limit");
		String field_type = getRequest().getParameter("field_type");
		String query = getRequest().getParameter("query"); //SearchField.js 里设置此参数名
		String sort = getRequest().getParameter("sort");// 指定排序的列
		String dir = getRequest().getParameter("dir");// 顺序倒序
		String orderBy = "id desc"; //默认正向排序列 
		if (sort != null && dir != null){
			if(sort.endsWith("ZH")){
				sort = sort.substring(0,sort.lastIndexOf("ZH"));
			}else if(sort.endsWith("String")){
				sort = sort.substring(0,sort.lastIndexOf("String"));
			}
			orderBy = sort + " " + dir;
		}
		PageRequest result = new PageRequest();
		try{
			//如果没有按照指定字段搜索,则按全条件查询
			if(field_type==null){
				result = newPageRequest(DEFAULT_SORT_COLUMNS);
			}else{
				Map map = new HashMap();
				map.put(field_type, query);
				result.setFilters(map);
			}
			int start = 0;
			int limit = DEFAULT_PAGE_SIZE;
			if (StringUtils.isNotBlank(strStart))
				start = Integer.valueOf(strStart);
			if (StringUtils.isNotBlank(strLimit))
				limit = Integer.valueOf(strLimit);
				
			Map<String,String> filters =result.getFilters();
			Users users=(Users)getRequest().getSession().getAttribute("currentUser");
			filters.put("jsbh", users.getJsbh());
			result.setFilters(filters);			
			
			result.setPageNumber(start / limit + 1);
			result.setPageSize(limit);
			result.setSortColumns(orderBy);
			Page page = ${classNameLower}Manager.findByPageRequest(result);
			
			List<${className}> ${className}list = (List) page.getResult();
			ListRange<${className}> resultList = new ListRange<${className}>();
			resultList.setList(${className}list);
			resultList.setTotalSize(page.getTotalCount());
			resultList.setMessage("ok");
			resultList.setSuccess(true);
			outJson(resultList);
		}catch (Exception e){
			logger.error(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * extGrid保存
	 * @throws IOException
	 */
	public void extsave() throws IOException{
		
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			Users  users = (Users)getRequest().getSession().getAttribute("currentUser");
			${classNameLower}.setOperator(users.getLoginname());
			${classNameLower}.setCreatetime(Calendar.getInstance().getTime());
			${classNameLower}.setScbz("0");
			${classNameLower}.setState("R2");
			${classNameLower}.setJsbh(users.getJsbh());
			${classNameLower}Manager.save(${classNameLower});
			
//			记日志
			logManager.log(users.getJsbh(), users.getLoginname(), logManager.LOGTYPE_OPERATE_ADD, ${classNameLower}.TABLE_ALIAS, ${classNameLower}.getOperator(), "oldvalue", "newvalue", getRequest().getRemoteAddr());

			result.put("success", true);
			result.put("msg", "添 加 成 功!");
		}catch (Exception e){
			result.put("failure", true);
			result.put("msg", e.getMessage());
			logger.error(e.toString());
			e.printStackTrace();
		}
		outJson(result);
	}
	
	/**
	 * extGrid修改
	 * @throws IOException
	 */
	public void extupdate() throws IOException{
		
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			Users  users = (Users)getRequest().getSession().getAttribute("currentUser");
			${classNameLower}.setOperator(users.getLoginname());
			${classNameLower}.setCreatetime(Calendar.getInstance().getTime());
			${classNameLower}.setScbz("0");
			${classNameLower}.setJsbh(users.getJsbh());
			${classNameLower}.setState("R2");
			
			${classNameLower}Manager.update(${classNameLower});
			
//			记日志
			logManager.log(users.getJsbh(), users.getLoginname(), logManager.LOGTYPE_OPERATE_EDIT, ${classNameLower}.TABLE_ALIAS, ${classNameLower}.getOperator(), "oldvalue", "newvalue", getRequest().getRemoteAddr());
			
			result.put("success", true);
			result.put("msg", "修 改 成 功!");
		}catch (RuntimeException e){
			result.put("failure", true);
			result.put("msg", e.getMessage());
			logger.error(e.toString());
			e.printStackTrace();
		}
		outJson(result);
	}
	
	/**
	 * extGrid删除
	 * @throws IOException
	 */
	public void extdelete() throws IOException{
		String ids = getRequest().getParameter("ids");
		String[] idarray = ids.split(",");
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			Users  users = (Users)getRequest().getSession().getAttribute("currentUser");
			for (int i = 0; i < idarray.length; i++){
				<#list table.compositeIdColumns as column>
				${column.javaType} id = new ${column.javaType}((String)idarray[i]);
					</#list>
			//	${classNameLower}Manager.removeById(id);
					${classNameLower}Manager.delete(id);
//					记日志
					logManager.log(users.getJsbh(), users.getLoginname(), logManager.LOGTYPE_OPERATE_DELETE, ${classNameLower}.TABLE_ALIAS, ${classNameLower}.getOperator(), "oldvalue", "newvalue", getRequest().getRemoteAddr());
				
			}
			result.put("success", true);
			result.put("msg", "删 除 成 功");
		}catch (RuntimeException e){
			result.put("failure", true);
			result.put("msg", e.getMessage());
			logger.error(e.toString());
			e.printStackTrace();
		}
		outJson(result);
	}
	
}
