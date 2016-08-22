package com.maomao.project.qfix;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

@SuppressWarnings("serial")
public class Repairs extends BmobObject implements Serializable {

	private String title;     //报修的提交标题  ex：南苑24#320#
	private String username;   //登录账号的用户名 ex：admin  获取admin的用户保修信息 开启交互
	private String phonenum;    //报修的电话
	private String detailed;    //用于保存用户的报修详情；
	private String state;       //用于保存报修事件的状态
	private String address;     //用于保存用户的详细信息   ex：济南市济南大学南苑24#319
	private BmobFile photo;
	private String name;
	private String lat;
	private String lon;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public BmobFile getPhoto() {
		return photo;
	}

	public void setPhoto(BmobFile photo) {
		this.photo = photo;
	}

	public String getState() {
		return state;
	}

	public String getDetailed() {
		return detailed;
	}

	public String getTitle() {
		return title;
	}

	public String getUsername() {
		return username;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	@Override
	public String getCreatedAt() {
		return super.getCreatedAt();
		//获取到表的创建时间
	}

}
