package com.example.model.request;

import java.io.Serializable;

import com.example.entity.Person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonAddRequest implements Serializable {

	private static final long serialVersionUID = -3502926048333417556L;

	private String name;
	
	private Integer age;
	
	public Person convertToPerson() {
		
		Person p = new Person();
		p.setId(null);
		p.setName(name);
		p.setAge(age);
		
		return p;
	}
}
