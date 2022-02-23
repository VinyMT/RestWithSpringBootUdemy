package com.vinymt.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinymt.course.data.vo.PersonVO;
import com.vinymt.course.data.vo.v2.PersonVOV2;
import com.vinymt.course.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@GetMapping("/{id}")
	public PersonVO findById(@PathVariable("id") Long id) throws Exception {
		return service.findById(id);
	}
	
	@GetMapping
	public List<PersonVO> findAll() throws Exception {
		return service.findAll();
	}
	
	@PostMapping("/v1")
	public PersonVO createv1(@RequestBody PersonVO person) throws Exception {
		return service.createv1(person);
	}
	
	@PostMapping("/v2")
	public PersonVOV2 createv2(@RequestBody PersonVOV2 person) throws Exception {
		return service.createv2(person);
	}
	
	@PutMapping
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		return service.update(person);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
