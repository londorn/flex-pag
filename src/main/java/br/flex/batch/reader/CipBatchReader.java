package br.flex.batch.reader;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.flex.batch.dto.ObjetoJobTeste;
import br.flex.batch.processor.CipBatchItemProcessor;

public class CipBatchReader implements ItemReader<ObjetoJobTeste> {

	private static final Logger log = LoggerFactory.getLogger(CipBatchItemProcessor.class);

	private final String apiUrl;
	private final RestTemplate restTemplate;

	private int nextObjetoTestIndex;
	private List<ObjetoJobTeste> objetoTestData;
	

	public CipBatchReader(String apiUrl, RestTemplate restTemplate) {
		log.info("!!! JOB OK READER CONSTRUTOR!");
		this.apiUrl = apiUrl;
		this.restTemplate = restTemplate;
		nextObjetoTestIndex = 0;
	}

	@Override
	public ObjetoJobTeste read() throws Exception {
		log.info("!!! JOB OK READER METHOD!");
		objetoTestData = fetchStudentDataFromAPI();
//		createPost();
		
		ObjetoJobTeste nextStudent = null;

		if (nextObjetoTestIndex < objetoTestData.size()) {
			nextStudent = objetoTestData.get(nextObjetoTestIndex);
			nextObjetoTestIndex++;
		}

		return nextStudent;
	}
	
	private List<ObjetoJobTeste> fetchStudentDataFromAPI() {
		ResponseEntity<ObjetoJobTeste[]> response = restTemplate.getForEntity(apiUrl, ObjetoJobTeste[].class);
		ObjetoJobTeste[] objetoJobData = response.getBody();
		return Arrays.asList(objetoJobData);
	}

	public void createPost() {
		ObjetoJobTeste newObjetoJobTeste = new ObjetoJobTeste("2", "Teste Api 2", "27/08/2019", "Alemão");
		
		HttpEntity<ObjetoJobTeste> requestBody = new HttpEntity<>(newObjetoJobTeste);
		
		ResponseEntity<ObjetoJobTeste> result = restTemplate.postForEntity(apiUrl, requestBody, ObjetoJobTeste.class);
		
		System.out.println("Status code:" + result.getStatusCode());
		
	    // Code = 200.
        if (result.getStatusCode() == HttpStatus.OK) {
        	ObjetoJobTeste e = result.getBody();
            System.out.println("(Client Side) Employee Created: "+ e.getId());
        }
	}
	
}
