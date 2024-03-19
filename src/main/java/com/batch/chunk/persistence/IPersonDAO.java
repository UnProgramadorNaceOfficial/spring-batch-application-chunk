package com.batch.chunk.persistence;

import com.batch.chunk.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonDAO extends CrudRepository<Person, Long> {
}
