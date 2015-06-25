package br.disklanche.sc.View;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

public class NecessidadeUI extends JInternalFrame{
	public NecessidadeUI() {
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setTitle("Necessidade Di\u00E1ria");
		setToolTipText("");
		
		JLabel lblDia = new JLabel("Dia");
		
		JComboBox cbDiaSemana = new JComboBox();
		
		JLabel lblProduto = new JLabel("Produto");
		
		JComboBox cbProduto = new JComboBox();
		
		JButton btnInserir = new JButton("Inserir");
		
		JPanel panel = new JPanel();
		
		JButton btnExcluir = new JButton("Excluir");
		
		JButton btnRealizarCadastro = new JButton("Realizar Cadastro");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDia)
								.addComponent(lblProduto))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(cbDiaSemana, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
									.addGap(35)
									.addComponent(btnInserir))
								.addComponent(cbProduto, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(btnExcluir)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRealizarCadastro))
							.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)))
					.addGap(13))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDia)
						.addComponent(cbDiaSemana, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInserir))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduto)
						.addComponent(cbProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir)
						.addComponent(btnRealizarCadastro))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
