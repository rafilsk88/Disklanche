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

import br.jotas.sc.util.DataUtil;
import br.jotas.sc.util.DataUtil.Mes;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class GerarRelatorioVendasUI extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerarRelatorioVendasUI frame = new GerarRelatorioVendasUI();
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
	public GerarRelatorioVendasUI() {
		setTitle("Relat\u00F3rio de Vendas");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 372, 290);
		
		JLabel lblPeriodo = new JLabel("Per\u00EDodo");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblDiaDaSemana = new JLabel("Inicio: M\u00EAs/Ano");
		lblDiaDaSemana.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		final JComboBox jcbAnoInicial = new JComboBox();
		jcbAnoInicial.setModel(new DefaultComboBoxModel(new String[] {"2015", "2014", "2013", "2012"}));
		
		final JComboBox jcbAnoFinal = new JComboBox();
		jcbAnoFinal.setModel(new DefaultComboBoxModel(new String[] {"2015", "2014", "2013", "2012"}));
		
		final JComboBox jcbMesInicial = new JComboBox();
		jcbMesInicial.setModel(new DefaultComboBoxModel(Mes.values()));
		
		final JComboBox jcbMesFinal = new JComboBox();
		jcbMesFinal.setModel(new DefaultComboBoxModel(Mes.values()));
		
		JButton btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date periodoInicial = DataUtil.criarDataCom(1, (Mes) jcbMesInicial.getSelectedItem(), Integer.parseInt(jcbAnoInicial.getSelectedItem().toString()));
				Date periodoFinal = DataUtil.criarDataCom(1, (Mes) jcbMesFinal.getSelectedItem(), Integer.parseInt(jcbAnoFinal.getSelectedItem().toString()));
				RelatorioVendasUI relatorio = new RelatorioVendasUI(periodoInicial, periodoFinal); 
				relatorio.setFocusable(true);
				relatorio.moveToFront();
				relatorio.requestFocus();
				PrincipalUI.obterInstancia().getContentPane().add(relatorio, 0);
				relatorio.setVisible(true);
				dispose();
			}
		});
		
		JLabel lblAno = new JLabel("Fim: M\u00EAs/Ano");
		lblAno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(106)
					.addComponent(lblPeriodo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(228))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDiaDaSemana, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAno, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addGap(14)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(jcbMesFinal, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jcbMesInicial, 0, 101, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGerar, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(jcbAnoFinal, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(jcbAnoInicial, 0, 86, Short.MAX_VALUE)))
					.addGap(97))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(lblPeriodo)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDiaDaSemana)
						.addComponent(jcbAnoInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jcbMesInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAno, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(jcbAnoFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jcbMesFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addComponent(btnGerar)
					.addGap(28))
		);
		getContentPane().setLayout(groupLayout);

	}

}
