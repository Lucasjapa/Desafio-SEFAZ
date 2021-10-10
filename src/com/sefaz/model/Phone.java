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
	
	@Column(nullable = false)
	private int ddd;
	
	@Column(nullable = false)
	private String number;
	
	@Column(nullable = false)
	private String type;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="user_id")
	private User user;

	public Phone() {
	}

	public Phone(int id, int ddd, String number, String type) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.number = number;
		this.type = type;
	}
}
