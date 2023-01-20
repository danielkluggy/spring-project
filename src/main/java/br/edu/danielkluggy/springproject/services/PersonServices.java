package br.edu.danielkluggy.springproject.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.danielkluggy.springproject.data.vo.v1.PersonVO;
import br.edu.danielkluggy.springproject.exceptions.ResourceNotFoundException;
import br.edu.danielkluggy.springproject.mapper.SpringMapper;
import br.edu.danielkluggy.springproject.model.Person;
import br.edu.danielkluggy.springproject.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;
		
	public List<PersonVO> findAll() {
		logger.info("Fiding all people!");
		
		return SpringMapper.parseListObjects(repository.findAll(), PersonVO.class) ;
	}

	public PersonVO findById(Long id) {
		logger.info("Fiding one PersonVO!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	
		return SpringMapper.parseObject(entity, PersonVO.class);
	
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one PersonVO!");
		
		var entity = SpringMapper.parseObject(person, Person.class);
		var vo = SpringMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one PersonVO!");
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		if(person.getFirstName() != null)
			entity.setFirstName(person.getFirstName());
		if(person.getLastName() != null)
			entity.setLastName(person.getLastName());
		if(person.getAddress() != null)
			entity.setAddress(person.getAddress());
		if(person.getGender() != null)
			entity.setGender(person.getGender());
		
		var vo = SpringMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one PersonVO!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
	
}
