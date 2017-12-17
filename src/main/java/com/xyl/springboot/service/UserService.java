package com.xyl.springboot.service;

import com.xyl.springboot.domain.User;

public interface UserService {
	
	/**
	 * 新增用户
	 * @param name
	 * @param age
	 */
	void create(String name,Integer age);
	
	/**
	 * 根据name删除一个用户
	 * @param name
	 */
	void deleteByName(String name);
	
	
	/**
	 * 根据用户id更新用户
	 * @param id
	 */
	void updateById(Long id,String newName, Integer newAge);
	
	/**
	 * 根据name查询用户
	 * @param name
	 */
	User getUserByName(String name);
	
	/**
	 * 获取所有用户总量 
	 * @return
	 */
	Integer getAllUserNum();
	
	/**
	 * 删除所有用户
	 */
	void deleteAllUsers();
	
}
