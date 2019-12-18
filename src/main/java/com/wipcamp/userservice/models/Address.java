package com.wipcamp.userservice.models;

public class Address {
	private String province;
	private String distict;

	public Address(String province, String distict) {
		this.province = province;
		this.distict = distict;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistict() {
		return distict;
	}

	public void setDistict(String distict) {
		this.distict = distict;
	}
}
