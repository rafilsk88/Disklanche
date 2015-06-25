	package br.jotas.sc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


import br.jotas.sc.model.Estoque;
import br.jotas.sc.model.Produto;
import br.jotas.sc.model.RealizarPedido;

public class ConsultaProdutoTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_PRODUTO = 0;
	private static final int COL_VALOR = 1;
	private static final int COL_CATEGORIA = 2;
	private List<Produto> valores;

	public ConsultaProdutoTableModel(List<Produto> valores) {
		this.valores = new ArrayList<Produto>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 3;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME_PRODUTO) return "Nome do Produto";
		if (column == COL_VALOR) return "Valor";
		if (column == COL_CATEGORIA) return "Categoria";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Produto produto = valores.get(row);
		if (column == COL_NOME_PRODUTO) return produto.getTitulo();
		if (column == COL_VALOR) return produto.getValor();
		if (column == COL_CATEGORIA) return produto.getCategoria().getDescricao();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Produto produto = valores.get(rowIndex);
		if (columnIndex == COL_NOME_PRODUTO) produto.setTitulo(aValue.toString());
		if (columnIndex == COL_VALOR) produto.setValor(Double.parseDouble(aValue.toString()));
		if (columnIndex == COL_CATEGORIA) produto.getCategoria().setDescricao(aValue.toString());
	}

	public Class<?> getColumnClass(int columnIndex) {		
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return false;
	}
	
	public Produto get(int row) {
		return valores.get(row);
	}
}
