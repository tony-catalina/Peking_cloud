package awd.cloud.internet.app.smsy.api.hystrix;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import awd.cloud.internet.app.smsy.api.AwdApi;
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
import feign.hystrix.FallbackFactory;

@Service("awdApi")
public class AwdApiHystrix implements FallbackFactory<AwdApi> {

	public static Logger logger = Logger.getLogger(AwdApi.class);
	
	@Override
	public AwdApi create(Throwable throwable) {
		return new AwdApi() {
			

			@Override
			public ResponseMessage<PagerResult<AdminModel>> queryAdmin(QueryParam queryParam) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<List<SmsyModel>> getDshrycx() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<List<SmsyModel>> queryPhase() {
				throwable.printStackTrace();
		        logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
		        return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<List<SmsyModel>> queryShsb(String badw) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage addUser(String wxh,String dwbh,String type) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<UserinfoModel> loginCheck(String wxh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}


			@Override
			public ResponseMessage<PagerResult<SmsyModel>> querySmsy(QueryParam queryParam) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}
			

			@Override
			public ResponseMessage<List<UnitModel>> queryPcs() {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}
			@Override
			public ResponseMessage<PagerResult<UserinfoModel>> userQuery(QueryParam param) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<List<SmsyModel>> querySyryByBadw(String badw) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> getDwmcByDwbhAndType(String dwbh, String type) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> upload(MultipartFile file, String uuid) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> pcsAddSm(SmsyModel smsyModel) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> fileDelete(String path, String suffix, String id) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<PagerResult<UnitModel>> queryUnit(QueryParam queryParam) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> updateSmsy(String id, SmsyModel smsyModel) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> addUserInfo(UserinfoModel userinfoModel) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}
			
			

			@Override
			public ResponseMessage<String> userInfoUpdate(String id, UserinfoModel userinfoModel) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<UnitModel> getDwmcDwdzByDwbhAndType(String dwbh, String type) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<List<FileModel>> queryFilesByid(String id) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<FileModel> queryFileByFileid(String id) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<Map<String, Object>> pcsZdchSmsy(String idString,String chyy) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<String> save(TsyyModel tsyyModel) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<PagerResult<TsyyModel>> getTsyy(QueryParam queryParam) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<PagerResult<FjsysModel>> getsyfj(QueryParam queryParam) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<PagerResult<TzggModel>> getTzgg(QueryParam param) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			
			
			
			
			@Override
			public ResponseMessage<String> logsSave(LogsModel logsModel) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<String> updateFjByJsbh(String sysl, String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<String> smdelete(String id) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<String> addUnit(UnitModel unit) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<PagerResult<ClglModel>> queryClgl(QueryParam queryParam) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<String> addClgl(ClglModel clglModel) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}



			@Override
			public ResponseMessage<String> deleteClglByid(String id) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> updateSmsyList(String[] ids,SmsyAndFcjlModel smsyAndFcjlModel) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage init() {
				return null;
			}
			
			@Override
			public ResponseMessage<Map<String, Object>> getRootOrg() {
				return null;
			}

			@Override
			public ResponseMessage<List<Map<String, Object>>> getOrgsByParentCode(String rootCode) {
				return null;
			}

			@Override
			public ResponseMessage<List<Map<String, Object>>> getUserByOrgCode(String orgCode) {
				return null;
			}
			
			@Override
			public ResponseMessage<Map<String, Object>> getOrgInfo(String orgCode) {
				return null;
			}

			@Override
			public ResponseMessage<Map<String, Object>> getUserInfo(String userCode) {
				return null;
			}

			@Override
			public ResponseMessage<List<Map<String, Object>>> getResourceByAppCode() {
				return null;
			}

			@Override
			public ResponseMessage<List<Map<String, Object>>> getRoleByAppCode() {
				return null;
			}

			@Override
			public ResponseMessage<List<Map<String, Object>>> getOrgList(String type) {
				return null;
			}

			@Override
			public ResponseMessage<UserDetailModel> getUserModelFrom4A(UserDetailModel userinfoModel) {
				return null;
			}

			@Override
			public ResponseMessage<String> deleteUserCacheByUserid(String userid) {
				return null;
			}

			@Override
			public ResponseMessage<UserDetailModel> registeUser(UserinfoModel userinfoModel) {
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<FcjlModel>> queryFcjl(QueryParam queryParam) {
				return null;
			}

			@Override
			public ResponseMessage<String> addFcjl(FcjlModel fcjlModel) {
				return null;
			}

			@Override
			public ResponseMessage<String> updateFcjl(String id,FcjlModel fcjlModel) {
				return null;
			}
			
			@Override
			public ResponseMessage<String> deleteFcjlByid(String id) {
				return null;
			}

			@Override
			public ResponseMessage<String> updateFcjlAfterDsm(String fcuuid,String badw,String fczt) {
				return null;
			}


			@Override
			public ResponseMessage<String> setCommonlyUnit(String userid, String dwbh, String option) {
				return null;
			}


			@Override
			public ResponseMessage<PagerResult<UnitcommonlyModel>> queryCommonlyUnit(QueryParam queryParam) {
				return null;
			}

			

		};
	}

}
