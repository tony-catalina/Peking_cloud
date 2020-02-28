package awd.cloud.internet.app.smsy.api;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import awd.cloud.internet.app.smsy.api.hystrix.AwdApiHystrix;
import awd.cloud.internet.app.smsy.model.AdminModel;
import awd.cloud.internet.app.smsy.model.ClglModel;
import awd.cloud.internet.app.smsy.model.FcjlModel;
import awd.cloud.internet.app.smsy.model.FileModel;
import awd.cloud.internet.app.smsy.model.FjsysModel;
import awd.cloud.internet.app.smsy.model.LogsModel;
import awd.cloud.internet.app.smsy.model.SmsyAndFcjlModel;
import awd.cloud.internet.app.smsy.model.SmsyModel;
import awd.cloud.internet.app.smsy.model.TsyyModel;
import awd.cloud.internet.app.smsy.model.TzggModel;
import awd.cloud.internet.app.smsy.model.UnitModel;
import awd.cloud.internet.app.smsy.model.UnitcommonlyModel;
import awd.cloud.internet.app.smsy.model.UserDetailModel;
import awd.cloud.internet.app.smsy.model.UserinfoModel;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;


@FeignClient(name = "${awd.cloud.internet.server:AWD-CLOUD-INTERNET-SERVER}",fallbackFactory = AwdApiHystrix.class)
public interface AwdApi {
	
	
	/**
	 * 查询监管单位的车辆信息
	 * @param queryParam
	 * @return
	 */
	@GetMapping("/cloud_fcjl")
	@ResponseBody
	ResponseMessage<PagerResult<FcjlModel>> queryFcjl(@RequestBody QueryParam queryParam);
	
	@PostMapping("/cloud_fcjl")
	@ResponseBody
	ResponseMessage<String> addFcjl(@RequestBody FcjlModel fcjlModel);
	
	@PutMapping("/cloud_fcjl/{id}")
	@ResponseBody
	ResponseMessage<String> updateFcjl(@PathVariable(value="id") String id,@RequestBody FcjlModel fcjlModel);
	
	@DeleteMapping("/cloud_fcjl/{id}")
	@ResponseBody
	ResponseMessage<String> deleteFcjlByid(@PathVariable(value="id") String id);
	
	@PostMapping("/cloud_fcjl/updateFcjlAfterDsm")
	@ResponseBody
	ResponseMessage<String> updateFcjlAfterDsm(@RequestParam("fcuuid") String fcuuid,
					@RequestParam("badw") String badw,
					@RequestParam("fczt") String fczt);
	
	/**
	 * 查询监管单位的车辆信息
	 * @param queryParam
	 * @return
	 */
	@GetMapping("/cloud_clgl")
    @ResponseBody
    ResponseMessage<PagerResult<ClglModel>> queryClgl(@RequestBody QueryParam queryParam);
	
	@PostMapping("/cloud_clgl")
	@ResponseBody
	ResponseMessage<String> addClgl(@RequestBody ClglModel clglModel);
	
	@DeleteMapping("/cloud_clgl/{id}")
	@ResponseBody
	ResponseMessage<String> deleteClglByid(@PathVariable(value="id") String id);
	
	
	/**
	 * 动态查询admin
	 */
    @GetMapping("/cloud_admin")
    @ResponseBody
    ResponseMessage<PagerResult<AdminModel>> queryAdmin(@RequestBody QueryParam queryParam);
	
	/**
	 * 动态查询smsy
	 */
    @GetMapping("/cloud_smsy")
    @ResponseBody
    ResponseMessage<PagerResult<SmsyModel>> querySmsy(@RequestBody QueryParam queryParam);
    
       
	
	/**
	 * 发起上门页面的人员查询
	 * @return
	 */
    @PostMapping("/cloud_smsy/queryPhase")
    @ResponseBody
    ResponseMessage<List<SmsyModel>> queryPhase();
    
    /**
     * 未通过人员的查询
     * @return
     */
    @PostMapping("/cloud_smsy/queryShsb")
    @ResponseBody
    ResponseMessage<List<SmsyModel>> queryShsb(@RequestParam("badw") String badw);
    
