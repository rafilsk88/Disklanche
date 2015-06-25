package br.disklanche.sc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Categoria;
import br.jotas.sc.model.Cliente;

public class CategoriaDAO {

	private static CategoriaDAO instance;
	private Connection con;
	
	public CategoriaDAO() {
		con = ConnectionFactory.getConnection();
	}

	public static CategoriaDAO getInstance() {
		if (instance == null) {
			instance = new CategoriaDAO();
		}
		return instance;
	}

	
	public void salvarCategoria(Categoria categoria) {
		String query = "INSERT INTO categoria (descricao) VALUES (?)";
		try {
			PreparedStatement stm = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS );
			stm.setString(1, categoria.getDescricao());
			stm.execute();
			 ResultSet rs = stm.getGeneratedKeys();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao tentar salvar Categoria ] : " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<Categoria> listarCategorias() { 
		String query = "select * From categoria";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			ArrayList<Categoria> listarCategoria = new ArrayList<Categoria>();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id_categoria"));
				categoria.setDescricao(rs.getString("descricao"));
				
				listarCategoria.add(categoria);
				}
				return listarCategoria;
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar listar categorias ] : " + e.getMessage());
				return null;
				}finally{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}


	public ArrayList<Categoria> listarNomeCategorias() { 
		String query = "select descricao from categoria";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			ArrayList<Categoria> listarCategoria = new ArrayList<Categoria>();
	
			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setDescricao(rs.getString("descricao"));
				
				listarCategoria.add(categoria);
				}
				return listarCategoria;
			} catch (SQLException e) {
				System.out.println("[ Erro ao tentar listar categorias por nome ] : " + e.getMessage());
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


