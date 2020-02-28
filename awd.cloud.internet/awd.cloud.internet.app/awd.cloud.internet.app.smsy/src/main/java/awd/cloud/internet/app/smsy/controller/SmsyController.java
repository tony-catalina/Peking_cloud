package awd.cloud.internet.app.smsy.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import awd.cloud.internet.app.smsy.api.AwdApi;
import awd.cloud.internet.app.smsy.model.FileModel;
import awd.cloud.internet.app.smsy.model.SmsyModel;
import awd.cloud.internet.app.smsy.model.UserDetailModel;
import awd.cloud.internet.app.smsy.model.UserinfoModel;
import awd.cloud.internet.app.smsy.utils.GeturlPic;
import awd.cloud.internet.app.smsy.utils.PagerResult;
import awd.cloud.internet.app.smsy.utils.QueryParam;
import awd.cloud.internet.app.smsy.utils.ResponseMessage;
import awd.cloud.internet.app.smsy.utils.TermType;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;

@Controller
public class SmsyController {

    @Autowired
    private AwdApi awdApi ;
    
    @Value("${awd.image.cache}")
    private String imagepath;
    
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<UserinfoModel> registe(@ModelAttribute UserinfoModel user,@RequestParam("type") String type) {
    	System.err.println("user---"+JSONUtil.toJson(user));
    	
    	if("0".equals(type)) {
        	user.setState("R2");
        }else {
        	user.setState("R3");
        }
    	user.setShbz("0");
    	user.setCreator("系统");
    	user.setCreatetime(Calendar.getInstance().getTime());
    	//ResponseMessage<String> re = awdApi.addUserInfo(user);
    	ResponseMessage<UserDetailModel> re = awdApi.registeUser(user);
        if(re.getStatus()==200){
            //return ResponseMessage.ok(user);
        	return ResponseMessage.ok(re.getResult());
        }else{
            return ResponseMessage.error("保存出错");//错误页面！
        }
    }
    
    
    @RequestMapping(value = "/userupdate",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<String> userupdate(UserinfoModel  userinfoModel,HttpServletRequest request){
    	ResponseMessage<String> result = awdApi.userInfoUpdate(userinfoModel.getId(), userinfoModel);
    	if ("success".equals(result.getResult())) {	//	算了，修改成功的话 就直接清空cookie吧
    		HttpSession session = request.getSession();
    		session.removeAttribute("userinfo");
		}
       return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<?> login(@RequestParam("userid") String userid, HttpServletRequest request) {
        QueryParam param = new QueryParam();
        if (StringUtils.isNullOrEmpty(userid)) {
        	return ResponseMessage.error("身份号错误！");
		}
        param.setPaging(false);
        param.and("userid", TermType.eq, userid);
        ResponseMessage<PagerResult<UserinfoModel>> result = awdApi.userQuery(param);
        if (result != null && result.getStatus() == 200) {
            List<UserinfoModel> list = result.getResult().getData();
            if (list != null && list.size() > 0) {
                UserinfoModel model = list.get(0);
                HttpSession session = request.getSession();
                Object userinfo = session.getAttribute("userinfo");
                if (!StringUtils.isNullOrEmpty(userinfo)) {
                    session.removeAttribute("userinfo");
				}
                session.setAttribute("userinfo", model);
                session.setMaxInactiveInterval(24 * 60 * 60);
                model.setUpdatetime(Calendar.getInstance().getTime());
                awdApi.userInfoUpdate(model.getId(), model);
                return ResponseMessage.ok(model);
                
            } else {
            	 UserinfoModel user = new UserinfoModel();
            	return ResponseMessage.ok(new UserinfoModel());
            }
        } else {
            return ResponseMessage.error("登陆错误");
        }
    }

    /**
     * 法律文书保存
     *
     * @param file
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<String> upload(@RequestPart("file") MultipartFile file,
                                          @RequestParam("uuid") String uuid) {

        if (file.isEmpty()) {
            return ResponseMessage.error("文件为空，保存失败！");
        }
        System.out.println("文件大小："+file.getSize());
        if (file.getSize() > 50000000) {
            return ResponseMessage.error("文件大于5M，保存失败！");
        }
        ResponseMessage<String> res = awdApi.upload(file, uuid);
        if (res.getStatus() == 200) {
            return ResponseMessage.ok();
        } else {
            return ResponseMessage.error(res.getMessage());
        }
    }
    /**
     * 法律文书 删除
     * @param path
     * @param suffix
     * @param id
     * @return
     */
    @RequestMapping(value = "/fileDelete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<String> fileDelete(@RequestParam("path") String path,
                                              @RequestParam("suffix")String suffix,
                                              @RequestParam("id")String id) {

        if (StringUtils.isNullOrEmpty(path)||StringUtils.isNullOrEmpty(suffix)||StringUtils.isNullOrEmpty(id)) {
            return ResponseMessage.error("法律文书删除失败！");
        }

        ResponseMessage<String> res = awdApi.fileDelete(path, suffix,id);
        if (res.getStatus() == 200) {
            return ResponseMessage.ok();
        } else {
            return ResponseMessage.error(res.getMessage());
        }
    }
    /**
     * 派出所 新增上门
     * Phase 阶段 定义
     * put("1", "等待审批");
     * put("2", "审批被撤");
     * put("3", "等待上门");
     * put("4", "等待收押");
     * put("5", "收押入所");
     * put("6", "人员拒收");
     * put("7", "重新审批");
     *
     * @param badw
     * @param badwdz
     * @param gyjs
     * @param xyrxm
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/pcsFqsm",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage<String> pcsfqsm(@RequestParam("badw") String badw,
                                           @RequestParam("badwdz") String badwdz,
                                           @RequestParam("gyjs") String gyjs,
                                           @RequestParam("xyrxm") String xyrxm,
                                           @RequestParam("uuid") String uuid,
                                           HttpServletRequest request
    ) {
        HttpSession session = request.getSession();

        UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
        SmsyModel smsyModel = new SmsyModel();
        smsyModel.setBadw(badw);
        smsyModel.setBadwdz(badwdz);
        smsyModel.setGyjs(gyjs);
        smsyModel.setXyrxm(xyrxm);
        smsyModel.setUuid(uuid);
        smsyModel.setPhase("1");
        smsyModel.setState("R2");
        smsyModel.setCreatetime(Calendar.getInstance().getTime());
        smsyModel.setCreator("as");
        ResponseMessage<String> responseMessage = awdApi.pcsAddSm(smsyModel);

        if (responseMessage.getStatus() == 200) {
            return ResponseMessage.ok("保存成功");
        } else {
            return ResponseMessage.error("保存失败！");
        }
    }
    
    /**
     * 注册时判读微信号存不存在
     * @param request
     */
    @RequestMapping(value = "/checkWxh",method = RequestMethod.GET)
    @ResponseBody
    public int checkWxh(HttpServletRequest request) {
    	String wxh = request.getParameter("wxh");
    	QueryParam qParam = new QueryParam();
    	qParam.and("wxh", wxh);
    	ResponseMessage<PagerResult<UserinfoModel>> rm = awdApi.userQuery(qParam);
    	if(rm.getStatus() == 200) {
    		//返回存在的微信号数量
    		return rm.getResult().getTotal();
    	}else {
    		//自定义返回值，代表执行失败
    		return 500;	
    	}
    }
    
    /**
     *看守所办理上门人员法律文书不通过 
     * @param request
     */
    @RequestMapping(value = "/kssDsmRySp",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage<String> kssDsmRySp(HttpServletRequest request) {
    	String phase = request.getParameter("phase");
    	String[] ids = request.getParameterValues("ids");
    	for(String id :ids) {
    		SmsyModel smsy = new SmsyModel();
    		smsy.setPhase(phase);
    	 	awdApi.updateSmsy(id,smsy);
    	}
    	return ResponseMessage.ok("办理成功");
    	
    }
    
    /**
     * 
     * @return
     */
    @PostMapping("/getPhotoByFileid")
    @ResponseBody
    public byte[] getPhotoByFileid(String id) {
    	ResponseMessage<FileModel> result = awdApi.queryFileByFileid(id);
    	byte[] byteList = new byte[0];
    	if (result.getStatus() == 200) {
    		FileModel file = result.getResult();
    		return GeturlPic.fileConvertToByteArray(new File(file.getFilepath()));
//    		return file.getFile();
		}
    	return byteList;
	}

    
    @RequestMapping(value = "/getPic.jpg",method = RequestMethod.GET)
    @ResponseBody
    public String getPic(HttpServletRequest request,HttpServletResponse response) {
        //String uuid = request.getParameter("uuid");
        String id = request.getParameter("id");
        if(!StringUtils.isNullOrEmpty(id)) {
            OutputStream outputSream = null;
            byte[] pictureByte = getPhotoByFileid(id);
            if(pictureByte!=null) {
                try {
                    outputSream = response.getOutputStream();
                    outputSream.write(pictureByte);
                }catch(Exception ex) {
                    ex.printStackTrace();
                }finally {
                    try {
                        if(outputSream != null) {
                            outputSream.close();
                        }
                    }catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
    
    
    
    /**
     * 派出所通知上门
     */
    @RequestMapping(value = "/updateTzsm",method = RequestMethod.GET)
    @ResponseBody
    public String updateTzsm(HttpServletRequest request,HttpServletResponse response) {
    	
		return null;
    	
    }
    
    @RequestMapping(value = "/userlist",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage<PagerResult<UserinfoModel>> userlist(HttpServletRequest request) {    	
    	HttpSession session = request.getSession();
		UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
		String dwbh = request.getParameter("dwbh");
		String state = request.getParameter("state");
		if (StringUtils.isNullOrEmpty(dwbh)) {
			dwbh = userinfo.getDwbh();
		}
		QueryParam param=new QueryParam();
		param.setPaging(false);
		param.and("dwbh", dwbh);
		param.and("state", state);
    	
    	return awdApi.userQuery(param);
    	
    }
    
    
}
