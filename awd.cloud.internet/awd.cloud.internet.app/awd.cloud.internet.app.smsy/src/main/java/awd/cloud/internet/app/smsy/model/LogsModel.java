/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.app.smsy.model;

import awd.cloud.internet.app.smsy.utils.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class LogsModel implements Model {
	
	//alias
	public static final String TABLE_ALIAS = "Logs";
	public static final String ALIAS_PRODUCT_ID = "应用系统ID";
	public static final String ALIAS_PRODUCT_NAME = "应用系统中文名称";
	public static final String ALIAS_LOG_ID = "日志ID";
	public static final String ALIAS_OPERATOR_ID = "操作人在应用中ID";
	public static final String ALIAS_OPERATOR_ACCOUNT = "操作人身份证号";
	public static final String ALIAS_OPERATOR_IDENTITY = "操作人身份 0管理员 1普通用户";
	public static final String ALIAS_OPERATOR_NAME = "操作人所在单位名称";
	public static final String ALIAS_ORGANIZATION_NAME = "单位名称";
	public static final String ALIAS_ORGANIZATION_ID = "操作人所属公安机构代码";
	public static final String ALIAS_OP_TIME = "操作时间yyyyMMddHHmmssSSSS";
	public static final String ALIAS_RES_TIME = "响应时间yyyyMMddHHmmssSSSS";
	public static final String ALIAS_TERMINAL_TYPE = "0非移动端 1移动端";
	public static final String ALIAS_TERMINAL_ID = "终端标识";
	public static final String ALIAS_TERMINAL_NUM = "终端移动号码";
	public static final String ALIAS_TERMINAL_MAC = "移动终端MAC";
	public static final String ALIAS_OP_TYPE = "操作行为类型 0登录 1查询 2新增 3修改 4删除 5退出";
	public static final String ALIAS_OPERATE_CONDITION = "操作条件";
	public static final String ALIAS_OPERATE_RESULT = "操作结果 0失败 1成功";
	public static final String ALIAS_ERR_CODE = "失败原因代码";
	public static final String ALIAS_CLIENT_IP = "客户端IP";
	public static final String ALIAS_CLIENT_PORT = "客户端Port";
	public static final String ALIAS_URL = "目标URL";
	public static final String ALIAS_OBJECT_PARAM = "目标URL参数";
	public static final String ALIAS_SESSION_ID = "会话ID";
	public static final String ALIAS_FUNC_MODULE_NAME = "功能模块名称";
	public static final String ALIAS_OBJECT_IP = "目标IP";
	public static final String ALIAS_OBJECT_PORT = "目标Port";
	public static final String ALIAS_QUERY_SQL = "查询语句";
	public static final String ALIAS_ID = "ID";
	
	//columns START
	
	private String productId;
	
	private String productName;
	
	private String logId;

	
	private String operatorId;
	
	private String operatorAccount;
	
	private Integer operatorIdentity;
	
	private String operatorName;
	
	private String organizationName;
	
	private String organizationId;
	
	private String opTime;
	
	private String resTime;
	
	private String terminalType;
	
	private String terminalId;
	
	private String terminalNum;
	
	private String terminalMac;
	
	private Integer opType;
	
	private String operateCondition;
	
	private Integer operateResult;
	
	private String errCode;
	
	private String clientIp;
	
	private String clientPort;
	
	private String url;
	
	private String objectParam;
	
	private String sessionId;
	
	private String funcModuleName;
	
	private String objectIp;
	
	private String objectPort;
	
	private String querySql;
	
	private String id;
	//columns END

	 

	public LogsModel(){
	}
	public LogsModel(String id) {
		this.id = id;
	}
	

	public void setLogId(String value) {
		this.logId = value;
	}
	
	public String getLogId() {
		return this.logId;
	}
	
	public String getProductId() {
		return this.productId;
	}
	
	public void setProductId(String value) {
		this.productId = value;
	}
	public String getProductName() {
		return this.productName;
	}
	
	public void setProductName(String value) {
		this.productName = value;
	}
	public String getOperatorId() {
		return this.operatorId;
	}
	
	public void setOperatorId(String value) {
		this.operatorId = value;
	}
	public String getOperatorAccount() {
		return this.operatorAccount;
	}
	
	public void setOperatorAccount(String value) {
		this.operatorAccount = value;
	}
	public Integer getOperatorIdentity() {
		return this.operatorIdentity;
	}
	
	public void setOperatorIdentity(Integer value) {
		this.operatorIdentity = value;
	}
	public String getOperatorName() {
		return this.operatorName;
	}
	
	public void setOperatorName(String value) {
		this.operatorName = value;
	}
	public String getOrganizationName() {
		return this.organizationName;
	}
	
	public void setOrganizationName(String value) {
		this.organizationName = value;
	}
	public String getOrganizationId() {
		return this.organizationId;
	}
	
	public void setOrganizationId(String value) {
		this.organizationId = value;
	}
	public String getOpTime() {
		return this.opTime;
	}
	
	public void setOpTime(String value) {
		this.opTime = value;
	}
	public String getResTime() {
		return this.resTime;
	}
	
	public void setResTime(String value) {
		this.resTime = value;
	}
	public String getTerminalType() {
		return this.terminalType;
	}
	
	public void setTerminalType(String value) {
		this.terminalType = value;
	}
	public String getTerminalId() {
		return this.terminalId;
	}
	
	public void setTerminalId(String value) {
		this.terminalId = value;
	}
	public String getTerminalNum() {
		return this.terminalNum;
	}
	
	public void setTerminalNum(String value) {
		this.terminalNum = value;
	}
	public String getTerminalMac() {
		return this.terminalMac;
	}
	
	public void setTerminalMac(String value) {
		this.terminalMac = value;
	}
	public Integer getOpType() {
		return this.opType;
	}
	
	public void setOpType(Integer value) {
		this.opType = value;
	}
	public String getOperateCondition() {
		return this.operateCondition;
	}
	
	public void setOperateCondition(String value) {
		this.operateCondition = value;
	}
	public Integer getOperateResult() {
		return this.operateResult;
	}
	
	public void setOperateResult(Integer value) {
		this.operateResult = value;
	}
	public String getErrCode() {
		return this.errCode;
	}
	
	public void setErrCode(String value) {
		this.errCode = value;
	}
	public String getClientIp() {
		return this.clientIp;
	}
	
	public void setClientIp(String value) {
		this.clientIp = value;
	}
	public String getClientPort() {
		return this.clientPort;
	}
	
	public void setClientPort(String value) {
		this.clientPort = value;
	}
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String value) {
		this.url = value;
	}
	public String getObjectParam() {
		return this.objectParam;
	}
	
	public void setObjectParam(String value) {
		this.objectParam = value;
	}
	public String getSessionId() {
		return this.sessionId;
	}
	
	public void setSessionId(String value) {
		this.sessionId = value;
	}
	public String getFuncModuleName() {
		return this.funcModuleName;
	}
	
	public void setFuncModuleName(String value) {
		this.funcModuleName = value;
	}
	public String getObjectIp() {
		return this.objectIp;
	}
	
	public void setObjectIp(String value) {
		this.objectIp = value;
	}
	public String getObjectPort() {
		return this.objectPort;
	}
	
	public void setObjectPort(String value) {
		this.objectPort = value;
	}
	public String getQuerySql() {
		return this.querySql;
	}
	
	public void setQuerySql(String value) {
		this.querySql = value;
	}
	public String getId() {
		return this.id;
	}
	
	public void setId(String value) {
		this.id = value;
	}
	 
}

