package br.flex.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport{

	
	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

//	private final JdbcTemplate jdbcTemplate;
//	
	
	@Autowired
	public JobCompletionNotificationListener() {
//		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

//			jdbcTemplate.query("SELECT * FROM tb12014",
//				(rs, row) -> new ObjetoJobTeste(
//					rs.getString(1),
//					rs.getString(2))
//			).forEach(person -> log.info("Found <" + person + "> in the database."));
		}
	}
	
	
}
