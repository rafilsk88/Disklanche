package br.disklanche.sc.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.disklanche.sc.Controller.RealizarPedidoController;
import br.disklanche.sc.Model.Produto;
import br.disklanche.sc.Model.RealizarPedido;

public class RelatorioVendasTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_PRODUTO = 0;
	private static final int COL_QUANTIDADE_VENDIDA = 1;
	private static final int COL_VALOR_UNITARIO = 2;
	private static final int COL_VALOR_TOTAL = 3;
	private Date dataInicio;
	private Date dataFim;

	private List<RealizarPedido> valores;

	public RelatorioVendasTableModel(ArrayList<RealizarPedido> arrayList, Date dataInicio, Date dataFim) {
		this.valores = new ArrayList<RealizarPedido>(arrayList);
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public int getRowCount() {		
		return valores.size();
	}

	public int getColumnCount() {		
		return 4;
	}

	public String getColumnName(int column) {		
		if (column == COL_NOME_PRODUTO) return "Nome Produto";
		if (column == COL_QUANTIDADE_VENDIDA) return "Quantidade";
		if (column == COL_VALOR_UNITARIO) return "Valor Unitário";
		if (column == COL_VALOR_TOTAL) return "Valor Total";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		RealizarPedido pedido = valores.get(row);
		if (column == COL_NOME_PRODUTO) return pedido.getProduto().getTitulo();
		if (column == COL_QUANTIDADE_VENDIDA) return new RealizarPedidoController().quantidadeProdutosVendidosPorPeriodo(pedido.getProduto().getId(), dataInicio, dataFim);
		if (column == COL_VALOR_UNITARIO) return pedido.getProduto().getValor();
		if (column == COL_VALOR_TOTAL) return (new RealizarPedidoController().quantidadeProdutosVendidosPorPeriodo(pedido.getProduto().getId(), dataInicio, dataFim) * pedido.getProduto().getValor());
		return ""; 
	}

	public Class<?> getColumnClass(int columnIndex) {		
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return false;
	}
	
	public RealizarPedido get(int row) {
		return valores.get(row);
	}
}
