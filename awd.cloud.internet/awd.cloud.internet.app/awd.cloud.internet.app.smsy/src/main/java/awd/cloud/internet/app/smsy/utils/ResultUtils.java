package awd.cloud.internet.app.smsy.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * \* Created with IntelliJ IDEA By WS
 * \* Date: 2017/11/22 10:18
 * \
 */

public class ResultUtils {


	public static Result<Object> success(Object object) {
		Result<Object> result = new Result<Object>();
        result.setCode(Result.SUCCESS);
        result.setMsg("成功");
        result.setResult(object);
        result.setSuccess(true);
        return result;
    }
	public static Result<Object> success(Object totalSize ,Object data) {
    	Result<Object> result = new Result<Object>();
        result.setCode(Result.SUCCESS);
        result.setMsg("成功");
        result.setResult(data);
        result.setTotalSize(totalSize);
        result.setSuccess(true);
        return result;
    }
	public static Result<Object> success(String msg,Object totalSize ,Object data) {
    	Result<Object> result = new Result<Object>();
        result.setCode(Result.SUCCESS);
        result.setMsg(msg);
        result.setResult(data);
        result.setTotalSize(totalSize);
        result.setSuccess(true);
        return result;
    }

    public static Result<Object> success() {
        return success(null);
    }

	public static Result<Object> error(Integer code, String msg) {
        Result<Object> result = new Result<Object>();
        result.setCode(code);
        result.setMsg(msg);
        result.setSuccess(false);
        return result;
    }
	public static Result<Object> error(String msg) {
        Result<Object> result = new Result<Object>();
        result.setCode(Result.ERR_EXCEPTION);
        result.setMsg(msg);
        result.setSuccess(false);
        return result;
    }
	/**
	 * 处理删除状态（state）
	 * @param param
	 * @return
	 */
	public static Map<String, String[]> paramState(String param){
		Map<String,String[]> map=new HashMap<String,String[]>();
		if(param==null) {			
			map.put("state$eq",new String[] {"R2"});
		}	
		return map;
	}
	/**
	 * 处理在所人员状态（state）
	 * @param param
	 * @return
	 */
	public static Map<String, String[]> paramZsztState(String param){
		Map<String, String[]> map=new HashMap<String ,String[]>();
		if(param==null) {
			map.put("state$eq", new String[] {"R8"});
		}
		return map;
	}
	/**
	 * 处理是否是在押人员(01)还是历史人员(02)（flag）
	 * @param param
	 * @return
	 */
	public static Map<String, String[]> paramFlag(String param){
		Map<String, String[]> map=new HashMap<String,String[]>();
		if(param==null) {
			map.put("flag$eq", new String[] {"01"});
		}
		return map;
	}
	/**
	 * 处理排序（sort）
	 * @param param
	 * @return
	 */
	public static Map<String, String[]> paramSort(String param){
		Map<String,String[]> map=new HashMap<String,String[]>();	
		if(param!=null&&param.indexOf("String")>0) {
			param = param.substring(0,param.length()-6);
			map.put("sort", new String[] {param});
		}		
		return map;
	}
	/**
	 * 处理排序方式（是否升降序）
	 * @param param
	 * @return
	 */
	public static Map<String,String[]> paramOrder(String param){
		Map<String, String[]> map=new HashMap<String,String[]>();
			if(param==null) {
				map.put("order", new String[] {"desc"});
			}
		return map;
	}
}
