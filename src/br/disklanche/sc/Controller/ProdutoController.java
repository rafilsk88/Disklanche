package br.disklanche.sc.Controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.disklanche.sc.DAO.ProdutoDAO;
import br.disklanche.sc.Exception.CampoObrigatorioException;
import br.disklanche.sc.Exception.ClienteException;
import br.disklanche.sc.Exception.ProdutoException;
import br.disklanche.sc.Model.Produto;
import br.disklanche.sc.View.CadastroCategoriaUI;

public class ProdutoController {

	public void salvarProduto(Produto produto) throws NullPointerException,
	ParseException, Exception 
	{
		ProdutoDAO dao = new ProdutoDAO();

		if (produto.getId() != 0) 
		{
			validaDados(produto);
			dao.editarProduto(produto);
		}
		else 
		{
			validaDados(produto);
			validaExistenciaProduto(produto);
			dao.salvarProduto(produto);
		}
	}
	
	public void validaDados(Produto produto) throws CampoObrigatorioException, NullPointerException, NumberFormatException
	{
		if (produto.getTitulo().isEmpty())	
			throw new CampoObrigatorioException("Nome");		
		if (produto.getValor() == null)
			throw new CampoObrigatorioException("Valor");
		if (produto.getCategoria() == null)
			throw new CampoObrigatorioException("Categoria");
	}
		
	public void validaExistenciaProduto(Produto produto) throws ProdutoException, NullPointerException, NumberFormatException
	{
		for (int i = 0; i < listarProduto().size(); i++)
		{
			if (listarProduto().get(i).getTitulo().equalsIgnoreCase(produto.getTitulo())) 
				throw new ProdutoException("cadastrado");
		}
	}

	
	public void excluirProduto(Produto produto) throws SQLException 
	{
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluirProduto(produto);
		JOptionPane.showMessageDialog(null, "Produto excluido com sucesso.");
	}
			
	public ArrayList<Produto> listarProduto() 
	{
		ProdutoDAO dao = new ProdutoDAO();
		return dao.listarProduto();
	}
	
	public ArrayList<Produto> listarProdutoPorNome() 
	{
		ProdutoDAO dao = new ProdutoDAO();
		return dao.listarProdutoPorNome();
	}

	public List<Produto> procurarProdutoPorNome(String s, String c) 
	{
			ProdutoDAO dao = new ProdutoDAO();
			return dao.procurarProdutoPorNome(s,c);
	}

	public Produto obterProduto(int id) 
	{
		ProdutoDAO dao = new ProdutoDAO();
		return dao.obterProduto(id);
	}
	
}
