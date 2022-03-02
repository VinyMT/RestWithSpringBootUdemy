package com.vinymt.course.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.vinymt.course.converter.mocks.MockBook;
import com.vinymt.course.converter.mocks.MockPerson;
import com.vinymt.course.data.model.Book;
import com.vinymt.course.data.model.Person;
import com.vinymt.course.data.vo.v1.BookVO;
import com.vinymt.course.data.vo.v1.PersonVO;

@SpringBootTest
public class DozerConverterTest {
	
    MockPerson inputObject;
    MockBook inputObjectBook;
    
    @Before
    public void setUp() {
        inputObject = new MockPerson();
        inputObjectBook = new MockBook();
    }

    @Test
    public void parseEntityToVOTest() {
        PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("First Name Test0", output.getFirstName());
        Assert.assertEquals("Last Name Test0", output.getLastName());
        Assert.assertEquals("Addres Test0", output.getAddress());
        Assert.assertEquals("Male", output.getGender());
    }
    
    @Test
    public void parseEntityToBookVOTest() {
    	Book input = inputObjectBook.mockEntity();
        BookVO output = DozerConverter.parseObject(input, BookVO.class);
        Assert.assertEquals(input.getId(), output.getId());
        Assert.assertEquals(input.getPrice(), output.getPrice(), 0);
        Assert.assertEquals(input.getAuthor(), output.getAuthor());
        Assert.assertEquals(input.getTitle(), output.getTitle());
        Assert.assertEquals(input.getLaunchDate(), output.getLaunchDate());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), PersonVO.class);
        PersonVO outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("First Name Test0", outputZero.getFirstName());
        Assert.assertEquals("Last Name Test0", outputZero.getLastName());
        Assert.assertEquals("Addres Test0", outputZero.getAddress());
        Assert.assertEquals("Male", outputZero.getGender());
        
        PersonVO outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("First Name Test7", outputSeven.getFirstName());
        Assert.assertEquals("Last Name Test7", outputSeven.getLastName());
        Assert.assertEquals("Addres Test7", outputSeven.getAddress());
        Assert.assertEquals("Female", outputSeven.getGender());
        
        PersonVO outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("First Name Test12", outputTwelve.getFirstName());
        Assert.assertEquals("Last Name Test12", outputTwelve.getLastName());
        Assert.assertEquals("Addres Test12", outputTwelve.getAddress());
        Assert.assertEquals("Male", outputTwelve.getGender());
    }
    
    @Test
    public void parseEntityListToBookVOListTest() {
    	List<Book> inputList = inputObjectBook.mockEntityList();
        List<BookVO> outputList = DozerConverter.parseListObjects(inputList, BookVO.class);
        
        Book inputZero = inputList.get(0);
        BookVO outputZero = outputList.get(0);
        
        Assert.assertEquals(inputZero.getId(), outputZero.getId());
        Assert.assertEquals(inputZero.getPrice(), outputZero.getPrice(), 0);
        Assert.assertEquals(inputZero.getAuthor(), outputZero.getAuthor());
        Assert.assertEquals(inputZero.getTitle(), outputZero.getTitle());
        Assert.assertEquals(inputZero.getLaunchDate(), outputZero.getLaunchDate());
        
        Book inputSeven = inputList.get(7);
        BookVO outputSeven = outputList.get(7);
        
        Assert.assertEquals(inputSeven.getId(), outputSeven.getId());
        Assert.assertEquals(inputSeven.getPrice(), outputSeven.getPrice(), 0);
        Assert.assertEquals(inputSeven.getAuthor(), outputSeven.getAuthor());
        Assert.assertEquals(inputSeven.getTitle(), outputSeven.getTitle());
        Assert.assertEquals(inputSeven.getLaunchDate(), outputSeven.getLaunchDate());
        
        Book inputTwelve = inputList.get(12);
        BookVO outputTwelve = outputList.get(12);
        
        Assert.assertEquals(inputTwelve.getId(), outputTwelve.getId());
        Assert.assertEquals(inputTwelve.getPrice(), outputTwelve.getPrice(), 0);
        Assert.assertEquals(inputTwelve.getAuthor(), outputTwelve.getAuthor());
        Assert.assertEquals(inputTwelve.getTitle(), outputTwelve.getTitle());
        Assert.assertEquals(inputTwelve.getLaunchDate(), outputTwelve.getLaunchDate());
    }

    @Test
    public void parseVOToEntityTest() {
        Person output = DozerConverter.parseObject(inputObject.mockVO(), Person.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("First Name Test0", output.getFirstName());
        Assert.assertEquals("Last Name Test0", output.getLastName());
        Assert.assertEquals("Addres Test0", output.getAddress());
        Assert.assertEquals("Male", output.getGender());
    }
    
    @Test
    public void parseVOToBookEntityTest() {
    	BookVO input = inputObjectBook.mockVO();
        Book output = DozerConverter.parseObject(input, Book.class);
        Assert.assertEquals(input.getId(), output.getId());
        Assert.assertEquals(input.getPrice(), output.getPrice(), 0);
        Assert.assertEquals(input.getAuthor(), output.getAuthor());
        Assert.assertEquals(input.getTitle(), output.getTitle());
        Assert.assertEquals(input.getLaunchDate(), output.getLaunchDate());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Person> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Person.class);
        Person outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("First Name Test0", outputZero.getFirstName());
        Assert.assertEquals("Last Name Test0", outputZero.getLastName());
        Assert.assertEquals("Addres Test0", outputZero.getAddress());
        Assert.assertEquals("Male", outputZero.getGender());
        
        Person outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("First Name Test7", outputSeven.getFirstName());
        Assert.assertEquals("Last Name Test7", outputSeven.getLastName());
        Assert.assertEquals("Addres Test7", outputSeven.getAddress());
        Assert.assertEquals("Female", outputSeven.getGender());
        
        Person outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("First Name Test12", outputTwelve.getFirstName());
        Assert.assertEquals("Last Name Test12", outputTwelve.getLastName());
        Assert.assertEquals("Addres Test12", outputTwelve.getAddress());
        Assert.assertEquals("Male", outputTwelve.getGender());
    }
    
    @Test
    public void parserVOListToBookEntityListTest() {
    	List<BookVO> inputList = inputObjectBook.mockVOList();
        List<Book> outputList = DozerConverter.parseListObjects(inputList, Book.class);
        
        BookVO inputZero = inputList.get(0);
        Book outputZero = outputList.get(0);
        
        Assert.assertEquals(inputZero.getId(), outputZero.getId());
        Assert.assertEquals(inputZero.getPrice(), outputZero.getPrice(), 0);
        Assert.assertEquals(inputZero.getAuthor(), outputZero.getAuthor());
        Assert.assertEquals(inputZero.getTitle(), outputZero.getTitle());
        Assert.assertEquals(inputZero.getLaunchDate(), outputZero.getLaunchDate());
        
        BookVO inputSeven = inputList.get(7);
        Book outputSeven = outputList.get(7);
        
        Assert.assertEquals(inputSeven.getId(), outputSeven.getId());
        Assert.assertEquals(inputSeven.getPrice(), outputSeven.getPrice(), 0);
        Assert.assertEquals(inputSeven.getAuthor(), outputSeven.getAuthor());
        Assert.assertEquals(inputSeven.getTitle(), outputSeven.getTitle());
        Assert.assertEquals(inputSeven.getLaunchDate(), outputSeven.getLaunchDate());
        
        BookVO inputTwelve = inputList.get(12);
        Book outputTwelve = outputList.get(12);
        
        Assert.assertEquals(inputTwelve.getId(), outputTwelve.getId());
        Assert.assertEquals(inputTwelve.getPrice(), outputTwelve.getPrice(), 0);
        Assert.assertEquals(inputTwelve.getAuthor(), outputTwelve.getAuthor());
        Assert.assertEquals(inputTwelve.getTitle(), outputTwelve.getTitle());
        Assert.assertEquals(inputTwelve.getLaunchDate(), outputTwelve.getLaunchDate());
    }
}