    /**
     * 未通过人员的查询
     * @return
     */
    @PostMapping("/cloud_smsy/pcsZdchSmsy")
    @ResponseBody
    ResponseMessage<Map<String, Object>> pcsZdchSmsy(@RequestParam("ids") String idString,@RequestParam("chyy") String chyy);
    
    /**
     * 查询派出所
     * @return
     */
    @PostMapping("/cloud_unit/queryPcs")
    @ResponseBody
    ResponseMessage<List<UnitModel>> queryPcs();

    @RequestMapping(value = "/cloud_userinfo",method = RequestMethod.POST)
    ResponseMessage addUser(@RequestParam("wxh") String wxh,@RequestParam("dwbh") String dwbh,
                            @RequestParam("type") String type);

    @RequestMapping(value = "/cloud_userinfo/loginCheck",method = RequestMethod.POST)
    ResponseMessage<UserinfoModel> loginCheck(@RequestParam("wxh") String wxh);
    
    @RequestMapping(value = "/cloud_userinfo",method = RequestMethod.GET)
	ResponseMessage<PagerResult<UserinfoModel>> userQuery(QueryParam param);

    @PostMapping("/cloud_smsy/querySyryByBadw")
    @ResponseBody
    ResponseMessage<List<SmsyModel>> querySyryByBadw(@RequestParam("badw") String badw);

    @RequestMapping(value = "/cloud_unit/getDwmcByDwbhAndType",method = RequestMethod.POST)
    ResponseMessage<String> getDwmcByDwbhAndType(@RequestParam("dwbh") String dwbh,@RequestParam("type") String type);
    
    @RequestMapping(value = "/cloud_unit/getDwmcDwdzByDwbhAndType",method = RequestMethod.POST)
    ResponseMessage<UnitModel> getDwmcDwdzByDwbhAndType(@RequestParam("dwbh") String dwbh,@RequestParam("type") String type);
    
    @GetMapping("/cloud_unitcommonly")
    @ResponseBody
    ResponseMessage<PagerResult<UnitcommonlyModel>> queryCommonlyUnit(@RequestBody QueryParam queryParam);
    
