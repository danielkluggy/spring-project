package br.edu.danielkluggy.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.danielkluggy.springproject.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}