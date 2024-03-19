package com.batch.chunk.step;

import com.batch.chunk.entities.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person, Person> {


    @Override
    public Person process(Person person) throws Exception {

        if(person == null){
            log.error("The item of ItemProcessor is null");
            throw new RuntimeException();
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();

        person.setCreateAt(dateTimeFormatter.format(localDateTime));

        return person;
    }
}
