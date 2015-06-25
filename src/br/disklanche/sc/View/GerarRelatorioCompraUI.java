package br.disklanche.sc.View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;

public class GerarRelatorioCompraUI extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerarRelatorioCompraUI frame = new GerarRelatorioCompraUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GerarRelatorioCompraUI() {
		setTitle("Relat\u00F3rio de Compras");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JLabel lblPeriodo = new JLabel("Per\u00EDodo");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblDiaDaSemana = new JLabel("Dia da semana :");
		lblDiaDaSemana.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JComboBox comboBoxDiaSemana = new JComboBox();
		comboBoxDiaSemana.setModel(new DefaultComboBoxModel(new String[] {"Domingo", "Segunda-Feira", "Ter\u00E7a-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sabado"}));
		
		JButton btnGerar = new JButton("Gerar");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(173)
					.addComponent(lblPeriodo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(161))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(72)
					.addComponent(lblDiaDaSemana, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(comboBoxDiaSemana, 0, 170, Short.MAX_VALUE)
					.addGap(66))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(326, Short.MAX_VALUE)
					.addComponent(btnGerar, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(lblPeriodo)
					.addGap(67)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDiaDaSemana)
						.addComponent(comboBoxDiaSemana, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(69)
					.addComponent(btnGerar)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

}
