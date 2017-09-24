package com.example.service.impl;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.example.entity.Person;
import com.example.model.request.PersonAddRequest;
import com.example.service.PersonService;

@ManagedBean(name = "personService")
@ApplicationScoped
public class PersonServiceImpl implements PersonService {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersonPersistence");
	
	  public List<Person> getList(int size, int page) {
	    	
	    	if (size < 1)
	    		size = 10;
	    	
	    	if (page < 1)
	    		page = 1;
	    	
	        List<Person> persons = null;
	        
	        // Create an EntityManager
	        EntityManager manager = factory.createEntityManager();
	        EntityTransaction transaction = null;
	        
	        try {
	            // Get a transaction
	            transaction = manager.getTransaction();
	            // Begin the transaction
	            transaction.begin();

	            // Get a List of Persons
	            persons = manager.createQuery("SELECT p FROM Person p ORDER BY p.id", Person.class).setMaxResults(size).setFirstResult((page - 1)*size).getResultList();

	            // Commit the transaction
	            transaction.commit();
	        } catch (Exception ex) {
	            // If there are any exceptions, roll back the changes
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            // Print the Exception
	            ex.printStackTrace();
	        } finally {
	            // Close the EntityManager
	            manager.close();
	        }
	        return persons;
	    }

	@Override
	public Person add(PersonAddRequest rq) {
		
		EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = null;
		
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new Person object
	        Person person = rq.convertToPerson();

            // Save the Person object
            manager.persist(person);

            manager.flush();
            
            // Commit the transaction
            transaction.commit();
            
            return person;
            
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        
        return null;
	}

	@Override
	public void update(Person person) {
		
		// Create an EntityManager
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            Person personDb = manager.find(Person.class, person.getId());

            // Change the values
            personDb.setName(person.getName());
            personDb.setAge(person.getAge());

            // Update the student
            manager.persist(personDb);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
	}

	@Override
	public void delete(Person person) {
		
		// Create an EntityManager
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = null;
		
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            Person personDb = manager.find(Person.class, person.getId());

            // Delete the student
            manager.remove(personDb);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
	}
     

}
