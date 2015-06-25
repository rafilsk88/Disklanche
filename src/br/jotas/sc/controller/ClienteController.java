package br.jotas.sc.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import br.jotas.sc.dao.ClienteDAO;
import br.jotas.sc.exception.CampoObrigatorioException;
import br.jotas.sc.model.Cliente;

public class ClienteController {

	public void salvarCliente(Cliente cliente) throws NullPointerException,
			ParseException, Exception {
		validaDados(cliente);
		ClienteDAO dao = new ClienteDAO();
		if (cliente.getId() != 0) {
			dao.editarCliente(cliente);
		} else {
			dao.salvarCliente(cliente);
		}
	}

	public Cliente obterCliente(int id) {
		ClienteDAO dao = new ClienteDAO();
		return dao.obterCliente(id);
	}

	public ArrayList<Cliente> procurarCliente(String nome) throws ArrayIndexOutOfBoundsException{
		ClienteDAO dao = new ClienteDAO();
		return dao.procurarCliente(nome);
	}

	public ArrayList<Cliente> listarClientes() {
		ClienteDAO dao = new ClienteDAO();
		return dao.listarClientes();
	}

	public void excluirCliente(int id) throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		dao.excluirCliente(id);
	}

	public void validaDados(Cliente cliente) throws CampoObrigatorioException,
			NullPointerException, Exception {
		if (cliente.getCpf().isEmpty())
			throw new CampoObrigatorioException("CPF");
		if (cliente.getTelefone().isEmpty())
			throw new CampoObrigatorioException("Telefone");
		if (cliente.getNome().isEmpty() || cliente.getNome().equals(""))
			throw new CampoObrigatorioException("Nome");
		if (cliente.getTelefone() == null)
			throw new CampoObrigatorioException("Telefone");
		if (cliente.getCpf() != null && cliente.getCpf().length() < 11)
			throw new Exception("CPF inválido");
	}
}
