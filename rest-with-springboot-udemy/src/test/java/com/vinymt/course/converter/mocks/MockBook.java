package com.vinymt.course.converter.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vinymt.course.data.model.Book;
import com.vinymt.course.data.vo.v1.BookVO;

public class MockBook {
    public Book mockEntity() {
    	return mockEntity(0);
    }
    
    public BookVO mockVO() {
    	return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    private Book mockEntity(Integer number) {
    	Book book = new Book(number.longValue(), "Author" + number, new Date(), 2.00, "Title" + number);
        return book;
    }

    private BookVO mockVO(Integer number) {
    	BookVO book = new BookVO();
    	book.setAuthor("Author" + number);
    	book.setLaunchDate(new Date());
    	book.setPrice(2.00);
    	book.setTitle("Title" + number);
    	book.setId(number.longValue());
        return book;
    }
}
