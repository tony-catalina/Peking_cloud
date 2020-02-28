package awd.cloud.internet.web.manager.utils;

/**
* @author Create By WS:
* @version 创建时间：2017年11月17日 上午9:49:27
*/

public class Result<T> {
	
	/** 校验错误 */
    public static final Integer ERR_VALIDATE = -1; 

	/** 保存错误 */
    public static final Integer ERR_SAVE = 100; 
    
	/** 更新错误 */
    public static final Integer ERR_UPDATE = 101; 
    
	/** 删除错误 */
    public static final Integer ERR_DELETE = 102; 

	/** 成功  */
    public static final Integer SUCCESS = 0; 
    
    /** 异常  */
    public static final Integer ERR_EXCEPTION = -200;

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体的内容. */
    private T data;

    /** 成功标记 **/
    private Boolean success;
    
    /** 分页信息 **/
    private T count;
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public T getCount() {
		return count;
	}

	public void setCount(T count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + ", success=" + success + ", totalSize="
				+ count + "]";
	}


}
