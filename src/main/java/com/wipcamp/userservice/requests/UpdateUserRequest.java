package com.wipcamp.userservice.requests;

import com.wipcamp.userservice.models.Address;
import com.wipcamp.userservice.models.User;

public class UpdateUserRequest extends User {
	private String addr_province;
	private String addr_district;

	private String parent_relation;
	private Long parent_telno;

	public String getAddr_province() {
		return addr_province;
	}

	public void setAddr_province(String addr_province) {
		super.getAddress().setProvince(addr_province);
		this.addr_province = addr_province;
	}

	public String getAddr_district() {
		return addr_district;
	}

	public void setAddr_district(String addr_district) {
		super.getAddress().setDistict(addr_district);
		this.addr_district = addr_district;
	}

	public String getParent_relation() {
		return parent_relation;
	}

	public void setParent_relation(String parent_relation) {
		super.getParent().setRelation(parent_relation);
		this.parent_relation = parent_relation;
	}

	public Long getParent_telno() {
		return parent_telno;
	}

	public void setParent_telno(Long parent_telno) {
		super.getParent().setTelNo(parent_telno);
		this.parent_telno = parent_telno;
	}
}
