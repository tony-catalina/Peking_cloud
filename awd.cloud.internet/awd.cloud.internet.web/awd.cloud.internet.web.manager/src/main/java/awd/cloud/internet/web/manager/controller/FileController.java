package awd.cloud.internet.web.manager.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import awd.cloud.internet.web.manager.api.AwdApi;
import awd.cloud.internet.web.manager.models.FileModel;
import awd.cloud.internet.web.manager.models.SmsyModel;
import awd.cloud.internet.web.manager.utils.PagerResult;
import awd.cloud.internet.web.manager.utils.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import awd.framework.common.utils.StringUtils;

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	AwdApi awdapi;
	
	 @GetMapping("/filequery")
	 @ResponseBody
	 public Map<String, Object> syquery(FileModel model,HttpServletRequest request){
		String uuid = request.getParameter("uuid");
		QueryParam param = new QueryParam();
		if (!StringUtils.isNullOrEmpty(uuid)) {
			param.and("uuid", TermType.eq , uuid);
		}
		ResponseMessage<PagerResult<FileModel>> file = awdapi.filequeryForPage(param);

		Map<String, Object> outmap = Maps.newHashMap();
		outmap.put("code", 0);
		outmap.put("msg", "");
		outmap.put("count", file.getResult().getTotal());
		outmap.put("data", file.getResult().getData());
		return outmap;
		 
		 
	 }

}
