package br.flex.batch.writer;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import br.flex.batch.dto.ObjetoJobTeste;
import br.flex.batch.processor.CipBatchItemProcessor;

public class CipBatchWriter implements ItemWriter<ObjetoJobTeste>{

	
	private static final Logger log = LoggerFactory.getLogger(CipBatchItemProcessor.class);
	
//	@Autowired
//	public DataSource dataSource;
	
	@Override
	public void write(List<? extends ObjetoJobTeste> items) throws Exception {
		
//		log.info("!!! JOB OK WRITER!");
		
//		 JdbcBatchItemWriter<ObjetoJobTeste> writer = new JdbcBatchItemWriter<>();
//		 	writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//        	writer.setSql("INSERT INTO tb99999 (id, nome, nascimento, nacionalidade) VALUES (:id, :nome, :nascimento, :nacionalidade)");
//        	writer.setDataSource(this.dataSource);
	        
	}

}
