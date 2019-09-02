package br.flex.batch.config;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.flex.batch.dto.JobLaunchRequest;

@Component
@EnableScheduling
public class ScheduledJob {

	
	private final AtomicBoolean enabled = new AtomicBoolean(true);

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private ApplicationContext context;
	
	@Scheduled(cron = "0 */1 * * * ?")
	void execute() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		if (enabled.get()) {
			
			JobLaunchRequest request = new JobLaunchRequest();
			request.setName("job");

			JobParameters params = new JobParametersBuilder()
		              .addString("JobID", String.valueOf(System.currentTimeMillis()))
		              .toJobParameters();

			Job job = this.context.getBean(request.getName(), Job.class);
			this.jobLauncher.run(job, params).getExitStatus();
			
		}
	}

}
