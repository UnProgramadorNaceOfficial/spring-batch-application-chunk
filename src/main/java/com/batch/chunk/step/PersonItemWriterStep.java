package com.batch.chunk.step;

import com.batch.chunk.entities.Person;
import com.batch.chunk.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class PersonItemWriterStep implements ItemWriter<Person> {

    @Autowired
    private IPersonService personService;

    @Override
    public void write(List<? extends Person> items) {

        log.info("Ingreso al writer");
        items.forEach(person -> log.info(person.toString()));

        personService.saveAll((List<Person>) items);
    }
}
