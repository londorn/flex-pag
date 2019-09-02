package br.flex.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.flex.batch.dto.ObjetoJobTeste;

public class CipBatchItemProcessor implements ItemProcessor<ObjetoJobTeste, ObjetoJobTeste>{

	private static final Logger log = LoggerFactory.getLogger(CipBatchItemProcessor.class);
	
	@Override	
	public ObjetoJobTeste process(ObjetoJobTeste item) throws Exception {
		
		log.info("!!! JOB OK PROCESSOR!");

			if(item.getNome().equals("willian")) {
				item.setNome("Novo");
			}

	        return item;
	}

}
