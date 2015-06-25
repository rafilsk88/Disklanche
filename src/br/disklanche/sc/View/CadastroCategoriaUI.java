package br.disklanche.sc.View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import br.jotas.sc.controller.CategoriaController;
import br.jotas.sc.model.Categoria;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroCategoriaUI extends JInternalFrame {
	private JTextField jtTituloCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCategoriaUI frame = new CadastroCategoriaUI();
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
	public CadastroCategoriaUI() {
		setTitle("Cadastro de Categoria");
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 450, 236);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Tipo de categoria",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Categoria categoria = new Categoria();
				categoria.setDescricao(jtTituloCategoria.getText());

				CategoriaController categoriaController = new CategoriaController();

				for (int i = 0; i < categoriaController.listaCategorias()
						.size(); i++) {
					if (categoriaController.listaCategorias().get(i).getDescricao().equalsIgnoreCase(jtTituloCategoria.getText())) {
						JOptionPane.showMessageDialog(null,
								"Categoria já cadastrada!");
						break;
					} else {
						try {
							categoriaController.salvarCategoria(categoria);
							JOptionPane.showMessageDialog(null,
									"Categoria cadastrado com sucesso");
							dispose();
							break;
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							break;
						}
					}
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnSalvar)
																		.addGap(18)
																		.addComponent(
																				btnCancelar))
														.addComponent(
																panel,
																GroupLayout.PREFERRED_SIZE,
																409,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(15, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(30)
								.addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 111,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(
										groupLayout
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnCancelar)
												.addComponent(btnSalvar))
								.addContainerGap(15, Short.MAX_VALUE)));

		JLabel lblTituloDaCategoria = new JLabel("Titulo da categoria :");

		jtTituloCategoria = new JTextField();
		jtTituloCategoria.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addGap(20)
						.addComponent(lblTituloDaCategoria)
						.addGap(18)
						.addComponent(jtTituloCategoria,
								GroupLayout.PREFERRED_SIZE, 245,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(25, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(32)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblTituloDaCategoria)
												.addComponent(
														jtTituloCategoria,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap(123, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
