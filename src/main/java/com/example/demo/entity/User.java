package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name="db_user")
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private int age;
	private String name;
	private String avatarURL;
	
	@Column(unique= true)
	private String username;
	private String password;
	
//	@OneToMany(mappedBy = "user")
//	private List<UserRole> roles;
	
	@ElementCollection
	@CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role")
	private List<String> roles;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@ManyToOne
	private Department department;
}