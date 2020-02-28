package awd.cloud.internet.web.manager.api.hystrix;

import org.springframework.stereotype.Service;

import awd.cloud.internet.web.manager.api.AwdApi;
import awd.cloud.internet.web.manager.models.FileModel;
import awd.cloud.internet.web.manager.models.SmsyModel;
import awd.cloud.internet.web.manager.models.TzggModel;
import awd.cloud.internet.web.manager.models.UnitModel;
import awd.cloud.internet.web.manager.models.UserinfoModel;
import awd.cloud.internet.web.manager.utils.PagerResult;
import awd.cloud.internet.web.manager.utils.ResponseMessage;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import awd.cloud.internet.web.manager.utils.PagerResult;
import awd.cloud.internet.web.manager.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;

@Service("awdApi")
public class AwdApiHystrix implements FallbackFactory<AwdApi> {

	@Override
	public AwdApi create(Throwable cause) {
		return new AwdApi() {

			@Override
			public ResponseMessage<PagerResult<UserinfoModel>> smsy_userlist(QueryParam param) {
				cause.printStackTrace();
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<SmsyModel>> syqueryForPage(QueryParam param) {
				cause.printStackTrace();
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<UserinfoModel>> userqueryForPage(QueryParam param) {
				cause.printStackTrace();
				return null;
			}

			@Override
			public ResponseMessage<String> usersave(UserinfoModel jbxxEntity) {
				cause.printStackTrace();
				return null;
			}

			@Override
			public ResponseMessage<String> userUpdate(String id, UserinfoModel userinfoModel) {
				cause.printStackTrace();
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<UnitModel>> unitqueryForPage(QueryParam param) {
				cause.printStackTrace();
				return null;
			}

			@Override
			public ResponseMessage<String> unitsave(UnitModel unitModel) {
				cause.printStackTrace();
				return null;
			}

			@Override
			public ResponseMessage<String> unitUpdate(String id, UnitModel unitModel) {
				cause.printStackTrace();
				return null;
			}

			@Override
			public UserinfoModel selectByUsername(String username, String password) {
				cause.printStackTrace();
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<FileModel>> filequeryForPage(QueryParam param) {
				cause.printStackTrace();
				return null;
			}

			@Override
			public ResponseMessage<String> enableUserinfo(UserinfoModel model) {
				cause.printStackTrace();
				return null;
			}

			@Override
			public ResponseMessage<String> shUserinfo(UserinfoModel model) {
				cause.printStackTrace();
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<TzggModel>> getTzgg(QueryParam param) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tzggSaveOrUpdate(TzggModel model) {
				// TODO Auto-generated method stub
				return null;
			}
			
			
		};
	}

}
