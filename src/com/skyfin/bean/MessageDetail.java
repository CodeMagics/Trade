package com.skyfin.bean;

import java.util.List;

public class MessageDetail {
	private User user;
	public List<String> getMessList() {
		return messList;
	}

	public void setMessList(List<String> messList) {
		this.messList = messList;
	}

	private List<String> messList;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
