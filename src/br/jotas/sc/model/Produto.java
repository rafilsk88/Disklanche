package br.jotas.sc.model;

import java.util.ArrayList;

public class Produto {

	private int id;
	private String titulo;
	private Categoria categoria;
	private Double valor;
	private StatusProdutoEnum status;

	public Produto(int id, String titulo, Categoria categoria, double valor,
			StatusProdutoEnum status) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.categoria = categoria;
		this.valor = valor;
		this.status = status;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Produto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public StatusProdutoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusProdutoEnum status) {
		this.status = status;
	}

	public String toString() {
		return this.titulo;
	}

}
