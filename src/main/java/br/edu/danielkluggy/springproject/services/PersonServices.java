package br.edu.danielkluggy.springproject.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.danielkluggy.springproject.exceptions.ResourceNotFoundException;
import br.edu.danielkluggy.springproject.model.Person;
import br.edu.danielkluggy.springproject.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll() {
		logger.info("Fiding all people!");
		
		return repository.findAll();
	}

	public Person findById(Long id) {
		logger.info("Fiding one person!");
			
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating one person!");
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		if(person.getFirstName() != null)
			entity.setFirstName(person.getFirstName());
		if(person.getLastName() != null)
			entity.setLastName(person.getLastName());
		if(person.getAdress() != null)
			entity.setAdress(person.getAdress());
		if(person.getGender() != null)
			entity.setGender(person.getGender());
		
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");

		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
	
}
