package com.sefaz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "phone")
@AllArgsConstructor
@Data
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 3)
	private String ddd;
	
	@Column(nullable = false, length = 9)
	private String number;
	
	@Column(nullable = false)
	private String type;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="user_id")
	private User user;

	public Phone() {
	}

	public Phone(int id, String ddd, String number, String type) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.number = number;
		this.type = type;
	}
}
