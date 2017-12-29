package com.xyl.springboot.service;

import com.xyl.springboot.domain.third.City;

public interface CityService {
	
	/**
	 * 根据 cityId 查询城市
	 * @param cityId
	 * @return
	 */
	public City findCityById(Long cityId);
	
	/**
	 * 保存城市
	 * @param city
	 * @return
	 */
	public int save(City city);
}
