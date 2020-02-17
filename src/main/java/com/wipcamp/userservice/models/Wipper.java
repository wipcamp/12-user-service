package com.wipcamp.userservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wippers")
@Data
@NoArgsConstructor
public class Wipper {

	@Id
	@GeneratedValue
	private Integer wipperId;

	@Column(nullable = true)
	private String name;

	@Column(nullable = true)
	private String surname;

	@ManyToOne
	@JoinColumn(name="role_id",referencedColumnName = "id")
	private Role role;
}
