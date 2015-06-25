package br.disklanche.sc.Controller;

import java.text.ParseException;
import java.util.ArrayList;

import br.disklanche.sc.DAO.CategoriaDAO;
import br.disklanche.sc.Exception.CampoObrigatorioException;
import br.disklanche.sc.Model.Categoria;

	public class CategoriaController {
	
		public void salvarCategoria(Categoria categoria) throws NullPointerException,
			ParseException, Exception {
			validaDados(categoria);
			CategoriaDAO dao = new CategoriaDAO();
			dao.salvarCategoria(categoria);
		}
	
		public ArrayList<Categoria> listaCategorias() {
			CategoriaDAO dao = new CategoriaDAO();
			return dao.listarCategorias();
		}

		public ArrayList<Categoria> listaCategoriasPorNome() {
			CategoriaDAO dao = new CategoriaDAO();
			return dao.listarNomeCategorias();
		}

		public void validaDados(Categoria categoria) throws CampoObrigatorioException,
		NullPointerException, Exception {
		if (categoria.getDescricao().isEmpty())
		throw new CampoObrigatorioException("Categoria");
		}
}
