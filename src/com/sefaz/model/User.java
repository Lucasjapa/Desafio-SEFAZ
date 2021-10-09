package com.sefaz.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Integer id;
	
	@NonNull
	@Column(nullable = false, length = 50)
	private String name;
	
	@NonNull
	@Column(nullable = false, length = 100)
	private String email;
	
	@NonNull
	@Column(nullable = false, length = 20)
	private String password;
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Phone> phones;
	
}
