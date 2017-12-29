package com.xyl.springboot.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyl.springboot.dao.CityDao;
import com.xyl.springboot.domain.third.City;
import com.xyl.springboot.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityDao cityDao;
	
	
	@Override
	public City findCityById(Long cityId) {
		return cityDao.findCityById(cityId);
	}
	
	@Transactional(value="thirdTransactionManager")
	@Override
	public int save(City city) {
		int result = cityDao.save(city);
		throw new RuntimeException("测试事务");
//		return result;
	}

}
