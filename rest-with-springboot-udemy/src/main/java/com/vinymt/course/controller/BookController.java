package com.vinymt.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinymt.course.data.vo.v1.BookVO;
import com.vinymt.course.services.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Book controller")
@RestController
@RequestMapping("/api/book/v1")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	PagedResourcesAssembler<BookVO> assembler;
	
	
	@ApiOperation(value="Find by id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO findById(@PathVariable("id") Long id) throws Exception {
		BookVO vo = service.findById(id);
		vo.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(id).withSelfRel());
		return vo;
	}
	
	@ApiOperation(value="Find all")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "12") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction) throws Exception {
		var sortDirection = direction.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
		
		Page<BookVO> books = service.findAll(pageable);
		
		for(BookVO vo : books) {
			vo.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(vo.getId()).withSelfRel());
		}
		
		PagedModel<?> model = assembler.toModel(books);
		
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@ApiOperation(value="Create")
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO createv1(@RequestBody BookVO person) throws Exception {
		BookVO vo = service.create(person);
		vo.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(vo.getId()).withSelfRel());
		return vo;
	}
	
	@ApiOperation(value="Update")
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO update(@RequestBody BookVO person) throws Exception {
		BookVO vo = service.update(person);
		vo.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(vo.getId()).withSelfRel());
		return vo;
	}
	
	@ApiOperation(value="Delete")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
