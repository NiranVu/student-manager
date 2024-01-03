package com.example.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PageDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.SearchDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/new")
	public ResponseDTO<Void> newUser(@ModelAttribute @Valid UserDTO userDTO) throws Exception {
		
		if(!userDTO.getFile().isEmpty()) {
			String filename = userDTO.getFile().getOriginalFilename();
			File saveFile = new File("/home/niran/project/spring-project/image" + filename);
			userDTO.getFile().transferTo(saveFile);
			userDTO.setAvatarURL(filename);
		}
		
		userService.create(userDTO);
		
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@DeleteMapping("/delete")
	public ResponseDTO<Void> delete(@RequestParam ("id") int id) {
		userService.delete(id);
		return ResponseDTO.<Void>builder().status(200).msg("ok").build();
	}
	
	@GetMapping("/download")
	public void download(@RequestParam("filename") String filename, HttpServletResponse resp) throws Exception {
		File file = new File("F:/Iamge/" + filename);
		Files.copy(file.toPath(), resp.getOutputStream());
	}
	
	@PutMapping("/edit")
	public ResponseDTO<UserDTO> edit(@ModelAttribute @Valid UserDTO userDTO) throws Exception {
	
		if(!userDTO.getFile().isEmpty()) {
			String filename = userDTO.getFile().getOriginalFilename();
			File saveFile = new File("/home/niran/project/spring-project/image" + filename);
			userDTO.getFile().transferTo(saveFile);
			userDTO.setAvatarURL(filename);
		}
		userService.update(userDTO);
		
		return ResponseDTO.<UserDTO>builder().status(200).data(userDTO).build();
	}
	
	
	@GetMapping("/list")
	public ResponseDTO<List<UserDTO>> list() {
		List<UserDTO> userDTOs = userService.getAll();
		
		return ResponseDTO.<List<UserDTO>>builder().status(200).data(userDTOs).build();
	}
	
	@PostMapping("/search")
	public ResponseDTO<PageDTO<List<UserDTO>>> search(@ModelAttribute @Valid SearchDTO searchDTO) {
		

		
		PageDTO<List<UserDTO>> pageUser = userService.searchName(searchDTO);

		return ResponseDTO.<PageDTO<List<UserDTO>>>builder().status(200).data(pageUser).build();
	}
}