package br.jotas.sc.model;

public class Necessidade {

	private int id;
	private String diaDaSemana;
	private Estoque estoque;
	private int necessidade;
	private int quantidade;

	public Necessidade(){
		
	}
	
	public Necessidade(int id, String diaDaSemana, Estoque estoque,
			int necessidade, int quantidade) {
		super();
		this.id = id;
		this.diaDaSemana = diaDaSemana;
		this.estoque = estoque;
		this.necessidade = necessidade;
		this.quantidade = quantidade;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public int getNecessidade() {
		return necessidade;
	}

	public void setNecessidade(int necessidade) {
		this.necessidade = necessidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	

}
