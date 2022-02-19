package com.vinymt.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinymt.course.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
