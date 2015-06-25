package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.model.Cliente;
import br.jotas.sc.model.ItensDoPedido;
import br.jotas.sc.model.Produto;
import br.jotas.sc.view.RealizarPedidoUI;

public class RealizaPedidoTableModel extends AbstractTableModel {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_PRODUTO = 0;
	private static final int COL_QUANTIDADE = 1;
	private static final int COL_VALOR = 2;

	private List<ItensDoPedido> valores;

	public RealizaPedidoTableModel(List<ItensDoPedido> valores) {
		this.valores = new ArrayList<ItensDoPedido>(valores);
	}

	public RealizaPedidoTableModel() {
		super();
	}

	public int getRowCount() {
		return valores.size();
	}

	public int getColumnCount() {
		return 3;
	}

	public void adicionarPedido(ItensDoPedido itens) {
		valores.add(itens);
	}

	public String getColumnName(int column) {
		if (column == COL_NOME_PRODUTO)
			return "Produto";
		if (column == COL_QUANTIDADE)
			return "Quantidade";
		if (column == COL_VALOR)
			return "Valor";
		return "";
	}

	public Object getValueAt(int row, int column) {
		ItensDoPedido itens = valores.get(row);
		if (column == COL_NOME_PRODUTO)
			return itens.getProduto().getTitulo();
		if (column == COL_QUANTIDADE)
			return itens.getQuantidade();
		if (column == COL_VALOR)
			return itens.getValorUnitario();
		return "";
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ItensDoPedido itens = valores.get(rowIndex);
		if (columnIndex == COL_NOME_PRODUTO)
			itens.getProduto().setTitulo(aValue.toString());
		if (columnIndex == COL_QUANTIDADE)
			itens.setQuantidade(Integer.parseInt(aValue.toString()));
		if (columnIndex == COL_VALOR)
			itens.setValorUnitario(Double.parseDouble(aValue.toString()));
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public ItensDoPedido get(int row) {
		return valores.get(row);
	}

}
