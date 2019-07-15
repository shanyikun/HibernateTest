package com.syk.service;

import java.util.List;

import com.syk.dao.UserInfoDao;
import com.syk.ormentity.UserInfo;
import com.syk.ormentity.UserInfo1;

public class UserInfoService {
	private UserInfoDao userInfoDao = new UserInfoDao();
	
	public List<UserInfo> getAllUser(){
		return userInfoDao.getUserList();
	}
	
	public Integer addUser(String name, String password) {
		return userInfoDao.addUser(name, password);
	}
	
	public void deleteOneUser(Integer id) {
		userInfoDao.deleteOneUser(id);
	}
	
	public void updateOneUser(Integer id) {
		userInfoDao.updateOneUser(id);
	}
	
	@SuppressWarnings("rawtypes")
	public List queryByNameAndPassword(String name, String password) {
		return userInfoDao.queryByNameAndPassword(name, password);
	}
	
	public Integer updateUserByHQL(String oldname, String newname) {
		return userInfoDao.updateUserByHQL(oldname, newname);
	}
	
	public Integer deleteUserByHQL(String name) {
		return userInfoDao.deleteUserByHQL(name);
	}
	
	public List getUserBySQL(String name) {
		return userInfoDao.getUserBySQL(name);
	}
	
	public List<UserInfo1> queryBothTableByHql(){
		return userInfoDao.queryBothTableByHql();
	}
	
	public Object queryBothTableBySQL(){
		return userInfoDao.queryBothTableBySQL();
	}
}
