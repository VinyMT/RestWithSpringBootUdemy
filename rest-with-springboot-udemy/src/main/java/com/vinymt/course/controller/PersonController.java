package com.vinymt.course.controller;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinymt.course.data.vo.v1.PersonVO;
import com.vinymt.course.services.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Person controller")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@ApiOperation(value="Find by id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO findById(@PathVariable("id") Long id) throws Exception {
		PersonVO vo = service.findById(id);
		vo.add(WebMvcLinkBuilder.linkTo(PersonController.class).slash(id).withSelfRel());
		return vo;
	}
	
	@ApiOperation(value="Find all")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<PersonVO> findAll() throws Exception {
		List<PersonVO> listVo = service.findAll();
		
		for(PersonVO vo : listVo) {
			vo.add(WebMvcLinkBuilder.linkTo(PersonController.class).slash(vo.getId()).withSelfRel());
		}
		
		return listVo;
	}
	
	@ApiOperation(value="Create")
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO createv1(@RequestBody PersonVO person) throws Exception {
		PersonVO vo = service.createv1(person);
		vo.add(WebMvcLinkBuilder.linkTo(PersonController.class).slash(vo.getId()).withSelfRel());
		return vo;
	}
	
	@ApiOperation(value="Update")
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		PersonVO vo = service.update(person);
		vo.add(WebMvcLinkBuilder.linkTo(PersonController.class).slash(vo.getId()).withSelfRel());
		return vo;
	}
	
	@ApiOperation(value="Delete")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value="Disable")
	@PatchMapping("/{id}")
	public PersonVO disablePerson(@PathVariable("id") Long id) throws Exception {
		PersonVO vo = service.disablePerson(id);
		vo.add(WebMvcLinkBuilder.linkTo(PersonController.class).slash(vo.getId()).withSelfRel());
		return vo;
	}
}
