package com.vinymt.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinymt.course.converter.DozerConverter;
import com.vinymt.course.data.model.Book;
import com.vinymt.course.data.vo.v1.BookVO;
import com.vinymt.course.exception.ResourceNotFoundException;
import com.vinymt.course.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repo;
	
	public BookVO findById(Long id) {
		var entity = repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this id!"));
		return DozerConverter.parseObject(entity, BookVO.class);
	}
	
	public List<BookVO> findAll() {
		return DozerConverter.parseListObjects(repo.findAll(), BookVO.class);
	}
	
	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var vo = DozerConverter.parseObject(repo.save(entity), BookVO.class);
		return vo;
	}
	
	public BookVO update(BookVO book) {
		var entity = repo.findById(book.getId()).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this id!"));
		entity.setAuthor(book.getAuthor());
		entity.setId(book.getId());
		entity.setPrice(book.getPrice());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setTitle(book.getTitle());
		var vo = DozerConverter.parseObject(repo.save(entity), BookVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Book entity = repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this id!"));
		repo.delete(entity);
	}
}
