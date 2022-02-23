package com.vinymt.course.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.vinymt.course.data.model.Person;
import com.vinymt.course.data.vo.v2.PersonVOV2;

@Service
public class PersonConverter {
	public PersonVOV2 convertEntityToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setAddress(person.getAddress());
		vo.setGender(person.getGender());
		vo.setBirthday(new Date());
		return vo ;
	}
	
	public Person convertVOToEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return entity ;
	}
}
