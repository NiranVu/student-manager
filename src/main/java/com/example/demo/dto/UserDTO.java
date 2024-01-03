package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

	private int id;
	
	@Min(value = 1, message = "{size.msg}")
	private int age;
	
	@NotBlank(message = "{not.blank}")
	private String name;
	private String avatarURL;
	
	private String username;
	private String password;
	
	private DepartmentDTO department;
	
	private MultipartFile file;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;

	private List<String> roles;
}
