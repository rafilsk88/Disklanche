package br.disklanche.sc.Model;

public class Estoque {

	private Produto produto;
	private int quantidadeComprada;
	private int estoqueAtual;

	public Estoque() {
		super();
	}

	public Estoque(int id, Produto produto, int quantidadeComprada,
			int totalCompra, int estoqueAtual) {
		super();
		this.produto = produto;
		this.quantidadeComprada = quantidadeComprada;
		this.estoqueAtual = estoqueAtual;
	}

	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidadeComprada() {
		return quantidadeComprada;
	}

	public void setQuantidadeComprada(int quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}

	
	public int getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(int estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

}
