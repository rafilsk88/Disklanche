package br.disklanche.sc.Controller;

import java.sql.SQLException;

import br.disklanche.sc.DAO.NecessidadeDAO;
import br.disklanche.sc.Model.Necessidade;


public class NecessidadesController {

	public void salvarNecessidade(Necessidade necessidade) throws NullPointerException, Exception {
		NecessidadeDAO dao = new NecessidadeDAO();
		if (necessidade.getId() != 0) {
			dao.editarNecessidade(necessidade);
		} else {
			dao.salvarNecessidade(necessidade);
		}
	}

	public void excluirNecessidade(int id) throws SQLException {
		NecessidadeDAO dao = new NecessidadeDAO();
		dao.excluirNecessidade(id);
	}
	
	public void mostrarQuantidadeNecessaria(int id){
		NecessidadeDAO dao = new NecessidadeDAO();
		dao.mostrarQuantidadeNecessaria(id);
	}
	
	public void mostrarQuantidadeNecessarias(int id){
		NecessidadeDAO dao = new NecessidadeDAO();
		dao.mostrarQuantidadeNecessaria(id);
	}
	
}
