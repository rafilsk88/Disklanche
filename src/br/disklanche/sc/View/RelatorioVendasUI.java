	package br.disklanche.sc.View;

	import java.awt.EventQueue;

	import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import br.disklanche.sc.Controller.ProdutoController;
import br.disklanche.sc.Util.RelatorioVendasTableModel;

	import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

	@SuppressWarnings("serial")
	public class RelatorioVendasUI extends JInternalFrame {

		public JTable jtRelatorioVendas;
		
		public RelatorioVendasUI(Date periodoInicial, Date periodoFinal) {
			setClosable(true);
			setTitle("Relat\u00F3rio de Vendas");
			setBounds(100, 100, 450, 300);
			
			JLabel jlLocacoesFilme = new JLabel("Vendas no per\u00EDodo de :");
			
			JLabel jlMesAnoInicial = new JLabel("M\u00EAs / Ano");
			
			JScrollPane jspRelatorioVendas = new JScrollPane();
			
			JButton jbSair = new JButton("Sair");
			jbSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			
			JLabel jlMesAnoFinal = new JLabel("M\u00EAs / Ano");
			
			JLabel jlLigacao = new JLabel("a");
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(jspRelatorioVendas, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(jlLocacoesFilme)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jlMesAnoInicial)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jlLigacao)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jlMesAnoFinal))
							.addComponent(jbSair))
						.addContainerGap())
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(jlLocacoesFilme)
							.addComponent(jlMesAnoInicial)
							.addComponent(jlMesAnoFinal)
							.addComponent(jlLigacao))
						.addGap(18)
						.addComponent(jspRelatorioVendas, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jbSair)
						.addContainerGap())
			);
			jtRelatorioVendas = new JTable();
			jtRelatorioVendas.setModel(new RelatorioVendasTableModel(new ProdutoController().listarProduto(), periodoInicial, periodoFinal));
			jtRelatorioVendas.getColumnModel().getColumn(0).setPreferredWidth(200);
			jtRelatorioVendas.getColumnModel().getColumn(1).setPreferredWidth(100);
			jtRelatorioVendas.getColumnModel().getColumn(2).setPreferredWidth(100);
			jtRelatorioVendas.getColumnModel().getColumn(3).setPreferredWidth(100);
			jspRelatorioVendas.setViewportView(jtRelatorioVendas);
			getContentPane().setLayout(groupLayout);

		}
	}

