package br.jotas.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.jotas.sc.controller.ProdutoController;
import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Categoria;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.model.Estoque;
import br.jotas.sc.model.Produto;
import br.jotas.sc.model.StatusProdutoEnum;

public class ProdutoDAO {

	private static ProdutoDAO instance;
	private Connection con;

	public ProdutoDAO() {
		con = ConnectionFactory.getConnection();
	}

	public static ProdutoDAO getInstance() {
		if (instance == null) {
			instance = new ProdutoDAO();
		}
		return instance;
	}

	public void salvarProduto(Produto produto) {
		String query = "INSERT INTO produto (titulo, categoria, valor, status) VALUES (?,?,?,?)";
		String query2 = "INSERT INTO estoque (produto) VALUES (?)";
		try {
			PreparedStatement stm = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, produto.getTitulo());
			stm.setInt(2, produto.getCategoria().getId());
			stm.setDouble(3, produto.getValor());
			stm.setInt(4, produto.getStatus().getCodigo());
			stm.execute();
			ResultSet rs = stm.getGeneratedKeys();
			rs.next();
			int id_produto = rs.getInt(1);
			Estoque e = new Estoque();
			PreparedStatement stm2 = con.prepareStatement(query2, PreparedStatement.RETURN_GENERATED_KEYS);
			stm2.setInt(1, id_produto);
			stm2.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao tentar salvar produto ] : " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void editarProduto(Produto produto) {
		String query = "UPDATE produto SET titulo = ?, categoria = ?, valor = ? WHERE id_produto = ?";
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(query);
			stm.setString(1, produto.getTitulo());
			stm.setInt(2, produto.getCategoria().getId());
			stm.setDouble(3, produto.getValor());
			stm.setInt(4, produto.getId());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("[ Erro ao salvar produto editado ] : " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Produto obterProduto(int id) {
		String query = "Select * from produto where id_produto = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
			while (res.next()) {
				Produto produto = new Produto();
				Categoria c = new Categoria();
				
				c.setDescricao(res.getString("descricao"));
				
				produto.setId(res.getInt("id_produto"));
				produto.setTitulo(res.getString("titulo"));
				produto.setValor(res.getDouble("valor"));
				produto.setCategoria(c);
				listaProdutos.add(produto);
			}
			return listaProdutos.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar obter Cliente ] : " + e.getMessage());
			return null;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Produto> procurarProdutoPorNome(String s, String c) {
		String query = "Select p.*, c.descricao from produto p inner join categoria c on c.id_categoria = p.categoria where p.titulo like ? and c.descricao like ? and p.status like 1";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, "%"+s+"%");
			stm.setString(2, "%"+c+"%");
			ResultSet res = stm.executeQuery();
			ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
			while (res.next()) {
				Categoria c2 = new Categoria();
				Produto produto = new Produto();

				c2.setDescricao(res.getString("descricao"));
				
				produto.setId(res.getInt("id_produto"));
				produto.setTitulo(res.getString("titulo"));
				produto.setValor(res.getDouble("valor"));
				produto.setCategoria(c2);
				
				listaProdutos.add(produto);
			}
			return listaProdutos;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar obter produtos ] : " + e.getMessage());
			return null;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public void excluirProduto(Produto produto) {
		String query = "UPDATE produto SET status = 0 WHERE id_produto = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, produto.getId());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao tentar excluir produto ] : " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Produto> listarProduto() {
		String query = "Select p.*, c.descricao from produto p inner join estoque e on p.id_produto = e.produto inner join categoria c on c.id_categoria = p.categoria where p.status = 1;";
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			ArrayList<Produto> listaProduto = new ArrayList<Produto>();
			
			while (res.next()) {
				
				Produto produto = new Produto();
				Categoria c = new Categoria();

				c.setDescricao(res.getString("descricao"));
				
				produto.setId(res.getInt("id_produto"));
				produto.setTitulo(res.getString("titulo"));
				produto.setValor(res.getDouble("valor"));
				produto.setCategoria(c);
				
				
				listaProduto.add(produto);
				
			}
			return listaProduto;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar clientes ] : " + e.getMessage());
			return null;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public ArrayList<Produto> listarProdutoPorNome() {
		String query = "Select * from produto";
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			ArrayList<Produto> listaProduto = new ArrayList<Produto>();
			
			while (res.next()) {
				
				Produto produto = new Produto();
			
				produto.setTitulo(res.getString("titulo"));		
				listaProduto.add(produto);
				
			}
			return listaProduto;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar produtos por nome ] : " + e.getMessage());
			return null;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
