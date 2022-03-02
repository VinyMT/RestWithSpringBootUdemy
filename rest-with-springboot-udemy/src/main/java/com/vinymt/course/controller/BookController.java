package com.vinymt.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinymt.course.data.vo.v1.BookVO;
import com.vinymt.course.services.BookService;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO findById(@PathVariable("id") Long id) throws Exception {
		BookVO vo = service.findById(id);
		vo.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(id).withSelfRel());
		return vo;
	}
	
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<BookVO> findAll() throws Exception {
		List<BookVO> listVo = service.findAll();
		
		for(BookVO vo : listVo) {
			vo.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(vo.getId()).withSelfRel());
		}
		
		return listVo;
	}
	
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO createv1(@RequestBody BookVO person) throws Exception {
		BookVO vo = service.create(person);
		vo.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(vo.getId()).withSelfRel());
		return vo;
	}
	
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO update(@RequestBody BookVO person) throws Exception {
		BookVO vo = service.update(person);
		vo.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(vo.getId()).withSelfRel());
		return vo;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
