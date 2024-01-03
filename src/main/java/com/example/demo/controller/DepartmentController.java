package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.PageDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.SearchDTO;
import com.example.demo.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@PostMapping("/new")
	public ResponseDTO<DepartmentDTO> create(@ModelAttribute @Valid DepartmentDTO departmentDTO) throws IllegalStateException, IOException {
		
		departmentService.create(departmentDTO);
		
		return ResponseDTO.<DepartmentDTO>builder().status(200).data(departmentDTO).build();
	}
	
	@PostMapping("/json")
	public ResponseDTO<DepartmentDTO> createNewJSON(@RequestBody @Valid DepartmentDTO departmentDTO) throws IllegalStateException, IOException {
		
		departmentService.create(departmentDTO);
		
		return ResponseDTO.<DepartmentDTO>builder().status(200).data(departmentDTO).build();
	}
	
	
	@DeleteMapping("/delete")
	public ResponseDTO<Void> delete(@RequestParam ("id") int id) {
		departmentService.delete(id);
		
		ResponseDTO<Void> responseDTO = new ResponseDTO<>();
		responseDTO.setStatus(200);
		responseDTO.setMsg("ok");
		return responseDTO;
	}
	
	
	@PutMapping("/edit")
	public ResponseDTO<DepartmentDTO> edit(@ModelAttribute @Valid DepartmentDTO departmentDTO) {
		departmentService.update(departmentDTO);
		return ResponseDTO.<DepartmentDTO>builder().status(200).data(departmentDTO).build();

	}
	
	@GetMapping("/list")
	public String list(Model model) {		
		return "redirect:/department/search";
	}
	
	@PostMapping("/search")
	public ResponseDTO<PageDTO<List<DepartmentDTO>>> search(@ModelAttribute @Valid SearchDTO searchDTO) {
		
		PageDTO<List<DepartmentDTO>> pageDTO = departmentService.search(searchDTO);
		
		return ResponseDTO.<PageDTO<List<DepartmentDTO>>>builder().status(200).data(pageDTO).build();
	}
	
	@GetMapping("/")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseDTO<DepartmentDTO> get(@RequestParam("id") int id) {

		return ResponseDTO.<DepartmentDTO>builder().status(200).data(departmentService.getById(id)).build();
	}
}
