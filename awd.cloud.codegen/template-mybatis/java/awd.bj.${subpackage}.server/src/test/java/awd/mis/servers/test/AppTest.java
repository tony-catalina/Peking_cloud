<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.test;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import awd.mis.servers.entity.${className}Entity;
import awd.mis.starter.springboot.convert.FastJsonHttpMessageConverter;
import awd.mis.test.SimpleWebApplicationTests;

/**
 * Unit test for simple App.
 */
public class AppTest  extends SimpleWebApplicationTests
{
	@Autowired
    private FastJsonHttpMessageConverter fastJsonHttpMessageConverter;

    @Test
    public void testCrud() throws Exception {
    	${className}Entity entity=new ${className}Entity();
    	entity.setJsbh("123456789");
    	entity.setState("R2");
    	entity.setCreator("测试");
    	entity.setCreatetime(Calendar.getInstance().getTime());
    	String requestBody = JSON.toJSONString(entity);
    	System.out.println(requestBody);
        JSONObject result = testPost("/${classNameLower}").setUp(setup -> setup.contentType(MediaType.APPLICATION_JSON).content(requestBody)).exec().resultAsJson();
        Assert.assertEquals(200, result.get("status"));        
        String id = result.getString("result");
        Assert.assertNotNull(id);
        entity.setId(id);
        // test get data
        result = testGet("/${classNameLower}/" + id).exec().resultAsJson();
        entity = result.getObject("result", entityFactory.getInstanceType(${className}Entity.class));

        Assert.assertEquals(200, result.get("status"));
        Assert.assertNotNull(result.getJSONObject("result"));

        Assert.assertEquals(fastJsonHttpMessageConverter.converter(entity),
                fastJsonHttpMessageConverter.converter(result.getObject("result", entityFactory.getInstanceType(${className}Entity.class))));
        //todo 修改测试属性
        ${className}Entity newEntity = entityFactory.newInstance(${className}Entity.class);
        newEntity.setUpdator("test");

        result = testPut("/${classNameLower}/" + id)
                .setUp(setup ->
                        setup.contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(newEntity)))
                .exec().resultAsJson();
        Assert.assertEquals(200, result.get("status"));

        result = testGet("/${classNameLower}/" + id).exec().resultAsJson();
        result = result.getJSONObject("result");
        Assert.assertNotNull(result);

        result = testDelete("/${classNameLower}/" + id).exec().resultAsJson();
        Assert.assertEquals(200, result.get("status"));

        result = testGet("/${classNameLower}/" + id).exec().resultAsJson();
        Assert.assertEquals(404, result.get("status"));
    }
}
