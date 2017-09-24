package com.example;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.example.entity.Person;
import com.example.model.request.PersonAddRequest;
import com.example.service.PersonService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "personBean")
@ViewScoped
@Getter
@Setter
public class PersonBean implements Serializable {

	private static final long serialVersionUID = -2759562433803549067L;
	
	private List<Person> persons;
    
    @ManagedProperty("#{personService}")
    private PersonService personService;
 
    private PersonAddRequest personAdd = new PersonAddRequest();
    
    @PostConstruct
    protected void init() {
    	persons = personService.getList(10, 1);
    }
    
    public void deleteAction(Person person) {
    	personService.delete(person);
    	persons.remove(person);
    }
    
    public void addAction() {
    	Person person = personService.add(personAdd);
    	persons.add(person);
    	personAdd = new PersonAddRequest();
    }
    
    public void onRowEdit(RowEditEvent event) {
    	
    	Person person = (Person)event.getObject();
    	
    	personService.update(person);
    	
    	addMessage("Person Edited", "For Person ID: " + person.getId().toString());
    }
     
    public void onRowCancel(RowEditEvent event) {
    	addMessage("Edit Cancelled", "");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}