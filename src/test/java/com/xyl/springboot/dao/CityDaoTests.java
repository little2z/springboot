package com.xyl.springboot.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyl.springboot.domain.third.City;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CityDaoTests {
	
	@Autowired
	private CityDao cityDao;
	
	@Test
	public void test(){
		City city = new City();
		city.setName("北京");
		city.setProvinceId(1);
		int result = cityDao.save(city);
		Assert.assertEquals(1, result);
		
	}
	
	@Test
	public void testFind(){
		City city = cityDao.findCityById(1L);
		System.out.println(city);
		Assert.assertEquals(1L, city.getId());
	}
	
}
