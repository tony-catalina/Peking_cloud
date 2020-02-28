package awd.cloud.internet.web.manager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import awd.cloud.internet.web.manager.api.AwdApi;
import awd.cloud.internet.web.manager.models.SmsyModel;
import awd.cloud.internet.web.manager.models.UserinfoModel;
import awd.cloud.internet.web.manager.utils.PagerResult;
import awd.cloud.internet.web.manager.utils.ResponseMessage;
import awd.cloud.internet.web.manager.utils.Result;
import awd.cloud.internet.web.manager.utils.ResultUtils;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.authorization.Authentication;
import awd.framework.expands.authorization.User;

@RestController
@RequestMapping("/userinfo")
public class UserinfoController {
	
	@Autowired
	AwdApi awdapi;
	
	//收押查询
	 @GetMapping("/userquery")
	 @ResponseBody
	 public Map<String, Object> userquery(UserinfoModel model,HttpServletRequest request){
		QueryParam param = new QueryParam();
		String username = request.getParameter("username");
		String state = request.getParameter("state");
 		if(!StringUtils.isNullOrEmpty(username)) {
			param.and("username", TermType.like, "%"+username+"%");
		}
 		if(!StringUtils.isNullOrEmpty(state)) {
 			String[] value = state.split(",");
 			param.and("state", value[0]);
 			param.and("shbz", value[1]);
 			
 		}
		String page = request.getParameter("page");
		String rows = request.getParameter("limit");
		int pageIndex = 0;
		int pageSize = 10;
		try {
			pageIndex = Integer.valueOf(page).intValue() - 1;
			pageSize = Integer.valueOf(rows).intValue();
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			System.err.println("分页参数出错！");
		} finally {
			param.setPageIndex(pageIndex);
			param.setPageSize(pageSize);
		}

		ResponseMessage<PagerResult<UserinfoModel>> user = awdapi.userqueryForPage(param);
		Map<String, Object> outmap = Maps.newHashMap();
		outmap.put("code", 0);
		outmap.put("msg", "");
		outmap.put("count", user.getResult().getTotal());
		outmap.put("data", user.getResult().getData());
		return outmap;
	 }
	 
	 
	 @PostMapping("/saveUserinfo")
	 @ResponseBody
	 public ResponseMessage<String>  saveUserinfo(@Validated() UserinfoModel userinfoModel){
		 User users =  Authentication.current().get().getUser();
		 userinfoModel.setCreator(users.getName());
		 userinfoModel.setState("R2");
		 userinfoModel.setCreatetime(new Date());
		 ResponseMessage<String> user = awdapi.usersave(userinfoModel);
		 System.err.println(user.getStatus());
		if (user.getStatus() == 200) {
			System.err.println(ResultUtils.success(Result.SUCCESS,"保存成功"));
            return ResponseMessage.ok("公文发送成功");
        }
        return  user;
		 
	 }
	 
	 @PostMapping("/updateUserinfo")
	 @ResponseBody
	 public Result<?>  updateUserinfo(@Validated() UserinfoModel userinfoModel){
		 User users =  Authentication.current().get().getUser();
		 userinfoModel.setUpdator(users.getName());
		 userinfoModel.setUpdatetime(new Date());
		 ResponseMessage<String> user = awdapi.userUpdate(userinfoModel.getId(), userinfoModel);
		 System.err.println(user.getStatus());
		if (user.getStatus() == 200) {
            return ResultUtils.success("成功");
        }
        return  ResultUtils.error(Result.ERR_SAVE, "保存失败");		 
	 }
	 
	 
	 @PostMapping("/deleteUserinfo")
	 @ResponseBody
	 public Result<?>  deleteUserinfo(UserinfoModel userinfoModel,HttpServletRequest request){
		 String id = request.getParameter("ids");
		 String[] ids = id.split(",");
		 if(ids.length>0) {
		 for(int i=0;i<ids.length;i++) {
		 userinfoModel.setState("R3");
		 ResponseMessage<String> user = awdapi.userUpdate(ids[i], userinfoModel);
		 System.err.println(user.getStatus());
		if (user.getStatus() == 200) {
            return ResultUtils.success("成功");
        }
        return  ResultUtils.error(Result.ERR_SAVE, "保存失败");
	 }
		 }
		 return ResultUtils.error(Result.ERR_SAVE, "保存失败");
	 }
	
	 
	 @PostMapping("/enableUserinfo")
	 @ResponseBody
	 public Result<?>  enableUserinfo(HttpServletRequest request){
		 String state = request.getParameter("state");
		 String id = request.getParameter("ids");
		 System.err.println(id);
		 System.err.println(state);
		 UserinfoModel userinfoModel=new UserinfoModel();
		 userinfoModel.setId(id);
		 userinfoModel.setState(state);
		 ResponseMessage<String> result=awdapi.enableUserinfo(userinfoModel);
		 System.err.println(result);
		 if("R2".equals(state)) {
			 if(result!=null&&result.getStatus()==200) {
				 return ResultUtils.success("启用成功");
			 }else {
				 return ResultUtils.error(Result.ERR_SAVE, "保存失败");
			 }			 
		 }else {
			 if(result!=null&&result.getStatus()==200) {
				 return ResultUtils.success("禁用成功");
			 }else {
				 return ResultUtils.error(Result.ERR_SAVE, "保存失败");
			 }
		 }
	 }
	 
	 @PostMapping("/shUserinfo")
	 @ResponseBody
	 public Result<?>  shUserinfo(HttpServletRequest request){
		 String state = request.getParameter("state");
		 String id = request.getParameter("ids");
		 String shbz = request.getParameter("shbz");
		 System.err.println(id);
		 System.err.println(state);
		 System.err.println(shbz);
		 UserinfoModel userinfoModel=new UserinfoModel();
		 userinfoModel.setId(id);
		 userinfoModel.setState(state);
		 userinfoModel.setShbz(shbz);
		 ResponseMessage<String> result=awdapi.shUserinfo(userinfoModel);
		 System.err.println(result);
		 if(result!=null&&result.getStatus()==200) {
			 return ResultUtils.success("审核成功");
		 }else {
			 return ResultUtils.error(Result.ERR_SAVE, "审核失败");
		 }			 
	 }
	 
}
