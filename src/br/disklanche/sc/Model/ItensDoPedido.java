package br.disklanche.sc.Model;

public class ItensDoPedido {

	private int id;
	private Produto produto;
	private int quantidade;
	private double valorUnitario;
	
	public ItensDoPedido(int id, Produto produto, int quantidade, double valorUnitario) {
		super();
		this.id = id;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
	}
	public ItensDoPedido(Produto produto, int quantidade, double valorUnitario) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
	}
	public ItensDoPedido() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	
	
	
}
