package br.jotas.sc.controller;

import java.sql.SQLException;

import br.jotas.sc.dao.NecessidadeDAO;
import br.jotas.sc.model.Necessidade;
import br.jotas.sc.model.Produto;

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
