package br.disklanche.sc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.disklanche.sc.Jdbc.ConnectionFactory;
import br.disklanche.sc.Model.Categoria;
import br.disklanche.sc.Model.Estoque;
import br.disklanche.sc.Model.ItensDoPedido;
import br.disklanche.sc.Model.Produto;

public class EstoqueDAO {

	private static EstoqueDAO instance;
	private Connection con;

	public EstoqueDAO() {
		con = ConnectionFactory.getConnection();
	}

	public static EstoqueDAO getInstance() {
		if (instance == null) {
			instance = new EstoqueDAO();
		}
		return instance;
	}

	public ArrayList<Estoque> listarEstoque() {
		String query = "Select * from estoque";
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			ArrayList<Estoque> listarEstoque = new ArrayList<Estoque>();
			while (res.next()) {
				Estoque estoque = new Estoque();
				estoque.getProduto().setTitulo(res.getString("titulo"));
				estoque.getProduto().setValor(res.getDouble("valor"));
				estoque.setEstoqueAtual(res.getInt("estoqueAtual"));
				estoque.getProduto().getCategoria()
						.setDescricao(res.getString("descricao"));
				listarEstoque.add(estoque);
			}
			return listarEstoque;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar produtos ] : "
					+ e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Estoque> AtualizarEstoque() {
		String query = "Select p.*, e.estoqueAtual, c.descricao from produto p inner join estoque e on p.id_produto = e.produto inner join categoria c on c.id_categoria = p.categoria";
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			ArrayList<Estoque> listarEstoque = new ArrayList<Estoque>();
			while (res.next()) {
				Estoque estoque = new Estoque();
				Produto produto = new Produto();
				Categoria c = new Categoria();

				c.setDescricao(res.getString("descricao"));
				
				produto.setId(res.getInt("id_produto"));
				produto.setTitulo(res.getString("titulo"));
				produto.setValor(res.getDouble("valor"));
				produto.setCategoria(c);
				estoque.setEstoqueAtual((int) res.getDouble("estoqueAtual"));
				estoque.setProduto(produto);
				
				listarEstoque.add(estoque);
			}
			return listarEstoque;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar produtos ] : "
					+ e.getMessage());
			return null;
		}
	}

	
	public Estoque obterProdutoPorNome(String s) {
		String query = "SELECT * FROM produto p, estoque e WHERE p.titulo = ? AND p.id_produto = e.produto";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, "%" + s + "%");
			ResultSet res = stm.executeQuery();
			ArrayList<Estoque> listaEstoque = new ArrayList<Estoque>();
			while (res.next()) {
				Estoque estoque = new Estoque();
				estoque.getProduto().setTitulo(res.getString("titulo"));
				estoque.getProduto().setValor(res.getDouble("valor"));
				estoque.setEstoqueAtual(res.getInt("estoqueAtual"));
				estoque.getProduto().getCategoria()
						.setDescricao(res.getString("de_descricao"));
				listaEstoque.add(estoque);
			}
			return listaEstoque.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar produtos ] : "
					+ e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Estoque> obterProdutoPorCategoria(int s) {
		String query = "SELECT * FROM produto WHERE categoria like ? ";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, s);
			ResultSet res = stm.executeQuery();
			ArrayList<Estoque> listaEstoque = new ArrayList<Estoque>();
			while (res.next()) {
				Estoque estoque = new Estoque();
				estoque.getProduto().setTitulo(res.getString("titulo"));
				estoque.getProduto().setValor(res.getDouble("valor"));
				estoque.setEstoqueAtual(res.getInt("estoqueAtual"));
				estoque.getProduto().getCategoria()
						.setDescricao(res.getString("de_descricao"));
				listaEstoque.add(estoque);
			}
			return listaEstoque;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar produtos ] : "
					+ e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void inserirEstoque(Estoque estoque) {
		String query = "UPDATE estoque SET estoqueAtual = ? WHERE produto = ?";
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(query);
			stm.setInt(1, estoque.getEstoqueAtual());
			stm.setInt(2, estoque.getProduto().getId());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao salvar estoque editado ] : "
						+ e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} 
	}
	
	public void Atualizar(ArrayList<ItensDoPedido> listaItens) {

		for (int i=0; i<listaItens.size(); i++){
		
			String query = "UPDATE estoque SET estoqueAtual = ? WHERE produto = ?";
			PreparedStatement stm;
		
			try {
				stm = con.prepareStatement(query);
				stm.setInt(1, listaItens.get(i).getQuantidade());
				stm.setInt(2, listaItens.get(i).getId());
				stm.execute();
				con.commit();
			} catch (SQLException e) {
				try {
					con.rollback();
					System.out.println("[ Erro ao salvar estoque editado ] : "
						+ e.getMessage());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} 
		}
	}
	
	public void Atualizar2(ArrayList<ItensDoPedido> listaItens) {

		for (int i=0; i<listaItens.size(); i++){
			String select = "select estoqueAtual from estoque where produto = ?";
			String query = "UPDATE estoque SET estoqueAtual = ? WHERE produto = ?";
			PreparedStatement stm;
			int qt_atual=0;
			try {
				stm = con.prepareStatement(select);
				stm.setInt(1, listaItens.get(i).getProduto().getId());
				ResultSet res = stm.executeQuery();
				res.next();
				qt_atual = res.getInt("estoqueAtual");
				stm = con.prepareStatement(query);
				stm.setInt(1, (qt_atual-listaItens.get(i).getQuantidade()));
				stm.setInt(2, listaItens.get(i).getProduto().getId());
				stm.execute();
				con.commit();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
