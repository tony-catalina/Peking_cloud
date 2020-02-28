package awd.cloud.internet.app.smsy.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import awd.cloud.internet.app.smsy.model.UserinfoModel;
import awd.framework.common.utils.StringUtils;

public class UserInfoUtil {

	public static UserinfoModel getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserinfoModel userinfo = (UserinfoModel) session.getAttribute("userinfo");
		
		if (StringUtils.isNullOrEmpty(userinfo)) {
			
		}
		
		return userinfo;
	}
}
