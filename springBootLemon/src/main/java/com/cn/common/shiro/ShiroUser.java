package com.cn.common.shiro;

import io.swagger.annotations.ApiModelProperty;

public class ShiroUser {
	
	@ApiModelProperty(value="用户ID")
	private Integer userId;
	@ApiModelProperty(value="用户名")
	private String userName;
	@ApiModelProperty(value="用户中文名")
	private String nameCn;
	@ApiModelProperty(value="用户英文名")
	private String nameEn;
	@ApiModelProperty(value="E-mail")
	private String email;
	@ApiModelProperty(value="地址")
	private String address;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNameCn() {
		return nameCn;
	}
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
