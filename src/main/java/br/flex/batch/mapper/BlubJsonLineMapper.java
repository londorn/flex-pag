package br.flex.batch.mapper;

import org.springframework.batch.item.file.LineMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.flex.batch.dto.ObjetoJobTeste;

public class BlubJsonLineMapper implements LineMapper<ObjetoJobTeste>{

	 private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public ObjetoJobTeste mapLine(String line, int lineNumber) throws Exception {
		return mapper.readValue(line, ObjetoJobTeste.class);
	}

}
