package com.xyl.springboot.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyl.springboot.domain.User;
import com.xyl.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource(name="primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public void create(String name, Integer age) {
		jdbcTemplate
				.update("insert into user(name,age) values(?,?)", name, age);
	}

	@Override
	public void deleteByName(String name) {
		jdbcTemplate.update("delete from user where name = ?", name);
	}

	@Override
	public User getUserByName(String name) {
		return jdbcTemplate.queryForObject("select * from user where name = ?",
				new Object[] {name}, new UserMapper());
	}

	@Override
	public Integer getAllUserNum() {
		return jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
	}

	@Override
	public void deleteAllUsers() {
		jdbcTemplate.update("delete from user");
	}
	
	
	@Override
	@Transactional
	public void updateById(Long id,String newName, Integer newAge) {
		jdbcTemplate.update("update user set name = ? , age = ? where id = ?", newName, newAge, id);
//		throw new RuntimeException("测试事务运行时异常");
//		throw new Exception("测试事务运行时异常");
	}
	
	private static final class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setName(rs.getString("name"));
			user.setAge(rs.getInt("age"));
			return user;
		}

	}
}
