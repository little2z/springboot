package com.xyl.springboot.dao;

import com.xyl.springboot.domain.third.City;

public interface CityDao {
	
	public City findCityById(Long cityId);
	
	public int save(City city);
}
