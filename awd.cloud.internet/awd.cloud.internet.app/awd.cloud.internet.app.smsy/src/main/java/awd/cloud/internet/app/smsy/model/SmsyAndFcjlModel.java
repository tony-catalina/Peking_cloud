/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.app.smsy.model;

import awd.cloud.internet.app.smsy.utils.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class SmsyAndFcjlModel implements Model{
	
	//columns START
	
	private SmsyModel smsyModel;

	private FcjlModel fcjlModel;

	public SmsyModel getSmsyModel() {
		return smsyModel;
	}

	public void setSmsyModel(SmsyModel smsyModel) {
		this.smsyModel = smsyModel;
	}

	public FcjlModel getFcjlModel() {
		return fcjlModel;
	}

	public void setFcjlModel(FcjlModel fcjlModel) {
		this.fcjlModel = fcjlModel;
	}
	
	 
}

