package br.disklanche.sc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.jotas.sc.jdbc.ConnectionFactory;
import br.jotas.sc.model.Necessidade;
import br.jotas.sc.model.Produto;

public class NecessidadeDAO {
	
	private static NecessidadeDAO instance;
	private Connection con;

	public NecessidadeDAO() {
		con = ConnectionFactory.getConnection();
	}

	public static NecessidadeDAO getInstance() {
		if (instance == null) {
			instance = new NecessidadeDAO();
		}
		return instance;
	}
	
	public void salvarNecessidade(Necessidade necessidade) {
		String query = "INSERT INTO necessidade n, Necessidade in (n.diaSemana, in.id_itensNecessidade, in.produto, in.quantidade) VALUES (?,?,?,?) WHERE n.id_necessidade = in.necessidade";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, necessidade.getDiaDaSemana());
			stm.setInt(2, necessidade.getId());
			stm.setString(3, necessidade.getEstoque().getProduto().getTitulo());
			stm.setInt(4, necessidade.getQuantidade());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao tentar salvar necessidade ] : " + e.getMessage());
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

	public void editarNecessidade(Necessidade necessidade) {
		String query = "UPDATE Necessidade SET produto = ?, quantidade = ? WHERE id_itensNecessidade = ?";
		PreparedStatement stm;
		try {
			stm = con.prepareStatement(query);
			stm.setString(1, necessidade.getEstoque().getProduto().getTitulo());
			stm.setInt(2, necessidade.getQuantidade());
			stm.setInt(3, necessidade.getId());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao salvar necessidade editada ] : " + e.getMessage());
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

	public void excluirNecessidade(int id) {
		String query = "DELETE FROM necessidade WHERE id_necessidade = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao tentar excluir necessidade ] : " + e.getMessage());
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
	
	public void mostrarQuantidadeNecessaria(int id){
		String query = "SELECT FROM ItensNecessidade in, estoque e (in.quantidade - e.estoqueAtual) WHERE in.produto = e.produto";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao tentar buscar quantidade necessaria ] : " + e.getMessage());
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
	
	public void mostrarQuantidadeNecessarias(int id){
		String query = "SELECT FROM Necessidade in, estoque e (in.quantidade - e.estoqueAtual) WHERE in.produto = e.produto";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao tentar buscar quantidade necessaria ] : " + e.getMessage());
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
	
}
