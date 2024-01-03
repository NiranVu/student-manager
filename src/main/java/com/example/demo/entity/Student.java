package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Entity
@Data
public class Student {

	@Id
	private int userId;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;
	
	private String studentCode;
}
