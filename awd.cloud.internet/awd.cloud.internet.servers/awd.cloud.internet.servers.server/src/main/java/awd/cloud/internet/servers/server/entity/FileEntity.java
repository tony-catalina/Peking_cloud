/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.internet.servers.server.entity;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class FileEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "File";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_FILE = "文件";
	public static final String ALIAS_FILEPATH = "文件地址";
	public static final String ALIAS_UUID = "UUID";
	public static final String ALIAS_FILENAME = "SUFFIX";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

	//columns START

	@NotNull(message="id不能为空",groups=CreateGroup.class)
    @Length(max=23,message="id最大长度23位" ,groups=CreateGroup.class)
	private String id;

	private byte[] file;

	@NotNull(message="文件地址不能为空",groups=CreateGroup.class)
    @Length(max=255,message="文件地址最大长度255位" ,groups=CreateGroup.class)
	private String filepath;

	@NotNull(message="UUID不能为空",groups=CreateGroup.class)
	@Length(max=30,message="UUID最大长度30位" ,groups=CreateGroup.class)
	private String uuid;
	@NotNull(message="SUFFIX不能为空",groups=CreateGroup.class)
	@Length(max=20,message="SUFFIX最大长度20位" ,groups=CreateGroup.class)
	private String suffix;

	//columns END


	public FileEntity(){
	}

	public FileEntity(
		String id
	){
		this.id = id;
	}



	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}


	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] value) {
		this.file = value;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String value) {
		this.filepath = value;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String value) {
		this.uuid = value;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}

