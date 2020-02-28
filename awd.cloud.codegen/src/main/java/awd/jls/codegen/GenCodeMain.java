package awd.jls.codegen;

import cn.org.rapid_framework.generator.GeneratorFacade;

public class GenCodeMain 
{
    public static void main( String[] args )
    {
    	try {
    		GeneratorFacade g = new GeneratorFacade();
    		//g.printAllTableNames();				//打印数据库中的表名称		
    		g.deleteOutRootDir();							//删除生成器的输出目录
    		//g.generateByTable("aqjc".toUpperCase(), "template-mybatis");

    		//g.generateByTable("CLASSFIC".toUpperCase(),"template-server");	//通过数据库表生成文件,template为模板的根目
    		//g.generateByAllTable("template");
    		g.generateByAllTable("template-mybatis");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}
