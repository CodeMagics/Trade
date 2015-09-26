package com.skyfin.bean;

import java.util.List;
/*
 * @author Shan
 * time:2015/9/26
 * Commdity bean
 */

public class Commodity {
	private int id;
	private String commNum;
	private String commTitle;
	private String commIntro;
	private int commPrice;
	private String commPic;
	private String commType;
	public String getCommType() {
		return commType;
	}
	public void setCommType(String commType) {
		this.commType = commType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommNum() {
		return commNum;
	}
	public void setCommNum(String commNum) {
		this.commNum = commNum;
	}
	public String getCommTitle() {
		return commTitle;
	}
	public void setCommTitle(String commTitle) {
		this.commTitle = commTitle;
	}
	public String getCommIntro() {
		return commIntro;
	}
	public void setCommIntro(String commIntro) {
		this.commIntro = commIntro;
	}
	public int getCommPrice() {
		return commPrice;
	}
	public void setCommPrice(int commPrice) {
		this.commPrice = commPrice;
	}
	public String getCommPic() {
		return commPic;
	}
	public void setCommPic(String commPic) {
		this.commPic = commPic;
	}

}
