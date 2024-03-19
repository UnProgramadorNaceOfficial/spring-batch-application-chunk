package com.batch.chunk.service;

import com.batch.chunk.entities.Person;

import java.util.List;

public interface IPersonService {

    Iterable<Person> saveAll(List<Person> personList);
}
