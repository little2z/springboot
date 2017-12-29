package com.xyl.springboot.domain.third;

public class City {
	
	private long id;
	
	private String name;
	
	private long provinceId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", provinceId=" + provinceId + "]";
	}
	
}
