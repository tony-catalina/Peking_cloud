/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import awd.framework.common.entity.SimpleGenericEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class LogsEntity extends SimpleGenericEntity<String>  {

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

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

	//columns START
	@NotNull(message="应用系统ID不能为空",groups=CreateGroup.class)
    @Length(max=20,message="应用系统ID最大长度20位" ,groups=CreateGroup.class)
	private String productId;

	@NotNull(message="应用系统中文名称不能为空",groups=CreateGroup.class)
    @Length(max=30,message="应用系统中文名称最大长度30位" ,groups=CreateGroup.class)
	private String productName;


    @Length(max=64,message="日志ID最大长度64位" ,groups=CreateGroup.class)
	private String logId;

    @Length(max=30,message="操作人在应用中ID最大长度30位" ,groups=CreateGroup.class)
	private String operatorId;

	@NotNull(message="操作人身份证号不能为空",groups=CreateGroup.class)
    @Length(max=18,message="操作人身份证号最大长度18位" ,groups=CreateGroup.class)
	private String operatorAccount;

	@NotNull(message="操作人身份 0管理员 1普通用户不能为空",groups=CreateGroup.class)
    @Max(value=9,message="操作人身份 0管理员 1普通用户数值最大为9" ,groups=CreateGroup.class)
	private Integer operatorIdentity;

	@NotNull(message="操作人所在单位名称不能为空",groups=CreateGroup.class)
    @Length(max=20,message="操作人所在单位名称最大长度20位" ,groups=CreateGroup.class)
	private String operatorName;

	@NotNull(message="单位名称不能为空",groups=CreateGroup.class)
    @Length(max=50,message="单位名称最大长度50位" ,groups=CreateGroup.class)
	private String organizationName;

	@NotNull(message="操作人所属公安机构代码不能为空",groups=CreateGroup.class)
    @Length(max=50,message="操作人所属公安机构代码最大长度50位" ,groups=CreateGroup.class)
	private String organizationId;

	@NotNull(message="操作时间yyyyMMddHHmmssSSSS不能为空",groups=CreateGroup.class)
    @Length(max=22,message="操作时间yyyyMMddHHmmssSSSS最大长度22位" ,groups=CreateGroup.class)
	private String opTime;

	@NotNull(message="响应时间yyyyMMddHHmmssSSSS不能为空",groups=CreateGroup.class)
    @Length(max=22,message="响应时间yyyyMMddHHmmssSSSS最大长度22位" ,groups=CreateGroup.class)
	private String resTime;

    @Length(max=1,message="0非移动端 1移动端最大长度1位" ,groups=CreateGroup.class)
	private String terminalType;

    @Length(max=32,message="终端标识最大长度32位" ,groups=CreateGroup.class)
	private String terminalId;

    @Length(max=18,message="终端移动号码最大长度18位" ,groups=CreateGroup.class)
	private String terminalNum;

    @Length(max=17,message="移动终端MAC最大长度17位" ,groups=CreateGroup.class)
	private String terminalMac;

	@NotNull(message="操作行为类型 0登录 1查询 2新增 3修改 4删除 5退出不能为空",groups=CreateGroup.class)
    @Max(value=9,message="操作行为类型 0登录 1查询 2新增 3修改 4删除 5退出最大值为9" ,groups=CreateGroup.class)
	private Integer opType;

	@NotNull(message="操作条件不能为空",groups=CreateGroup.class)
    @Length(max=2048,message="操作条件最大长度2048位" ,groups=CreateGroup.class)
	private String operateCondition;

	@NotNull(message="操作结果 0失败 1成功不能为空",groups=CreateGroup.class)
    @Max(value=9,message="操作结果 0失败 1成功最大值为9" ,groups=CreateGroup.class)
	private Integer operateResult;

    @Length(max=4,message="失败原因代码最大长度4位" ,groups=CreateGroup.class)
	private String errCode;

	@NotNull(message="客户端IP不能为空",groups=CreateGroup.class)
    @Length(max=23,message="客户端IP最大长度23位" ,groups=CreateGroup.class)
	private String clientIp;

	@NotNull(message="客户端Port不能为空",groups=CreateGroup.class)
    @Length(max=23,message="客户端Port最大长度23位" ,groups=CreateGroup.class)
	private String clientPort;

	@NotNull(message="目标URL不能为空",groups=CreateGroup.class)
    @Length(max=2048,message="目标URL最大长度2048位" ,groups=CreateGroup.class)
	private String url;

    @Length(max=2048,message="目标URL参数最大长度2048位" ,groups=CreateGroup.class)
	private String objectParam;

	@NotNull(message="会话ID不能为空",groups=CreateGroup.class)
    @Length(max=100,message="会话ID最大长度100位" ,groups=CreateGroup.class)
	private String sessionId;

	@NotNull(message="功能模块名称不能为空",groups=CreateGroup.class)
    @Length(max=100,message="功能模块名称最大长度100位" ,groups=CreateGroup.class)
	private String funcModuleName;

	@NotNull(message="目标IP不能为空",groups=CreateGroup.class)
    @Length(max=23,message="目标IP最大长度23位" ,groups=CreateGroup.class)
	private String objectIp;

	@NotNull(message="目标Port不能为空",groups=CreateGroup.class)
    @Length(max=23,message="目标Port最大长度23位" ,groups=CreateGroup.class)
	private String objectPort;

	@NotNull(message="查询语句不能为空",groups=CreateGroup.class)
    @Length(max=2048,message="查询语句最大长度2048位" ,groups=CreateGroup.class)
	private String querySql;

    @Length(max=64,message="ID最大长度64位" ,groups=CreateGroup.class)
	private String id;

	//columns END


	public LogsEntity(){
	}

	public LogsEntity(
		String Id
	){
		this.id = Id;
		this.logId = Id;
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
		this.logId = value;
	}
}

