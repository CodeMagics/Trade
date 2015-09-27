package com.skyfin.dao;

import java.util.List;

import com.skyfin.bean.Album;


public interface AlbumDao {
	public List<String> selectByCommId(String commNum);
	public boolean insertPic (String commNum);
	public boolean insertAlbum (Album abl);

}
