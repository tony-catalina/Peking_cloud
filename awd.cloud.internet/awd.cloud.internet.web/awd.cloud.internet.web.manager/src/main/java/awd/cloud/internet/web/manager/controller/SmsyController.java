package awd.cloud.internet.web.manager.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.Maps;
import awd.cloud.internet.web.manager.api.AwdApi;
import awd.cloud.internet.web.manager.models.SmsyModel;
import awd.cloud.internet.web.manager.utils.PagerResult;
import awd.cloud.internet.web.manager.utils.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import awd.framework.common.utils.StringUtils;
import awd.cloud.internet.web.manager.api.AwdApi;
import awd.cloud.internet.web.manager.models.UserinfoModel;
import awd.cloud.internet.web.manager.utils.PagerResult;
import awd.cloud.internet.web.manager.utils.ResponseMessage;
import awd.cloud.internet.web.manager.utils.Result;
import awd.cloud.internet.web.manager.utils.ResultUtils;
import awd.framework.common.utils.JSONUtil;
/**
 * 上门收押
 * 
 * @author sgh
 *
 */
@RestController
@RequestMapping("/smsy")
public class SmsyController {
	
	@Autowired
	AwdApi awdapi;

	// 收押查询
	@GetMapping("/smsylist")
	public String smsyList(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	// 收押申请
	@GetMapping("/smsysq")
	public String smsysq(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	// 收押审批
	@GetMapping("/smsysp")
	public String smsysp(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	// 收押登记
	@GetMapping("/sydj")
	public String sydj(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	// 收押退回
	@GetMapping("/syth")
	public String syth(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	// 收押确认
	@GetMapping("/syqr")
	public String syqr(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	// 办案单位
	@GetMapping("/badwlist")
	public String badwlist(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	@GetMapping("/badwsave")
	public String badwsave(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	@GetMapping("/badwupdate")
	public String badwupdate(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	@GetMapping("/badwdelete")
	public String badwdelete(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	// 监管单位
	@GetMapping("/jgdwlist")
	public String jgdwlist(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	@GetMapping("/jgdwsave")
	public String jgdwsave(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	@GetMapping("/jgdwupdate")
	public String jgdwupdate(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	@GetMapping("/jgdwdelete")
	public String jgdwdelete(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	// 用户管理
	@GetMapping("/userlist")
	public Result<Object> userlist(@RequestParam("username")String xm, @RequestParam("state")String state,HttpServletRequest  request) {
		Result<UserinfoModel> result =new Result<UserinfoModel>();
		String page=request.getParameter("page");
		String limit=request.getParameter("limit");
		QueryParam param=new QueryParam();
		param.setPageSize(Integer.parseInt(limit));
		param.setPageIndex(Integer.parseInt(page));
		param.and("state",TermType.eq,state)
		.and("xm", TermType.like, xm+"%");
		System.err.println(JSONUtil.toJson(param));
		ResponseMessage<PagerResult<UserinfoModel>> responeresult=awdapi.smsy_userlist(param);
		if(responeresult!=null&&responeresult.getStatus()==200) {
			return ResultUtils.success("查询成功", responeresult.getResult().getTotal(), responeresult.getResult().getData());
		}else {
			return ResultUtils.error("查询失败");
		}		
	}

	@GetMapping("/usersave")
	public String usersave(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	@GetMapping("/userupdate")
	public String userupdate(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	@GetMapping("/userdelete")
	public String userdelete(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	@GetMapping("/userenable")
	public String userenable(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	@GetMapping("/userforbid")
	public String userforbid(String lx, String dwbh) {
		String result = "stesta";

		return result;
	}

	
	//收押查询
	 @GetMapping("/syquery")
	 @ResponseBody
	 public Map<String, Object> syquery(SmsyModel model,HttpServletRequest request){
		 QueryParam param = new QueryParam();
		 String xyrxm = request.getParameter("xyrxm");
		 String phase = request.getParameter("phase");
		 if(!StringUtils.isNullOrEmpty(xyrxm)) {
			 param.and("xyrxm", TermType.like, "%"+xyrxm+"%");
		 }
		 if(!StringUtils.isNullOrEmpty(phase)) {
			 param.and("phase", TermType.eq, phase);
		 }
		 
		 String page = request.getParameter("page");
		String rows = request.getParameter("limit");
		int pageIndex = 0;
		int pageSize = 10;
		try {
			pageIndex = Integer.valueOf(page).intValue() - 1;
			pageSize = Integer.valueOf(rows).intValue();
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			System.err.println("分页参数出错！");
		}finally {
			param.setPageIndex(pageIndex);
			param.setPageSize(pageSize);
		}
		 ResponseMessage<PagerResult<SmsyModel>> smsyModel =awdapi.syqueryForPage(param);
		 Map<String, Object> outmap = Maps.newHashMap();
		 outmap.put("code", 0);
		 outmap.put("msg", "");
		 outmap.put("count",smsyModel.getResult().getTotal());
		 outmap.put("data", smsyModel.getResult().getData());
		 
		return outmap;
		 
	 }
	 
	 
	

}
