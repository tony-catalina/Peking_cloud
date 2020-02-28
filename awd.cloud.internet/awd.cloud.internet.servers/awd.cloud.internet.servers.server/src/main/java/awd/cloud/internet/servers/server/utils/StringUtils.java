package awd.cloud.internet.servers.server.utils;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang.StringUtils{

    private static final Pattern validNumber = Pattern.compile("^[\\d\\.]+$|^0x[\\dA-Fa-f]+$");
    private static final Pattern validStr = Pattern.compile("[\\w\\d\\u4E00-\\u9FA5]+");
    private static final Pattern valueTypePat = Pattern.compile("(\\d+\\.?\\d*)([ilfdD])");
    private static final Pattern validPhone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    private static final Pattern validEmail = Pattern.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*"
            + "@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
    private static char punct[] = { ',', '.', '!', '?', ';', ':', '，', '。', '！', '？', '；', '：', '、' };

   /**
     * 验证邮箱
     *
     * @param email
     * @return
     */

    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码，11位数字，1开通，第二位数必须是3456789这些数字之一 *
     * 
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            // Pattern regex =
            // Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Pattern regex = Pattern.compile("^1[345789]\\d{9}$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }


    /**
     * 将字符串中的标点符号过滤掉
     * 
     * @param str
     * @return
     */
    public static String TrimPunctuation(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            boolean need_filter = false;
            for (int j = 0; j < punct.length; ++j) {
                if (punct[j] == str.charAt(i)) {
                    need_filter = true;
                    break;
                }
            }

            if (!need_filter) {
                result.append(str.charAt(i));
            }
        }

        return result.toString();
    }

    /**
     * 判断是否包含标点
     */
    public static boolean ContainsPunctuation(String str) {
        for (int i = 0; i < str.length(); ++i) {
            for (int j = 0; j < punct.length; ++j) {
                if (punct[j] == str.charAt(i)) {
                    return true;
                }
            }
        }

        return false;
    }

    // 将日期简单格式化
    public static String FormatDateInFormat(Date date, String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(date);
    }

    public static String FormatDateTime(Date date) {
        return FormatDateInFormat(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String FormatDateTime(Calendar date) {
        return FormatDateTime(date.getTime());
    }

    public static String FormatDate(Date date) {
        return FormatDateInFormat(date, "yyyy-MM-dd");
    }

    public static String FormatDateForQuery(Date date) {
        return FormatDateInFormat(date, "yyyyMMdd");
    }

    public static String FormatDate(Calendar date) {
        return FormatDateInFormat(date.getTime(), "yyyy-MM-dd");
    }

    public static String CalendarToString(Calendar date) {
        return FormatDateInFormat(date.getTime(), "yyyy-MM-dd");
    }

    /**
     * 将时间字符串解析为calendar
     */
    public static Calendar StringToCalender(String str_date) {
        Date date = StringToDate(str_date);
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal;
    }

    /**
     * 使用指定日期格式解析日期字符串
     *
     * @param dateStr
     *            日期字符串
     * @param dateFormat
     *            日期格式，SimpleDateFormat能够识别的格式
     * @return 成功返回Date，失败返回null
     */
    public static Date StringToDateInFormat(String dateStr, String dateFormat) {
        SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
        try {
            return fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date StringToDate(String date) {
        return StringToDateInFormat(date, "yyyy-MM-dd");
    }

    public static Date StringToDateInQuery(String date) {
        return StringToDateInFormat(date, "yyyyMMdd");
    }

    // 将时长格式化为时分秒的样式
    public static String FormatTimeDuration(int sec) {
        return String.format("%02d:%02d", sec / 60, sec % 60);
    }

    // 对字符串数组进行trim
    public static String[] TrimStringArray(String[] arr) {
        assert (arr != null);
        Vector<String> lst = new Vector<String>();
        for (int i = 0; i < arr.length; ++i) {
            String item = arr[i];
            item.trim();
            if (!item.isEmpty())
                lst.add(item);
        }

        if (lst.size() == 0) {
            return null;
        } else {
            String[] str_arr = new String[lst.size()];
            for (int i = 0; i < lst.size(); ++i) {
                str_arr[i] = lst.get(i);
            }
            return str_arr;
        }
    }

    // 对get方式过来的中文参数的乱码进行转码
    public static String StringToUTF8(String param) throws Exception {
        return new String(param.getBytes("ISO-8859-1"), "UTF-8");
    }

    // 判断字符串是否为空
    // 包括是否为null，是否为空字符串，过滤完空格后是否为空字符串
    public static boolean IsAbsEmpty(final String str) {
        if (str == null) {
            return true;
        } else {
            return str.trim().isEmpty();
        }
    }

    // 判断字符串是否为空
    // 包括是否为null，是否为空字符串，过滤完空格后是否为空字符串
    public static boolean IsAbsEmpty(final StringBuffer str) {
        if (str == null) {
            return true;
        } else {
            return IsAbsEmpty(str.toString());
        }
    }

    // 判断字符串是否为空
    // 包括是否为null，是否为空字符串，过滤完空格后是否为空字符串
    public static boolean IsAbsEmpty(final StringBuilder str) {
        if (str == null) {
            return true;
        } else {
            return IsAbsEmpty(str.toString());
        }
    }

    /**
     * 将字符串值转换为指定类型
     *
     * @param val
     *            字符串字面值
     * @param type
     *            目标类型 支持包括int, long, date, string, float, double
     * @return 转换后的对象
     */
    public static Object ConvertToType(String val, String type) {
        Matcher mat = valueTypePat.matcher(val);
        boolean matRet = mat.matches();
        if (matRet) {
            val = mat.group(1);
        }

        try {
            if (type.matches("i|int")) {
                return Integer.valueOf(val);
            } else if (type.matches("l|long")) {
                return Long.valueOf(val);
            } else if (type.matches("s|string")) {
                return val;
            } else if (type.matches("f|float")) {
                return Float.valueOf(val);
            } else if (type.matches("d|double")) {
                return Double.valueOf(val);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String ToShortType(String type) {
        if (type.matches("i|int")) {
            return "i";
        } else if (type.matches("l|long")) {
            return "l";
        } else if (type.matches("s|string")) {
            return "";
        } else if (type.matches("f|float")) {
            return "f";
        } else if (type.matches("d|double")) {
            return "d";
        } else if (type.matches("D|date")) {
            return "D";
        } else {
            return null;
        }
    }

    /**
     * 通过后缀表示类型，进行转换 后缀类型，包括i,l,f,d,D
     */
    public static Object ConvertToType(String val) {
        Matcher mat = valueTypePat.matcher(val);
        boolean matRet = mat.matches();

        if (!matRet) {
            // 没匹配则之间返回原始字符串
            return val;
        }

        try {
            String type = mat.group(2);
            String value = mat.group(1);

            if (type.equals("i")) {
                return Integer.valueOf(value);
            } else if (type.equals("l")) {
                return Long.valueOf(value);
            } else if (type.equals("f")) {
                return Float.valueOf(value);
            } else if (type.equals("d")) {
                return Double.valueOf(value);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据后缀判断类型
     */
    public static String DetectType(String val) {
        Matcher mat = valueTypePat.matcher(val);
        boolean matRet = mat.matches();
        if (!matRet)
            return null;

        return mat.group(2);
    }

    /**
     * 从字符串形式的年-月-日中提取出 年-月
     *
     * @param fileDate
     *            原始日期字符串
     * @return 返回的年-月字符串
     */
    public static String ExtractMonth(String fileDate) {
        String[] date = fileDate.split("-");
        assert date.length >= 3;
        return date[0] + "-" + date[1];
    }

    public static String ExtractMonth(Calendar date) {
        return String.format("%4d-%02d", date.get(Calendar.YEAR), date.get(Calendar.MONTH));
    }

    /**
     * 从字符串中解析出列表，分隔符支持;,: 该方法会将空节点剔除掉
     *
     * @param str
     *            字符串
     * @return 解析后的列表
     */
    public static List<String> ParseList(String str) {
        final String splitter = "[;,:]";
        return ParseList(splitter);
    }

    public static List<String> ParseList(String str, String splitter, boolean trimEmpty) {
        List<String> lst = new Vector<String>();
        String[] splits = str.split(splitter, -1);
        for (String item : splits) {
            if (trimEmpty && IsAbsEmpty(item))
                continue;
            lst.add(item);
        }

        return lst;
    }

    /**
     * 将字符串列表，使用分号分割
     *
     * @param lst
     *            字符串列表
     * @return 返回整合后的字符串
     */
    public static String CollectionToStringWithSemicolon(Collection<? extends Object> lst) {
        StringBuilder str = new StringBuilder();
        for (Object item : lst) {
            str.append(item.toString()).append(";");
        }
        if (str.length() > 0)
            str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    /**
     * 从字符串中解析出map key和value之间使用:分割，节点之间使用;分割
     *
     * @param str
     *            原始字符串
     * @return 解析后的map
     */
    public static Map<String, String> ParseMap(String str) {
        return ParseMap(str, ";", ":");
    }

    /**
     * 从字符串中解析出map 可指定分割符
     */
    public static Map<String, String> ParseMap(String str, String item_split, String kv_split) {
        String[] splits = str.split(item_split);
        Map<String, String> map = new HashMap<String, String>();
        for (String item : splits) {
            String[] key_val = item.split(kv_split);
            if (key_val.length == 1) {
                map.put(key_val[0], "");
            } else if (key_val.length >= 2) {
                map.put(key_val[0].trim(), key_val[1].trim());
            }
        }
        return map;
    }

    /**
     * 从字符串中解析出map key和value之间使用:分割，节点之间使用;分割
     *
     * @param str
     *            原始字符串
     * @return 解析后的map
     */
    public static Map<String, Integer> ParseIntegerMap(String str) {
        String[] splits = str.split("[;,]");
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String item : splits) {
            String[] key_val = item.trim().split(":");
            if (key_val.length != 2)
                continue;

            map.put(key_val[0].trim(), Integer.valueOf(key_val[1].trim()));
        }
        return map;
    }

    /**
     * 将map对象转为字符串，节点之间使用;，key val之间使用:
     */
    public static String MapToStringWithSemicolon(Map<String, ? extends Object> map) {
        return MapToString(map, ";", ":");
    }

    /**
     * 将map转换为字符串，分隔符可指定
     *
     * @param item_split
     *            节点之间的字符串
     * @param kv_split
     *            键值对质检的字符串
     */
    public static String MapToString(Map<String, ? extends Object> map, String item_split, String kv_split) {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, ? extends Object> item : map.entrySet()) {
            str.append(item.getKey()).append(kv_split).append(item.getValue()).append(item_split);
        }
        if (str.length() > 0)
            str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    /**
     * 用于将两条存在一一对应关系的字符串，解析为map
     */
    public static Map<String, Integer> ParseIntegerMapFromPairString(String str_key, String str_val) {
        List<String> key_lst = ParseList(str_key);
        List<String> val_lst = ParseList(str_val);
        if (key_lst.size() != val_lst.size())
            return null;

        Map<String, Integer> final_map = new HashMap<String, Integer>();
        for (int i = 0; i < key_lst.size(); ++i) {
            final_map.put(key_lst.get(i), Integer.valueOf(val_lst.get(i)));
        }
        return final_map;
    }

    public static List<Integer> ParseIntegerList(String str) {
        List<String> lst = ParseList(str);
        List<Integer> int_lst = new ArrayList<Integer>();
        for (String item : lst) {
            int_lst.add(Integer.valueOf(item));
        }

        return int_lst;
    }

    /**
     * 使用指定的分隔符合并两个字符串 分隔符会和第一个字符串末尾、第二个字符串的开头出现的对应字符进行合并
     *
     * @param one
     *            第一个字符串
     * @param two
     *            第二个字符串
     * @param splitter
     *            分隔符
     * @return 合并后的字符串
     */
    public static String CombineWithSplitter(String one, String two, String splitter) {
        StringBuilder finalStr = new StringBuilder();
        int splitterPos = one.lastIndexOf(splitter);
        if ((splitterPos != -1) && (splitterPos + splitter.length() == (one.length()))) {
            finalStr.append(one.substring(0, splitterPos));
        } else {
            finalStr.append(one);
        }

        if (finalStr.length() > 0)
            finalStr.append(splitter);

        splitterPos = two.indexOf(splitter);
        if (splitterPos == 0) {
            finalStr.append(two.substring(splitterPos + splitter.length()));
        } else {
            finalStr.append(two);
        }

        return finalStr.toString();
    }

    /**
     * 前缀补充
     *
     * @param prefix
     *            前缀字符串
     * @param str
     *            原始字符串
     * @return 补充哦功能后的字符串
     */
    public static String PrependPrefix(String prefix, String str) {
        return prefix + str;
    }

    /**
     * 删除前缀
     */
    public static String RemovePrefix(String prefix, String str) {
        int index = str.indexOf(prefix);
        if (index != 0) {
            return str.substring(index + prefix.length());
        } else {
            return str;
        }
    }

    /**
     * 生成固定长度的随即字符串
     *
     * @param length
     *            字符串长度
     */
    public static String RandomString(int length) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            str.append((char) ('A' + (int) (Math.random() * 26)));
        }

        return str.toString();
    }

    /**
     * 判断是否包含非法字符、特殊字符
     */
    public static boolean IsValidChar(String str) {
        return validStr.matcher(str).matches();
    }

    /**
     * 路径统一格式化
     */
    public static String NormalizePath(String path) {
        return path.replace("\\", "/");
    }

    /**
     * 获取文件不带扩展名的路径
     *
     * @param source
     *            文件路径
     * @return 文件名
     */
    public static String GetFileNameWithoutExt(String source) {
        String fileName = new File(source).getName();
        int pos = fileName.indexOf(".");
        if (pos == -1) {
            return fileName;
        } else {
            return fileName.substring(0, pos);
        }
    }

    /**
     * 判断字符串是否是数字
     *
     * @param input
     *            字符串
     */
    public static boolean IsValidInteger(String input) {
        try {
            Integer.valueOf(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点型数字
     *
     * @param input
     *            字符串
     */
    public static boolean IsValidFloat(String input) {
        try {
            Float.valueOf(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 验证邮箱格式是否合法
     * 
     * @param email
     *            邮箱名
     * @return 是否合法
     */
    public static boolean IsValidEmail(String email) {
        return validEmail.matcher(email).matches();
    }

    /**
     * 验证手机号是否合法
     * 
     * @param phone
     *            手机号
     * @return 是否合法
     */
    public static boolean IsValidPhone(String phone) {
        return validPhone.matcher(phone).matches();
    }

    /**
     * 生成组合路径
     *
     * @param base
     *            父目录
     * @param sub
     *            子路径
     */
    public static String CombinePath(String base, String sub) {
        // 如果是根目录,直接合并
        if (base.equals("/")) {
            return base + sub;
        }
        return CombineWithSplitter(base, sub, "/");
    }

    /**
     * 全角转半角
     *
     * @param str
     *            字符串
     */
    public static String FullToHalf(String str) {
        StringBuffer sb = new StringBuffer();
        try {
            for (int i = 0; i < str.length(); i++) {
                if (str.substring(i, i + 1).equals("　") || str.substring(i, i + 1).equals(" ")) {
                    sb.append(" ");
                    continue;
                }
                byte[] temp = str.substring(i, i + 1).getBytes("unicode");
                if (temp[2] == -1) {
                    temp[3] = (byte) (temp[3] + 32);
                    temp[2] = 0;
                }
                sb.append(new String(temp, "unicode"));
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }

    /**
     * 小数转百分数
     */
    public static String DecimalToPercent(double num) {
        return String.format("%.2f", (double) (Math.round(num * 10000) / 100.f));
    }

    /**
     * 生成唯一令牌
     */
    public static String UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 清理掉前后的空白字符
     */
    public static Object TrimBlank(String version) {
        return version.replaceAll("^\\s+|\\s+$", "");
    }

    public static boolean IsTrue(String value) {
        return value.toLowerCase().matches("1|true");
    }

    public static boolean IsFalse(String value) {
        return value.toLowerCase().matches("0|false");
    }

    /**
     * 判断str是否匹配regExp正则表达式
     * 
     * @param regExp
     *            正则表达式
     * @param str
     *            字符串
     * @return 是否匹配
     */
    public static boolean IsMatch(String regExp, String str) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher mat = pattern.matcher(str);
        return mat.matches();
    }


    /**
     * 转换为字节数组
     *
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes) {
        return new String(bytes, Charset.forName("UTF-8"));
    }
 
    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            return str.getBytes(Charset.forName("UTF-8"));
        } else {
            return null;
        }
    }
 
    /**
     * 一次性判断多个或单个对象为空。
     *
     * @param objects
     * @return 只要有一个元素为Blank，则返回true
     * @author zhou-baicheng
     */
    public static boolean isBlank(Object... objects) {
        Boolean result = false;
        for (Object object : objects) {
            if (null == object || "".equals(object.toString().trim())
                    || "null".equals(object.toString().trim())) {
                result = true;
                break;
            }
        }
        return result;
    }
 
    public static String getRandom(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val.toLowerCase();
    }
 
    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
 
    /**
     * 一次性判断多个或单个对象不为空。
     *
     * @param objects
     * @return 只要有一个元素不为Blank，则返回true
     * @author zhou-baicheng
     */
    public static boolean isNotBlank(Object... objects) {
        return !isBlank(objects);
    }
 
    public static boolean isBlank(String... objects) {
        Object[] object = objects;
        return isBlank(object);
    }
 
    public static boolean isNotBlank(String... objects) {
        Object[] object = objects;
        return !isBlank(object);
    }
 
    public static boolean isBlank(String str) {
        Object object = str;
        return isBlank(object);
    }
 
    public static boolean isNotBlank(String str) {
        Object object = str;
        return !isBlank(object);
    }
    public static boolean isNullOrEmpty(String str) {
    	Object object = str;
    	return isBlank(object);
    }
 
    /**
     * 判断一个字符串在数组中存在几个
     *
     * @param baseStr
     * @param strings
     * @return
     */
    public static int indexOf(String baseStr, String[] strings) {
 
        if (null == baseStr || baseStr.length() == 0 || null == strings) {
            return 0;
        }
        int i = 0;
        for (String string : strings) {
            boolean result = baseStr.equals(string);
            i = result ? ++i : i;
        }
        return i;
    }
 
 
    public static String trimToEmpty(Object str) {
        return (isBlank(str) ? "" : str.toString().trim());
    }
 
    /**
     * 把Map转换成get请求参数类型,如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
     *
     * @param map
     * @return
     */
    public static String mapToGet(Map<? extends Object, ? extends Object> map) {
        String result = "";
        if (map == null || map.size() == 0) {
            return result;
        }
        Set<? extends Object> keys = map.keySet();
        for (Object key : keys) {
            result += ((String) key + "=" + (String) map.get(key) + "&");
        }
 
        return isBlank(result) ? result : result.substring(0, result.length() - 1);
    }
 
    /**
     * 把一串参数字符串,转换成Map 如"?a=3&b=4" 转换为Map{a=3,b=4}
     *
     * @param args
     * @return
     */
    public static Map<String, ? extends Object> getToMap(String args) {
        if (isBlank(args)) {
            return null;
        }
        args = args.trim();
        //如果是?开头,把?去掉
        if (args.startsWith("?")) {
            args = args.substring(1, args.length());
        }
        String[] argsArray = args.split("&");
 
        Map<String, Object> result = new HashMap<String, Object>();
        for (String ag : argsArray) {
            if (!isBlank(ag) && ag.indexOf("=") > 0) {
 
                String[] keyValue = ag.split("=");
                //如果value或者key值里包含 "="号,以第一个"="号为主 ,如  name=0=3  转换后,{"name":"0=3"}, 如果不满足需求,请勿修改,自行解决.
 
                String key = keyValue[0];
                String value = "";
                for (int i = 1; i < keyValue.length; i++) {
                    value += keyValue[i] + "=";
                }
                value = value.length() > 0 ? value.substring(0, value.length() - 1) : value;
                result.put(key, value);
 
            }
        }
 
        return result;
    }
 
    /**
     * 转换成Unicode
     *
     * @param str
     * @return
     */
    public static String toUnicode(String str) {
        String as[] = new String[str.length()];
        String s1 = "";
        for (int i = 0; i < str.length(); i++) {
            int v = str.charAt(i);
            if (v >= 19968 && v <= 171941) {
                as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
                s1 = s1 + "\\u" + as[i];
            } else {
                s1 = s1 + str.charAt(i);
            }
        }
        return s1;
    }
 
    /**
     * 合并数据
     *
     * @param v
     * @return
     */
    public static String merge(Object... v) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < v.length; i++) {
            sb.append(v[i]);
        }
        return sb.toString();
    }
 
    /**
     * 字符串转urlcode
     *
     * @param value
     * @return
     */
    public static String strToUrlcode(String value) {
        try {
            value = java.net.URLEncoder.encode(value, "utf-8");
            return value;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
 
    /**
     * urlcode转字符串
     *
     * @param value
     * @return
     */
    public static String urlcodeToStr(String value) {
        try {
            value = java.net.URLDecoder.decode(value, "utf-8");
            return value;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
 
    /**
     * 判断字符串是否包含汉字
     *
     * @param txt
     * @return
     */
    public static Boolean containsCN(String txt) {
        if (isBlank(txt)) {
            return false;
        }
        for (int i = 0; i < txt.length(); i++) {
 
            String bb = txt.substring(i, i + 1);
 
            boolean cc = Pattern.matches("[\u4E00-\u9FA5]", bb);
            if (cc) {
                return cc;
            }
        }
        return false;
    }
 
    /**
     * 判断字符串是否是英文字母
     *
     * @param txt
     * @return
     */
	public static boolean isEnglishChars(String str) {
		boolean isChar = false;
		if (str != null && str.trim().matches("^[a-zA-Z]+$")) {
			isChar = true;
		}
		return isChar;
	}
    
    /**
     * 去掉HTML代码
     *
     * @param news
     * @return
     */
    public static String removeHtml(String news) {
        String s = news.replaceAll("amp;", "").replaceAll("<", "<").replaceAll(">", ">");
 
        Pattern pattern = Pattern.compile("<(span)?\\sstyle.*?style>|(span)?\\sstyle=.*?>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(s);
        String str = matcher.replaceAll("");
 
        Pattern pattern2 = Pattern.compile("(<[^>]+>)", Pattern.DOTALL);
        Matcher matcher2 = pattern2.matcher(str);
        String strhttp = matcher2.replaceAll(" ");
 
 
        String regEx = "(((http|https|ftp)(\\s)*((\\:)|：))(\\s)*(//|//)(\\s)*)?"
                + "([\\sa-zA-Z0-9(\\.|．)(\\s)*\\-]+((\\:)|(:)[\\sa-zA-Z0-9(\\.|．)&%\\$\\-]+)*@(\\s)*)?"
                + "("
                + "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])"
                + "|([\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*)*[\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*[\\sa-zA-Z]*"
                + ")"
                + "((\\s)*(\\:)|(：)(\\s)*[0-9]+)?"
                + "(/(\\s)*[^/][\\sa-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*";
        Pattern p1 = Pattern.compile(regEx, Pattern.DOTALL);
        Matcher matchhttp = p1.matcher(strhttp);
        String strnew = matchhttp.replaceAll("").replaceAll("(if[\\s]*\\(|else|elseif[\\s]*\\().*?;", " ");
 
 
        Pattern patterncomma = Pattern.compile("(&[^;]+;)", Pattern.DOTALL);
        Matcher matchercomma = patterncomma.matcher(strnew);
        String strout = matchercomma.replaceAll(" ");
        String answer = strout.replaceAll("[\\pP‘’“”]", " ")
                .replaceAll("\r", " ").replaceAll("\n", " ")
                .replaceAll("\\s", " ").replaceAll("　", "");
 
 
        return answer;
    }
 
    /**
     * 把数组的空数据去掉
     *
     * @param array
     * @return
     */
    public static List<String> array2Empty(String[] array) {
        List<String> list = new ArrayList<String>();
        for (String string : array) {
            if (StringUtils.isNotBlank(string)) {
                list.add(string);
            }
        }
        return list;
    }
 
    /**
     * 把数组转换成set
     *
     * @param array
     * @return
     */
    public static Set<?> array2Set(Object[] array) {
        Set<Object> set = new TreeSet<Object>();
        for (Object id : array) {
            if (null != id) {
                set.add(id);
            }
        }
        return set;
    }
 
    /**
     * serializable toString
     *
     * @param serializable
     * @return
     */
    public static String toString(Serializable serializable) {
        if (null == serializable) {
            return null;
        }
        try {
            return (String) serializable;
        } catch (Exception e) {
            return serializable.toString();
        }

    }
}