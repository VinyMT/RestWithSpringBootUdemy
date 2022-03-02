package com.vinymt.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vinymt.course.converter.DozerConverter;
import com.vinymt.course.converter.custom.PersonConverter;
import com.vinymt.course.data.model.Person;
import com.vinymt.course.data.vo.v1.PersonVO;
import com.vinymt.course.data.vo.v1.PersonVOV2;
import com.vinymt.course.exception.ResourceNotFoundException;
import com.vinymt.course.repository.PersonRepository;


@Service
public class PersonService {
	
	@Autowired
	PersonRepository repo;
	
	@Autowired
	PersonConverter converter;
	
	public PersonVO findById(Long id) {
		var entity = repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this id!"));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public List<PersonVO> findAll() {
		return DozerConverter.parseListObjects(repo.findAll(), PersonVO.class);
	}
	
	public PersonVO createv1(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(repo.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createv2(PersonVOV2 person) {
		var entity = converter.convertVOToEntity(person);
		var vo = converter.convertEntityToVO(repo.save(entity));
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		var entity = repo.findById(person.getId()).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this id!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		var vo = DozerConverter.parseObject(repo.save(entity), PersonVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Person entity = repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this id!"));
		repo.delete(entity);
	}
}
