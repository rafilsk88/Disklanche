package br.disklanche.sc.Util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.disklanche.sc.Model.Estoque;
import br.disklanche.sc.Model.Necessidade;

public class ConsultaNecessidadeTableModel extends AbstractTableModel  {

	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_PRODUTO = 0;
	private static final int COL_QUANTIDADE_ATUAL = 1;
	private static final int COL_QUANTIDADE_NECESSARIA = 2;
	private List<Estoque> valores;

	public ConsultaNecessidadeTableModel(List<Estoque> valores) {
		this.valores = new ArrayList<Estoque>(valores);
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 3;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME_PRODUTO) return "Nome do Produto";
		if (column == COL_QUANTIDADE_ATUAL) return "Quantidade Atual";
		if (column == COL_QUANTIDADE_NECESSARIA) return "Quantidade à Comprar";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Estoque compras = valores.get(row);
		if (column == COL_NOME_PRODUTO) return compras.getProduto().getTitulo();
		if (column == COL_QUANTIDADE_ATUAL) return compras.getEstoqueAtual();
		if (column == COL_QUANTIDADE_NECESSARIA) return (new Necessidade().getQuantidade() - COL_QUANTIDADE_ATUAL);
		return ""; 
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Estoque compras = valores.get(rowIndex);
		if (columnIndex == COL_NOME_PRODUTO) compras.getProduto().setTitulo(aValue.toString());
		if (columnIndex == COL_QUANTIDADE_ATUAL) compras.setQuantidadeComprada(Integer.parseInt(aValue.toString()));
		if (columnIndex == COL_QUANTIDADE_NECESSARIA) compras.getProduto().getCategoria().setDescricao(aValue.toString());;
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
