package awd.cloud.internet.app.smsy.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.model.LogsModel;
import awd.cloud.internet.app.smsy.model.UserinfoModel;

/**
 * 综合岗位AOP切面
 *
 * @author ws
 */
@Aspect
@Component
public class AopConfig {

    private final static Logger logger = LoggerFactory.getLogger(AopConfig.class);

    @Pointcut("execution(public * awd.cloud.internet.app.smsy.controller.*.*(..))")
    public void log() {
    }

    @Value("${spring.cloud.client.ipAddress}")
    private String ip;

    @Value("${server.port}")
    private String port;

    @Value("${awd.smsy.logName}")
    private String fileName;

    @Value("${awd.smsy.logPath}")
    private String filePath;

    @Autowired
    private AwdApi awdApi;

    private LogsModel logsModel;

    private String logContent = "";

    private String startTime;

    private String methodName;
    
    private String operateType;

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        logsModel = new LogsModel();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
            Date date = new Date();
            startTime = sdf.format(date);
            //logsModel.setOpTime(startTime);
            methodName = joinPoint.getSignature().getName();
            //logsModel.setFuncModuleName(methodName);
            if (methodName.contains("login")) {
            	operateType = "0";
            }else if (methodName.contains("get")) {
            	operateType = "1";
			}else if (methodName.contains("add")) {
				operateType = "2";
			}else if (methodName.contains("update") || methodName.contains("change")) {
				operateType = "3";
			}else if (methodName.contains("delete")) {
				operateType = "4";
			}else {
				operateType = "5";
			}
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After("log()")
    public void doAfter() {
        //logger.info("业务完成后执行");
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();            
            HttpSession session = request.getSession();
            UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
            if (userinfo != null) {
                
                String productID = "";				//应用ID	是
                String productName = "";			//应用名称	是
                String logID = "";					//日志ID	是
                String operatorID = "";				//操作人ID	否
                String operatorAccount = "";		//操作人账号	是
                String operatorIdentity = "";		//操作人身份	是	0.管理员；1.普通用户
                String operatorName = "";			//操作人名	是
                String organizationName = "";		//单位名称	是
                String organizationID = "";			//单位机构代码	是
                String opTime = "";					//操作时间	是
                String resTime = "";				//响应时间	是
                String terminalType = "";			//终端类型	是	0.非移动终端；1.移动终端
                String terminalID = "";				//终端标识	是
                String terminalNum = "";			//移动终端号码	否
                String terminalMac = "";			//移动终端MAC	否
                String opType = "";					//操作行为类型	是	0：登录；1：查询；2：新增；3：修改；4：删除；5：退出
                String operateCondition = "";		//操作条件	是
                String operateResult = "";			//操作结果	是	0：失败；1：成功
                String errCode = "";				//失败原因代码	否
                String clientIp = "";				//客户端IP	是
                String clientPort = "";				//客户端Port	是
                String url = "";					//目标URL	是
                String objectParams = "";			//目标URL参数	否
                String sessionID = "";				//会话ID	是
                String funcModuleName = "";			//功能模块名称	是
                String objectIP = "";				//目标IP	是
                String objectPort = "";				//目标Port	是
                String querySql = "";				//查询语句
                
                
                productID = "1000171";
                productName = "监管-e约";
                logID = getFixLenthString(64);
                operatorID = userinfo.getId();
                operatorAccount = userinfo.getUserid();
                operatorIdentity = "1";
                operatorName = userinfo.getUsername();
                if ("".equals(userinfo.getDwbhString())) {
                	organizationName = userinfo.getUnit();
                }else {
                	organizationName = userinfo.getDwbhString();
                }
                organizationID = userinfo.getDwbh();
                opTime = startTime;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
                Date date = new Date();
                String endTime = sdf.format(date);
                resTime = endTime;
                terminalType = "1";
                terminalID = "";	//通过政务微信操作，置空
                terminalNum = "";	//通过政务微信操作，置空
                terminalMac = "";	//通过政务微信操作，置空
                opType = operateType;	
                operateCondition = "";	//todo,置空
                operateResult = "操作结果";
                errCode = "";	//非必填,todo
                clientIp = ip;
                clientPort = port;
                url = request.getRequestURL().toString();
                objectParams = cn.hutool.json.JSONUtil.toJsonStr(request.getParameterMap());
                sessionID = session.getId();
                funcModuleName = methodName;
                objectIP = SystemUtils.getIpAddr(request);
                objectPort = "11101";
                querySql = "select * from table";	//todo
                
                logContent = productID +"|"+ 			//应用ID
	                         productName +"|"+			//应用名称	是
	                         logID +"|"+				//日志ID	是
	                         operatorID +"|"+			//操作人ID	否
	                         operatorAccount +"|"+		//操作人账号	是
	                         operatorIdentity +"|"+		//操作人身份	是	0.管理员；1.普通用户
	                         operatorName +"|"+			//操作人名	是
	                         organizationName +"|"+		//单位名称	是
	                         organizationID +"|"+		//单位机构代码	是
	                         opTime +"|"+				//操作时间	是
	                         resTime +"|"+				//响应时间	是
	                         terminalType +"|"+			//终端类型	是	0.非移动终端；1.移动终端
	                         terminalID +"|"+			//终端标识	是
	                         terminalNum +"|"+			//移动终端号码	否
	                         terminalMac +"|"+			//移动终端MAC	否
	                         opType +"|"+				//操作行为类型	是	0：登录；1：查询；2：新增；3：修改；4：删除；5：退出
	                         operateCondition +"|"+		//操作条件	是
	                         operateResult +"|"+		//操作结果	是	0：失败；1：成功
	                         errCode +"|"+				//失败原因代码	否
	                         clientIp +"|"+				//客户端IP	是
	                         clientPort +"|"+			//客户端Port	是
	                         url +"|"+					//目标URL	是
	                         objectParams +"|"+			//目标URL参数	否
	                         sessionID +"|"+			//会话ID	是
	                         funcModuleName +"|"+		//功能模块名称	是
	                         objectIP +"|"+				//目标IP	是
	                         objectPort +"|"+			//目标Port	是
	                         querySql ;					//查询语句
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        try {
            //logger.info("执行完了");
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            HttpSession session = request.getSession();
            UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
            if (userinfo != null) {
            	//awdApi.logsSave(logsModel);

                logContent = logContent.replace("操作结果", "0");

                logger.info(logContent);

                saveLogByFile(logContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //异常处理
    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) throws Throwable {
        logger.error("出错了");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
        if (userinfo != null) {
            logContent = logContent.replace("操作结果", "1");

            logger.info(logContent);

            saveLogByFile(logContent);
        }
    }

    private static String getFixLenth(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);

    }

    private static String getFixLenthString(int strLength) {

        String random = "";
        for (int i = 0; i < 10; i++) {
            random += getFixLenth(6);
        }
        return random + getFixLenth(4);

    }

    private void saveLogByFile(String content) throws IOException {
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(filePath + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file, true);
        StringBuffer sb = new StringBuffer();
        sb.append(content + "\n");
        out.write(sb.toString().getBytes("utf-8"));
        out.close();
        //logger.info("文件更新成功!!!");
    }
}
