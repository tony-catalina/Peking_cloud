package awd.cloud.internet.web.manager.controller;

import java.util.Date;
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
import awd.cloud.internet.web.manager.models.UnitModel;
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
@RequestMapping("/dw")
public class DwController {
	
	
			@Autowired
			AwdApi awdapi;
	
		 @GetMapping("/userquery")
		 @ResponseBody
		 public Map<String, Object> userquery(UnitModel model,HttpServletRequest request){
			QueryParam param = new QueryParam();
			String username = request.getParameter("dwmc");
			String dwlx = request.getParameter("dwlx");
			String dwbh = request.getParameter("bh");
			if(!StringUtils.isNullOrEmpty(username)) {
				param.and("dwmc", TermType.like, "%"+username+"%");
			}
			if(!StringUtils.isNullOrEmpty(dwbh)) {
				param.and("dwbh", TermType.like, "%"+dwbh+"%");
			}
			if(!StringUtils.isNullOrEmpty(dwlx)) {
				param.and("type", TermType.eq, dwlx);
			}
			param.and("state", "R2");
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

			ResponseMessage<PagerResult<UnitModel>> user = awdapi.unitqueryForPage(param);
			Map<String, Object> outmap = Maps.newHashMap();
			outmap.put("code", 0);
			outmap.put("msg", "");
			outmap.put("count", user.getResult().getTotal());
			outmap.put("data", user.getResult().getData());
			return outmap;
		 }
		 
		 
		 @PostMapping("/saveUnit")
		 @ResponseBody
		 public ResponseMessage<String>  saveUnit(@Validated() UnitModel unitModel){
			 User users =  Authentication.current().get().getUser();
			 unitModel.setCreator(users.getUsername());
			 unitModel.setState("R2");
			 unitModel.setCreatetime(new Date());
			 System.err.println(JSON.toJSON(unitModel));
			 ResponseMessage<String> user = awdapi.unitsave(unitModel);
			if (user!=null&&user.getStatus() == 200) {
	            return ResponseMessage.ok("保存成功");
	        }else {
	        	return ResponseMessage.error("保存失败");
	        }
		 }
		 
		 
		 @PostMapping("/updateUnit")
		 @ResponseBody
		 public Result<?>  updateUnit(@Validated() UnitModel unitModel){
			 User users =  Authentication.current().get().getUser();
			 unitModel.setUpdator(users.getUsername());
			 unitModel.setUpdatetime(new Date());
			 ResponseMessage<String> user = awdapi.unitUpdate(unitModel.getId(), unitModel);
			 System.err.println(user.getStatus());
			if (user.getStatus() == 200) {
	            return ResultUtils.success("修改成功");
	        }
	        return  ResultUtils.error(Result.ERR_SAVE, "保存失败");
			 
		 }
		 
		 
		 @PostMapping("/deleteUnit")
		 @ResponseBody
		 public Result<?>  deleteUnit(UnitModel unitModel,HttpServletRequest request){
			 String id = request.getParameter("ids");
			 String[] ids = id.split(",");
			 if(ids.length>0) {
			 for(int i=0;i<ids.length;i++) {
			unitModel.setState("R3");
			 ResponseMessage<String> user = awdapi.unitUpdate(ids[i], unitModel);
			 System.err.println(user.getStatus());
			if (user.getStatus() == 200) {
	            return ResultUtils.success("成功");
	        }
	        return  ResultUtils.error(Result.ERR_SAVE, "保存失败");
		 }
			 }
			 return ResultUtils.error(Result.ERR_SAVE, "保存失败");
		 }
		 
		 
		 @PostMapping("/getPcsUnit")
		 @ResponseBody
		 public Result<?>  getPcsUnit(HttpServletRequest request){
			 QueryParam param = new QueryParam();
			 param.setPaging(false);
			 param.and("type", "0");
			 ResponseMessage<PagerResult<UnitModel>> re = awdapi.unitqueryForPage(param);
			 Result result = new Result<>();
			 result.setData(re.getResult().getData());
			 result.setCode(200);
			 return  result;
			 
		 }
		 
		 

}
