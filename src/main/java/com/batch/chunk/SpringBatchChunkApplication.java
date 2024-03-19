package com.batch.chunk;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SpringBatchChunkApplication {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchChunkApplication.class, args);
	}

	@Bean
	CommandLineRunner init(){
		return args -> {
			JobParameters jobParameters = new JobParametersBuilder()
					.addString("nombre", "chunk")
					.addLong("id", System.currentTimeMillis())
					.addDate("fecha", new Date())
					.toJobParameters();

			jobLauncher.run(job, jobParameters);

		};
	}

}
