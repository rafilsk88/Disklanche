	package br.jotas.sc.util;

	import java.util.ArrayList;
import java.util.List;

	import javax.swing.table.AbstractTableModel;

	import br.jotas.sc.model.Cliente;
import br.jotas.sc.model.ItensDoPedido;
import br.jotas.sc.model.Necessidade;
import br.jotas.sc.model.Produto;
import br.jotas.sc.view.RealizarPedidoUI;

	public class NecessidadeDiariaTableModel extends AbstractTableModel{
		/**
			 * 
			 */
		private static final long serialVersionUID = 1L;
		private static final int COL_NOME_PRODUTO = 0;
		private static final int COL_QUANTIDADE = 1;
		private static final int COL_CATEGORIA = 2;

		private List<Necessidade> valores;

		public NecessidadeDiariaTableModel(List<Necessidade> valores) {
			this.valores = new ArrayList<Necessidade>(valores);
		}

		public NecessidadeDiariaTableModel() {
			super();
		}

		public int getRowCount() {
			return valores.size();
		}

		public int getColumnCount() {
			return 3;
		}

		public void adicionarPedido(Necessidade itens) {
			valores.add(itens);
		}

		public String getColumnName(int column) {
			if (column == COL_NOME_PRODUTO)
				return "Produto";
			if (column == COL_QUANTIDADE)
				return "Quantidade";
			if (column == COL_CATEGORIA)
				return "Valor";
			return "";
		}

		public Object getValueAt(int row, int column) {
			Necessidade itens = valores.get(row);
			if (column == COL_NOME_PRODUTO)
				return itens.getEstoque().getProduto().getTitulo();
			if (column == COL_QUANTIDADE)
				return itens.getQuantidade();
			if (column == COL_CATEGORIA)
				return itens.getEstoque().getProduto().getCategoria().getDescricao();
			return "";
		}

		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			Necessidade itens = valores.get(rowIndex);
			if (columnIndex == COL_NOME_PRODUTO)
				itens.getEstoque().getProduto().setTitulo(aValue.toString());
			if (columnIndex == COL_QUANTIDADE)
				itens.setQuantidade(Integer.parseInt(aValue.toString()));
			if (columnIndex == COL_CATEGORIA)
				itens.getEstoque().getProduto().getCategoria().setDescricao(aValue.toString());
		}

		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public Necessidade get(int row) {
			return valores.get(row);
		}

	}