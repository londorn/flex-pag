package br.flex.batch.dto;

public class ObjetoJobTeste {
	
	private String id;
	
	private String nome;
	
	private String nascimento;
	
	private String nacionalidade;

	
	public ObjetoJobTeste() {
		
	}
	
	public ObjetoJobTeste(String id, String nome, String nascimento, String nacionalidade) {
		this.id = id;
		this.nome = nome;
		this.nascimento = nascimento;
		this.nacionalidade = nacionalidade;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
}
