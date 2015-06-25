package br.disklanche.sc.Model;

import java.util.ArrayList;
import java.util.Date;

public class RealizarPedido {

	private int id;
	private Cliente cliente;
	private Produto produto;
	private Date dataPedido;
	private Double valor;
	private int quantidade;

	public RealizarPedido(int id, Cliente cliente, Produto produto,
			Date dataPedido, Double valor, int quantidade) {
		super();
		this.cliente = cliente;
		this.produto = produto;
		this.dataPedido = dataPedido;
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public RealizarPedido() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}


}
