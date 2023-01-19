package br.edu.danielkluggy.springproject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.edu.danielkluggy.springproject.model.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<Person> findAll() {
		logger.info("Fiding all people!");
		
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i <8; i++) {
			Person person = mockPerson(counter.incrementAndGet());
			persons.add(person);
		}
		
		return persons;
	}

	public Person findById(String id) {
		logger.info("Fiding one person!");
			
		return mockPerson(counter.incrementAndGet());
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Updating one person!");
		
		return person;
	}
	
	public void delete(String id) {
		logger.info("Deleting one person!");
	}
	
	private Person mockPerson(long i) {
		Person person = new Person();
		person.setId(i);
		person.setFirstName("Nome" + i);
		person.setLastName("Sobrenome " + i);
		person.setAdress("Endereço" + i);
		if (i % 2 == 1)
			person.setGender("Masculino");
		else
			person.setGender("Feminino");
		return person;
	}

}
