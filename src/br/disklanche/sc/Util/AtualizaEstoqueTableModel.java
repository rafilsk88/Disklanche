package br.disklanche.sc.Util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.disklanche.sc.Model.Estoque;

//Transformar em CONSULTAESTOQUE
public class AtualizaEstoqueTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_TITULO = 0;
	private static final int COL_QUANTIDADEATUAL = 1;
	private List<Estoque> valores;

	public AtualizaEstoqueTableModel(List<Estoque> valores) {
		this.valores = new ArrayList<Estoque>(valores);
	}
	
	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 2;
	}

	public String getColumnName(int column) {		
		if (column == COL_TITULO) return "Nome do Produto";
		if (column == COL_QUANTIDADEATUAL) return "Quantidade Atual";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Estoque estoque = valores.get(row);
		if (column == COL_TITULO) return estoque.getProduto().getTitulo();
		if (column == COL_QUANTIDADEATUAL) return estoque.getEstoqueAtual();
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Estoque estoque = valores.get(rowIndex);
		if (columnIndex == COL_TITULO) estoque.getProduto().setTitulo(aValue.toString());
		if (columnIndex == COL_QUANTIDADEATUAL) estoque.setEstoqueAtual(Integer.parseInt(aValue.toString()));
	}

	public Class<?> getColumnClass(int columnIndex) {		
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return false;
	}
	
	public Estoque get(int row) {
		return valores.get(row);
	}
}
