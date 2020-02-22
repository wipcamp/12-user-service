package com.wipcamp.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="know_whences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnowWhence {

	@Id
	@GeneratedValue
	private int id;

	private boolean facebook = false;
	private boolean camphub = false;
	private boolean dekd = false;
	private boolean sit = false;
	private String etc = "";
}
