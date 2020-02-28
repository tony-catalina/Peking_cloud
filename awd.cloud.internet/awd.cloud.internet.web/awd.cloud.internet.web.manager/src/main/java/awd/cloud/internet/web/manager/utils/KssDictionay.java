/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.web.manager.utils;

import java.util.Map;






/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class KssDictionay  {
	
	private Map<String,Object> map;
	private static KssDictionay dictionary;
	private KssDictionay() {}
	


	public Map<String, Object> getMap() {
		return map;
	}



	public void setMap(Map<String, Object> map) {
		this.map = map;
	}



		public static synchronized KssDictionay getDictionary() {
	        if (dictionary == null) {
	        	dictionary = new KssDictionay();
	        }
	        return dictionary;
	    }
		
}

