package awd.cloud.internet.servers.server.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 字典类型转换
 * @Author WS
 * @Date 2019-07-10 16:26
 */
public class DictionaryMap {
    //单位类型字典定义
    public final static Map<String, String> SMSYDWLX_MAP = new HashMap<String, String>() {{
        put("0", "办案单位");
        put("1", "监管单位");
    }};
    //阶段字典定义
    public final static Map<String, String> PHASE_MAP = new HashMap<String, String> () {{
        put("1", "等待审核");
        put("2", "法律文书不全");
        put("3", "可以上门");
        put("4", "等待收押");
        put("5", "收押入所");
        put("6", "取消上门");
    }};
    //发车字典定义
    public final static Map<String, String> FCZT_MAP = new HashMap<String, String> () {{
    	put("1", "已发车");
    	put("2", "延迟发车");
    	put("3", "已完成");
    	put("4", "已取消");
    }};
}
