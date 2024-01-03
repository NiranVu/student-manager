package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	//select user where username = ?
	UserDTO findByUsername(String username);
	
	//select user where name = :s
	Page<User> findByName(String s, Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE u.name LIKE :x")
	Page<User> searchByName(@Param("x") String s, Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE u.name LIKE :x " + "OR u.username LIKE :x")
	List<User> searchByNameAndUsername(@Param("x") String s);
	
	@Modifying
	@Query("DELETE FROM User u WHERE u.username = :x")
	int deleteUser(@Param("x") String username);
	
	void deleteByUsername(String username);
}
