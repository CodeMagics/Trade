package com.skyfin.bean;
import java.util.List;
/**
 * @author yu
 *
 */
public class Relation {
	private int id;
	private String relaUserId;
	private String relaCommId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRelaUserId(){
		return relaUserId;
	}
	public void setRelaUserId(String relaUserId){
		this.relaUserId=relaUserId;
	}
	public String getRelaCommId(){
		return relaCommId;
	}
	public void setRelaCommId(String relaCommId){
        this.relaCommId=relaCommId;
	}
}
