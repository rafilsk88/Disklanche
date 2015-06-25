package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.controller.RealizarPedidoController;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.model.RealizarPedido;

public class ConsultaClienteTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME = 0;
	private static final int COL_TELEFONE = 1;
	private static final int COL_ENDERECO = 2;
	private static final int COL_NUMERO_PEDIDOS = 3;

	private List<Cliente> valores;

	public ConsultaClienteTableModel(List<Cliente> valores) {
		this.valores = new ArrayList<Cliente>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 4;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME) return "Nome do Cliente";
		if (column == COL_TELEFONE) return "Telefone";
		if (column == COL_ENDERECO) return "Endereço";
		if (column == COL_NUMERO_PEDIDOS) return "Nº Pedidos";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Cliente cliente = valores.get(row);
		ArrayList<RealizarPedido> pedidosFeitos = new RealizarPedidoController().listarPedidoPorCliente(cliente.getId());
		if (column == COL_NOME) return cliente.getNome();
		if (column == COL_TELEFONE) return cliente.getTelefone();
		if (column == COL_ENDERECO) return cliente.getEndereco();
		if (column == COL_NUMERO_PEDIDOS) return pedidosFeitos.size();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente cliente = valores.get(rowIndex);
		ArrayList<RealizarPedido> pedidosFeitos = new RealizarPedidoController().listarPedidoPorCliente(cliente.getId());
		if (columnIndex == COL_NOME) cliente.setNome(aValue.toString());
		if (columnIndex == COL_TELEFONE) cliente.setTelefone(aValue.toString());
		if (columnIndex == COL_ENDERECO) cliente.setEndereco(aValue.toString());
		if (columnIndex == COL_NUMERO_PEDIDOS) pedidosFeitos.size();
	}

	public Class<?> getColumnClass(int columnIndex) {		
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return false;
	}
	
	public Cliente get(int row) {
		return valores.get(row);
	}
}
