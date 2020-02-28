package awd.cloud.internet.web.manager.utils;

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
        result.setData(object);
        result.setSuccess(true);
        return result;
    }
	public static Result<Object> success(Object totalSize ,Object data) {
    	Result<Object> result = new Result<Object>();
        result.setCode(Result.SUCCESS);
        result.setMsg("成功");
        result.setData(data);
        result.setCount(totalSize);
        result.setSuccess(true);
        return result;
    }
	public static Result<Object> success(String msg,Object totalSize ,Object data) {
    	Result<Object> result = new Result<Object>();
        result.setCode(Result.SUCCESS);
        result.setMsg(msg);
        result.setData(data);
        result.setCount(totalSize);
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
	
	
	
}
