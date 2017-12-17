package com.xyl.springboot.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xyl.springboot.domain.User;

@RestController
@RequestMapping("/users")
public class UserController {

	private Map<Long, User> userMaps = Collections
			.synchronizedMap(new HashMap<Long, User>());

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@ApiOperation(value = "获取用户列表", notes = "")
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUserList() {

		return new ArrayList<User>(userMaps.values());
	}

	/**
	 * 增加用户，注意如果这里参数指定是 body中获取，则不能以 form 表单的方式提交
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, paramType="body", dataType = "User")
	@RequestMapping(method = RequestMethod.POST)
	public String postUser(@RequestBody User user) {

		userMaps.put(user.getId(), user);
		return "success";
	}

	/**
	 * 查询某个具体的用户
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, paramType="path", dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long id) {

		return userMaps.get(id);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param id
	 * @param newUser
	 * @return
	 */
	@ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType="path", dataType = "Long"),
			@ApiImplicitParam(name = "newUser", value = "用户详细实体user", required = true, dataType = "User") })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @RequestBody User newUser) {
		User oldUser = userMaps.get(id);

		if (oldUser != null) {
			oldUser.setName(newUser.getName());
			oldUser.setAge(newUser.getAge());
			// userMaps.put(id, oldUser);
		}

		return "success";
	}

	/**
	 * 删除用户信息
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType="path", dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {

		userMaps.remove(id);
		return "success";
	}

}
