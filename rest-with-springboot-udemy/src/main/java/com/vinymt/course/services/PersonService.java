package com.vinymt.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinymt.course.exception.ResourceNotFoundException;
import com.vinymt.course.model.Person;
import com.vinymt.course.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repo;
	
	public Person findById(Long id) {
		return repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this id!"));
	}
	
	public List<Person> findAll() {
		return repo.findAll();
	}
	
	public Person create(Person person) {
		return repo.save(person);
	}
	
	public Person update(Person person) {
		Person entity = repo.findById(person.getId()).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this id!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		repo.save(entity);
		return entity;
	}
	
	public void delete(Long id) {
		Person entity = repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No records found for this id!"));
		repo.delete(entity);
	}
}
