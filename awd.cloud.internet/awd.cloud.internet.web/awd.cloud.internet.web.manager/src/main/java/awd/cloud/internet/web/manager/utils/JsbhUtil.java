package awd.cloud.internet.web.manager.utils;

public class JsbhUtil {
	public static String KSS="1";
	public static String JLS="2";
	public static String JDS="3";
	public static String SJS="4";
	public static String AKYY="5";
	
	public static boolean isKss(String jsbh) {
		return KSS.equals(jsType(jsbh));
	}
	public static boolean isJls(String jsbh) {
		return JLS.equals(jsType(jsbh));
	}
	public static boolean isJds(String jsbh) {
		return JDS.equals(jsType(jsbh));
	}
	
	public static boolean isSjs(String jsbh) {
		return SJS.equals(jsType(jsbh));
	}
	
	public static boolean isAkyy(String jsbh) {
		return AKYY.equals(jsType(jsbh));
	}
	public static boolean isZOND(String jsbh) {
		boolean iszongdui=!jsbh.substring(0,2).equals("00")&&jsbh.substring(2,9).equals("0000000");
		return iszongdui;
	}
	public static boolean isZD(String jsbh) {
		if(!isZOND(jsbh)) {
			boolean iszd=!jsbh.substring(0,4).equals("0000")&&jsbh.substring(4,9).equals("00000");
			return iszd;
		}
		return false;
		
	}
	public static String jsType(String jsbh) {
		if(jsbh.length()==9) {
			return jsbh.substring(7, 8);
		}
		return "-1";
	}

}
