package com.example.service;

import java.util.List;

import com.example.entity.Person;
import com.example.model.request.PersonAddRequest;

public interface PersonService {

	public List<Person> getList(int size, int page);
	
	public Person add(PersonAddRequest rq);
	
	public void update(Person person);
	
	public void delete(Person person);
}
