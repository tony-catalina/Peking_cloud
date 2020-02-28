package awd.cloud.internet.app.smsy.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class GeturlPic {
    
	/*
    public static void main(String[] args) throws Exception {  
        //new一个URL对象  
        URL url = new URL("http://www.gz135.cn/data/attachment/forum/201702/13/165605xyayykq5vy4h81vy.jpg");  
        //打开链接  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        //设置请求方式为"GET"  
        conn.setRequestMethod("GET");  
        //超时响应时间为5秒  
        conn.setConnectTimeout(5 * 1000);  
        //通过输入流获取图片数据  
        InputStream inStream = conn.getInputStream();  
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
        byte[] data = readInputStream(inStream);  
        //new一个文件对象用来保存图片，默认保存当前工程根目录  
        File imageFile = new File("pic20170419.jpg");  
        //创建输出流  
        FileOutputStream outStream = new FileOutputStream(imageFile);  
        //写入数据  
        outStream.write(data);  
        //关闭输出流  
        outStream.close();  
    }  
    */
    
    public static String getpic(String imagepath,String port,String id) {
    	String path=imagepath+id+".jpg";
    	System.err.println(path);
    	File file=new File(path);
    	if(judeFileExists(file)) {
    		return imagepath+id+".jpg"; 
    	}else {
    		 URL url;
			try {
				url = new URL("http://localhost:"+port+"/getPic.jpg?id="+id);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");  
		        conn.setConnectTimeout(60 * 1000);  
		        InputStream inStream = conn.getInputStream();  
		        byte[] data = readInputStream(inStream);  
		        File imageFile = new File(path);
		        imageFile.createNewFile();
		        FileOutputStream outStream = new FileOutputStream(imageFile);  
		        outStream.write(data);  
		        outStream.close();		        
			} catch (Exception e) {
				e.printStackTrace();
			}
    		return "/user/images/"+id+".jpg"; 
    	}
    }
    public static byte[] fileConvertToByteArray(File file) {
        byte[] data = null;
 
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
 
            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
 
            data = baos.toByteArray();
 
            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return data;
    }
    
 // 判断文件是否存在
    public static boolean judeFileExists(File file) {

        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    // 判断文件夹是否存在
    public static boolean judeDirExists(File file) {

        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            } else {
                return false;
            }
        } 
        return false;
    }
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }  

}
