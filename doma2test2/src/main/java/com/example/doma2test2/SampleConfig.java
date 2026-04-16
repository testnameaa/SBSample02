package com.example.doma2test2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.doma2test2.tasklet.DbSelectTasklet;
import com.example.doma2test2.tasklet.DbUpdateTasklet;
import com.example.doma2test2.tasklet.OutConvTasklet;

@Configuration
public class SampleConfig {
  @Autowired
  private DbSelectTasklet dbSelectTasklet;

  @Autowired
  private OutConvTasklet outConvTasklet;

  @Autowired
  private DbUpdateTasklet dbUpdateTasklet;

  @Bean
  Job sampleJob(JobRepository jobRepository, PlatformTransactionManager txManager) {
    Step dbSelectStep = new StepBuilder("dbSelectStep", jobRepository)
      .allowStartIfComplete(true)
      .tasklet(dbSelectTasklet, txManager)
      .build();

      Step outConvStep = new StepBuilder("outConvStep", jobRepository)
      .allowStartIfComplete(true)
      .tasklet(outConvTasklet, txManager)
      .build();

      Step dbUpdateStep = new StepBuilder("dbUpdateStep", jobRepository)
      .allowStartIfComplete(true)
      .tasklet(dbUpdateTasklet, txManager)
      .build();

    return new JobBuilder("sampleJob", jobRepository)
      .incrementer(new RunIdIncrementer())
      .start(dbSelectStep)
      .next(outConvStep)
      .next(dbUpdateStep)
      .build();
  }
}