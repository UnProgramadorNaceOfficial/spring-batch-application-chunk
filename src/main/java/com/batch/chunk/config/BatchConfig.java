package com.batch.chunk.config;

import com.batch.chunk.entities.Person;
import com.batch.chunk.step.PersonItemProcessor;
import com.batch.chunk.step.PersonItemReader;
import com.batch.chunk.step.PersonItemWriterStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public PersonItemWriterStep personItemWriter(){
        return new PersonItemWriterStep();
    }

    @Bean
    public PersonItemReader personItemReader() {
        return new PersonItemReader();
    }

    @Bean
    public PersonItemProcessor personItemProcessor(){
        return new PersonItemProcessor();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(5);
        return taskExecutor;
    }

    @Bean
    public Step readFile() {
        return stepBuilderFactory.get("step1")
                .<Person, Person>chunk(10)
                .reader(personItemReader())
                .processor(personItemProcessor())
                .writer(personItemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(readFile())
                .build();
    }
}
