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

import com.vinymt.course.model.Person;
import com.vinymt.course.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@GetMapping(value="/{id}")
	public Person findById(@PathVariable("id") Long id) throws Exception {
		return service.findById(id);
	}
	
	@GetMapping
	public List<Person> findAll() throws Exception {
		return service.findAll();
	}
	
	@PostMapping
	public Person create(@RequestBody Person person) throws Exception {
		return service.create(person);
	}
	
	@PutMapping
	public Person update(@RequestBody Person person) throws Exception {
		return service.update(person);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
