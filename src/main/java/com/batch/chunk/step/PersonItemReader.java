package com.batch.chunk.step;

import com.batch.chunk.entities.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;

public class PersonItemReader extends FlatFileItemReader<Person> {

    public PersonItemReader() {
        setName("personReader");
        setResource(new ClassPathResource("persons.csv"));
        setLinesToSkip(1);
        setLineMapper(getLineMapper());
        setEncoding(StandardCharsets.UTF_8.name());
    }

    private LineMapper<Person> getLineMapper() {
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        String[] columnsToBeInserted = new String[]{"name", "lastName", "age"};
        int[] fields = new int[]{0, 1, 2};
        tokenizer.setNames(columnsToBeInserted);
        tokenizer.setIncludedFields(fields);

        BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Person.class);
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        tokenizer.setDelimiter(",");
        return lineMapper;
    }
}
