package br.flex.batch.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import br.flex.batch.dto.ObjetoJobTeste;
import br.flex.batch.processor.CipBatchItemProcessor;
import br.flex.batch.reader.CipBatchReader;

@Configuration
@EnableBatchProcessing
public class BatchCipConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	private RestTemplate restTemplate;

	@Autowired
	public DataSource dataSource;
	
    @Autowired
    EntityManagerFactory emf;
	
//	@Autowired
//	Job job;
	
	private static final Logger log = LoggerFactory.getLogger(CipBatchItemProcessor.class);
	
	public BatchCipConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			DataSource dataSource) {
		log.info("!!! JOB OK BATCHCONFIG CONSTRUTOR!");
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.dataSource = dataSource;
	}
	
	@Bean
	ItemReader<ObjetoJobTeste> reader(RestTemplate restTemplate) {
		ItemReader<ObjetoJobTeste> obj = new CipBatchReader(
				"http://localhost:9000/autores", restTemplate);
		return obj;
	}

	@Bean
	public CipBatchItemProcessor processor() {
		return new CipBatchItemProcessor();
	}

	
	@Bean
    public JdbcBatchItemWriter<ObjetoJobTeste> writer() {
		log.info("!!! JOB OK WRITER!");
        JdbcBatchItemWriter<ObjetoJobTeste> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO tb99999 (id, nome, nascimento, nacionalidade) VALUES (:id, :nome, :nascimento, :nacionalidade)");
        writer.setDataSource(this.dataSource);
        return writer;
    }
	
	@Bean
	public Job job() {
		log.info("!!! JOB OK METHOD!");
		return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer())
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		log.info("!!! JOB OK STEP1!");
		return stepBuilderFactory.get("step1").<ObjetoJobTeste, ObjetoJobTeste>chunk(10)
				.reader(reader(restTemplate)).processor(processor()).writer(writer()).build();
	}
	    
}
