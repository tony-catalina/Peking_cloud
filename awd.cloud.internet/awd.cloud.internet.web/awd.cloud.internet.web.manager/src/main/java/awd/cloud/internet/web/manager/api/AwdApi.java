package awd.cloud.internet.web.manager.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import awd.cloud.internet.web.manager.api.hystrix.AwdApiHystrix;
import awd.cloud.internet.web.manager.models.FileModel;
import awd.cloud.internet.web.manager.models.SmsyModel;
import awd.cloud.internet.web.manager.models.TzggModel;
import awd.cloud.internet.web.manager.models.UnitModel;
import awd.cloud.internet.web.manager.models.UserinfoModel;
import awd.cloud.internet.web.manager.utils.PagerResult;
import awd.cloud.internet.web.manager.utils.ResponseMessage;
import awd.framework.common.datasource.orm.core.param.QueryParam;


@FeignClient(name = "${awd.cloud.internet.server:awd-cloud-internet-server}",url="http://192.168.4.73:11101", fallbackFactory=AwdApiHystrix.class)
public interface AwdApi {


	@RequestMapping(value="/cloud_smsy", method = RequestMethod.GET)
	ResponseMessage<PagerResult<SmsyModel>> syqueryForPage(QueryParam param);
	
	@RequestMapping(value = "/user/getbypass", method = RequestMethod.GET)
	UserinfoModel selectByUsername(@RequestParam("username")String username, @RequestParam("password")String password);
	
	@RequestMapping(value = "/cloud_userinfo", method = RequestMethod.GET)
	ResponseMessage<PagerResult<UserinfoModel>> smsy_userlist(QueryParam param);

	@RequestMapping(value="/cloud_userinfo", method = RequestMethod.GET)
	 ResponseMessage<PagerResult<UserinfoModel>> userqueryForPage(QueryParam param);
	
	@PostMapping("/cloud_userinfo")
    ResponseMessage<String> usersave(@RequestBody UserinfoModel userinfoModel);
	
	@PutMapping("/cloud_userinfo/{id}")
	public ResponseMessage<String> userUpdate(@RequestParam(value = "id") String id, @RequestBody UserinfoModel userinfoModel);
	
	@RequestMapping(value="/cloud_unit", method = RequestMethod.GET)
	 ResponseMessage<PagerResult<UnitModel>> unitqueryForPage(QueryParam param);
	
	@PostMapping("/cloud_unit")
    ResponseMessage<String> unitsave(@RequestBody UnitModel unitModel);
	
	@PutMapping("/cloud_unit/{id}")
	public ResponseMessage<String> unitUpdate(@RequestParam(value = "id") String id, @RequestBody UnitModel unitModel);
	
	@RequestMapping(value="/cloud_file", method = RequestMethod.GET)
	ResponseMessage<PagerResult<FileModel>> filequeryForPage(QueryParam param);

	@RequestMapping(value="/cloud_userinfo/enableUser", method = RequestMethod.POST)
	ResponseMessage<String> enableUserinfo(@RequestBody UserinfoModel model);
	
	@RequestMapping(value="/cloud_userinfo/shUser", method = RequestMethod.POST)
	ResponseMessage<String> shUserinfo(@RequestBody UserinfoModel model);
	
	@GetMapping("/tzgg")
	ResponseMessage<PagerResult<TzggModel>> getTzgg(QueryParam param);
	 
	@PatchMapping("/tzgg")
	public ResponseMessage<String> tzggSaveOrUpdate(@RequestBody TzggModel model);
	
}
