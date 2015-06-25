package br.jotas.sc.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.jotas.sc.controller.ProdutoController;
import br.jotas.sc.controller.RealizarPedidoController;
import br.jotas.sc.model.Cliente;
import br.jotas.sc.model.Produto;
import br.jotas.sc.model.RealizarPedido;

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

	private List<Produto> valores;

	public RelatorioVendasTableModel(ArrayList<Produto> arrayList, Date dataInicio, Date dataFim) {
		this.valores = new ArrayList<Produto>(arrayList);
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
		if (column == COL_VALOR_UNITARIO) return "Valor Unit�rio";
		if (column == COL_VALOR_TOTAL) return "Valor Total";
		return "";
	}

	public Object getValueAt(int row, int column) {		
		Produto produto = valores.get(row);
		if (column == COL_NOME_PRODUTO) return produto.getTitulo();
		if (column == COL_QUANTIDADE_VENDIDA) return new RealizarPedidoController().quantidadeProdutosVendidosPorPeriodo(produto.getId(), dataInicio, dataFim);
		if (column == COL_VALOR_UNITARIO) return produto.getValor();
		if (column == COL_VALOR_TOTAL) return (new RealizarPedidoController().quantidadeProdutosVendidosPorPeriodo(produto.getId(), dataInicio, dataFim) * produto.getValor());
		return ""; 
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