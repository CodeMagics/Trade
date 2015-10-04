package com.skyfin.bean;

import java.util.ArrayList;
import java.util.List;

public class CommodityDetail {
	private User user;
	private String typeName;
	private List<Album> album;
	private List<String>picPath=new ArrayList<String>();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Commodity getCommdity() {
		return commdity;
	}
	public void setCommdity(Commodity commdity) {
		this.commdity = commdity;
	}
	public List<Album> getAlbum() {
		return album;
	}
	public void setAlbum(List<Album> album) {
		this.album = album;
	}
	private Commodity  commdity;

	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public List<String> getPicPath() {
		return picPath;
	}
	public void setPicPath(List<String> picPath) {
		this.picPath = picPath;
	}


}
