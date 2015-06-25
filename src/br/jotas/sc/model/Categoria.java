package br.jotas.sc.model;

public class Categoria {

	private int id;
	private String descricao;

	public Categoria(){
		
	}
	
	public Categoria(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String toString(){
		return descricao;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
