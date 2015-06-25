package br.jotas.sc.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.jotas.sc.dao.ProdutoDAO;
import br.jotas.sc.exception.CampoObrigatorioException;
import br.jotas.sc.model.Produto;

public class ProdutoController {

	public void salvarProduto(Produto produto) throws NullPointerException,
			Exception {
		validaDados(produto);
		ProdutoDAO dao = new ProdutoDAO();
		if (produto.getId() != 0) {
			dao.editarProduto(produto);
		} else {
			dao.salvarProduto(produto);
		}
	}

	public Produto obterProduto(int id) {
		ProdutoDAO dao = new ProdutoDAO();
		return dao.obterProduto(id);
	}

	public void excluirProduto(Produto produto) throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluirProduto(produto);
		JOptionPane.showMessageDialog(null, "Produto excluido com sucesso.");
	}

	public void validaDados(Produto produto) throws CampoObrigatorioException, NullPointerException, NumberFormatException,
			Exception {
		if (produto.getTitulo().isEmpty())	
			throw new CampoObrigatorioException("Nome");		
		if (produto.getValor() == null)
			throw new CampoObrigatorioException("Valor");
		if (produto.getCategoria() == null)
			throw new CampoObrigatorioException("Categoria");
	}
	
	public ArrayList<Produto> listarProduto() {
		ProdutoDAO dao = new ProdutoDAO();
		return dao.listarProduto();
	}
	
	public ArrayList<Produto> listarProdutoPorNome() {
		ProdutoDAO dao = new ProdutoDAO();
		return dao.listarProdutoPorNome();
	}

	public List<Produto> procurarProdutoPorNome(String s, String c) {
			ProdutoDAO dao = new ProdutoDAO();
			return dao.procurarProdutoPorNome(s,c);
	}

	
}