    @RequestMapping(value = "/cloud_unitcommonly/setCommonlyUnit",method = RequestMethod.POST)
    ResponseMessage<String> setCommonlyUnit(@RequestParam("userid") String userid,
    		@RequestParam("dwbh") String dwbh,
    		@RequestParam("option") String option);
    
    
    @RequestMapping(value = "/cloud_file/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseMessage<String> upload(@RequestPart("file") MultipartFile file,@RequestParam("UUID") String uuid);

    @RequestMapping(value = "/cloud_file/queryFilesByid", method = RequestMethod.POST)
    ResponseMessage<List<FileModel>> queryFilesByid(@RequestParam("id") String id);
    
    @GetMapping("/cloud_file/{id}")
    ResponseMessage<FileModel> queryFileByFileid(@RequestParam("id") String id);

    @RequestMapping(value = "/cloud_smsy", method = RequestMethod.POST)
    ResponseMessage<String> pcsAddSm(@RequestBody SmsyModel smsyModel);
    
    @RequestMapping(value = "/cloud_smsy/{id}", method = RequestMethod.DELETE)
    ResponseMessage<String> smdelete(@PathVariable(value="id")String id);

    @RequestMapping(value = "/cloud_file/deleteFile", method = RequestMethod.POST)
    ResponseMessage<String> fileDelete(@RequestParam("path") String path,
                                       @RequestParam("suffix") String suffix,
                                       @RequestParam("id") String id);

    /**
     * 待审核人员查询
     * @return
     */

    @PostMapping("/cloud_smsy/getDshrycx")
    @ResponseBody
    ResponseMessage<List<SmsyModel>> getDshrycx();

    @GetMapping("/cloud_unit")
    @ResponseBody
    ResponseMessage<PagerResult<UnitModel>> queryUnit(@RequestBody QueryParam queryParam);
    
    @PutMapping("/cloud_smsy/{id}")
    @ResponseBody
    ResponseMessage<String> updateSmsy(@RequestParam(value = "id") String id,@RequestBody SmsyModel smsyModel);
    
    @PostMapping("/cloud_smsy/updateSmsyList")
    @ResponseBody
    ResponseMessage<String> updateSmsyList(@RequestParam(value = "ids") String[] ids,
    		@RequestBody SmsyAndFcjlModel smsyAndFcjlModel);

    
    @PostMapping("/cloud_userinfo")
    @ResponseBody
    ResponseMessage<String> addUserInfo(@RequestBody UserinfoModel userinfoModel);
    
    @PostMapping("/cloud_userinfo/registeUser")
    @ResponseBody
    ResponseMessage<UserDetailModel> registeUser(@RequestBody UserinfoModel userinfoModel);
    
    @PutMapping("/cloud_userinfo/{id}")
    @ResponseBody
    ResponseMessage<String> userInfoUpdate(@PathVariable(value="id")String id,@RequestBody UserinfoModel userinfoModel);
    
    /**
     * 保存提审预约方法
     */
    
    @PostMapping("/tsyy")
    @ResponseBody
    ResponseMessage<String> save(@RequestBody TsyyModel tsyyModel);
    

    @GetMapping("/tsyy")
    @ResponseBody
    ResponseMessage<PagerResult<TsyyModel>> getTsyy(QueryParam queryParam);
    
    @GetMapping("/fjsys")
    @ResponseBody
    ResponseMessage<PagerResult<FjsysModel>> getsyfj(QueryParam queryParam);

    /**
     * 日志存储
     */
    @PostMapping("/cloud_logs")
    @ResponseBody
    ResponseMessage<String> logsSave(@RequestBody LogsModel logsModel);

    @GetMapping("/tzgg")
	ResponseMessage<PagerResult<TzggModel>> getTzgg(QueryParam param);
    
    @PostMapping("/fjsys/updateFjByJsbh")
    public ResponseMessage<String> updateFjByJsbh(@RequestParam("sysl")String sysl,@RequestParam("jsbh")String jsbh);

    @PatchMapping("/cloud_unit")
	public ResponseMessage<String> addUnit(@RequestBody UnitModel unit);

   
    /**
     *  //下面是接口对接
     * @return
     */
    @GetMapping("/interface/init")
    ResponseMessage init();
    
    @PostMapping("/interface/getRootOrg")
    ResponseMessage<Map<String, Object>> getRootOrg();
	
    @PostMapping("/interface/getOrgsByParentCode")
    ResponseMessage<List<Map<String, Object>>> getOrgsByParentCode(@RequestParam("rootCode") String rootCode);
	
    @PostMapping("/interface/getUserByOrgCode")
    ResponseMessage<List<Map<String, Object>>> getUserByOrgCode(@RequestParam("orgCode") String orgCode);
    
    @PostMapping("/interface/getOrgInfo")
    ResponseMessage<Map<String, Object>> getOrgInfo(@RequestParam("orgCode") String orgCode);
	
    @PostMapping("/interface/getUserInfo")
    ResponseMessage<Map<String, Object>> getUserInfo(@RequestParam("userCode") String userCode);

    @PostMapping("/interface/getResourceByAppCode")
	ResponseMessage<List<Map<String, Object>>> getResourceByAppCode();
    
    @PostMapping("/interface/getRoleByAppCode")
    ResponseMessage<List<Map<String, Object>>> getRoleByAppCode();
    
    @PostMapping("/interface/getOrgList")
    ResponseMessage<List<Map<String, Object>>> getOrgList(@RequestParam(value = "type",required = false) String type);
    
    @PostMapping("/interface/getUserModelFrom4A")
    ResponseMessage<UserDetailModel> getUserModelFrom4A(@RequestBody UserDetailModel userinfoModel);
    
    @PostMapping("/interface/deleteUserCacheByUserid")
    ResponseMessage<String> deleteUserCacheByUserid(@RequestParam("userid") String userid);

}
