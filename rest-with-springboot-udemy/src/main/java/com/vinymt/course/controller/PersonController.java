package com.vinymt.course.controller;

import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;
	
	@ApiOperation(value="Find by id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO findById(@PathVariable("id") Long id) throws Exception {
		PersonVO vo = service.findById(id);
		vo.add(WebMvcLinkBuilder.linkTo(PersonController.class).slash(id).withSelfRel());
		return vo;
	}
	
	@ApiOperation(value="Find all")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "12") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction) throws Exception {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
		Page<PersonVO> persons = service.findAll(pageable);
		
		for(PersonVO vo : persons) {
			vo.add(WebMvcLinkBuilder.linkTo(PersonController.class).slash(vo.getId()).withSelfRel());
		}
		
		PagedModel<?> model = assembler.toModel(persons);
		
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@ApiOperation(value="Find person by name")
	@GetMapping(value="/findPersonByName/{firstName}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findPersonByName(
			@PathVariable("firstName") String firstName,
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "12") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction) throws Exception {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
		Page<PersonVO> persons = service.findPersonByName(firstName, pageable);
		
		for(PersonVO vo : persons) {
			vo.add(WebMvcLinkBuilder.linkTo(PersonController.class).slash(vo.getId()).withSelfRel());
		}
		
		PagedModel<?> model = assembler.toModel(persons);
		
		return new ResponseEntity<>(model, HttpStatus.OK);
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
