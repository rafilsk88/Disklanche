package br.disklanche.sc.View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;







import br.disklanche.sc.Controller.CategoriaController;
import br.disklanche.sc.Controller.ProdutoController;
import br.disklanche.sc.Model.Categoria;
import br.disklanche.sc.Model.Produto;
import br.disklanche.sc.Util.ConsultaProdutoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CadastroProdutoUI extends JInternalFrame {
	private JTextField jtNome;
	private JTextField jtValor;
	private Categoria categoria;

	private JComboBox comboBoxCategoria;

	private ArrayList<Categoria> listaCategorias = new CategoriaController()
			.listaCategorias();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProdutoUI frame = new CadastroProdutoUI(null);
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
	public CadastroProdutoUI(final Produto produto) {
		setTitle("Cadastro de Produtos");
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 408, 227);

		JLabel lblNome = new JLabel("Nome :");
		jtValor = new JTextField();

		jtNome = new JTextField();
		jtNome.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria :");

		DefaultComboBoxModel<Categoria> modelCategoria = new DefaultComboBoxModel<Categoria>();
		for (Categoria categoria : listaCategorias) {
			modelCategoria.addElement(categoria);
		}
		comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setModel(modelCategoria);

		if (produto != null) {
			jtNome.setText(produto.getTitulo());
			jtValor.setText(produto.getValor().toString());
			comboBoxCategoria.setSelectedItem(produto.getCategoria()
					.getDescricao());

		}

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (produto != null) {

					// Editar

					produto.setTitulo(jtNome.getText());
					produto.setValor(Double.parseDouble(jtValor.getText()));
					produto.setCategoria((Categoria) comboBoxCategoria
							.getSelectedItem());
					produto.setStatus(produto.getStatus().ATIVO);

					ProdutoController proc = new ProdutoController();

					try {
						proc.salvarProduto(produto);
						JOptionPane.showMessageDialog(null,
								" Editado com sucesso. ");
						dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					ConsultaProdutoUI.obterInstancia().table
							.setModel(new ConsultaProdutoTableModel(
									new ProdutoController().listarProduto()));
				} else {

					// Salvar

					Produto produto = new Produto();
					ProdutoController produtoController = new ProdutoController();

					produto.setTitulo(jtNome.getText());
					try {
						produto.setValor(Double.parseDouble(jtValor.getText()));
					} catch (NumberFormatException e) {
						e.getMessage();
					}
					produto.setCategoria((Categoria) comboBoxCategoria
							.getSelectedItem());
					produto.setStatus(produto.getStatus().ATIVO);

					for (int i = 0; i < produtoController
							.listarProduto().size(); i++) {
						if (produtoController.listarProduto().get(i)
								.getTitulo().equalsIgnoreCase(jtNome.getText())) {
							JOptionPane.showMessageDialog(null,
									"Produto já cadastrado!");
							break;
						} else {
							try {
								produtoController.salvarProduto(produto);
								JOptionPane.showMessageDialog(null, "Produto: "
										+ produto.getTitulo()
										+ " cadastrado com sucesso");
								dispose();
								break;
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null,
										e1.getMessage());
								break;
							}

						}
					}
				}

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		JLabel lblValor = new JLabel("Valor :");

		jtValor.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(178, Short.MAX_VALUE)
					.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNome)
						.addComponent(lblValor)
						.addComponent(lblCategoria))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(comboBoxCategoria, Alignment.LEADING, 0, 179, Short.MAX_VALUE)
								.addComponent(jtNome, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
							.addGap(75))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jtValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(jtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValor)
						.addComponent(jtValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategoria))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
