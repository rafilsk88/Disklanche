package br.disklanche.sc.View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import br.jotas.sc.controller.ProdutoController;
import br.jotas.sc.dao.EstoqueDAO;
import br.jotas.sc.model.Estoque;
import br.jotas.sc.model.Produto;
import br.jotas.sc.util.AtualizaEstoqueTableModel;
import br.jotas.sc.util.ConsultaProdutoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class ConsultaProdutoUI extends JInternalFrame {
	private JTextField jtfNome;
	public JTable table;
	private Produto produto;
	private static ConsultaProdutoUI instancia;
	
	
	public static ConsultaProdutoUI obterInstancia() {
		if (instancia == null) {
			instancia = new ConsultaProdutoUI();
		}
		return instancia;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaProdutoUI frame = ConsultaProdutoUI.obterInstancia();
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
	public ConsultaProdutoUI() {
		
		
		setTitle("Consulta de Produtos");
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 791, 523);
		
		JLabel lblNome = new JLabel("Nome : ");
		
		jtfNome = new JTextField();
		jtfNome.setColumns(10);
		final JComboBox comboBox = new JComboBox();
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoController pc = new ProdutoController();
				table.setModel(new ConsultaProdutoTableModel(pc.procurarProdutoPorNome(jtfNome.getText(),(String) comboBox.getSelectedItem())));
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Resultado da busca", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnEditar = new JButton("Editar Produto");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produto p = (Produto) new ConsultaProdutoTableModel(new ProdutoController().listarProduto()).get(table.getSelectedRow());
				
				CadastroProdutoUI cad = new CadastroProdutoUI(p);
				cad.setFocusable(true);
				cad.moveToFront();
				cad.requestFocus();
				PrincipalUI.obterInstancia().getContentPane().add(cad,0);
				cad.setVisible(true);
			}
		});
		
		
		JLabel lblCategoria = new JLabel("Categoria :");
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Comida", "Bebida"}));
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				table.setModel(new ConsultaProdutoTableModel(new ProdutoController().listarProduto()));
				
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produto p = (Produto) new ProdutoController().listarProduto().get(table.getSelectedRow());
				try {
					new ProdutoController().excluirProduto(p);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCategoria)
								.addComponent(lblNome))
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(141, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 387, Short.MAX_VALUE)
									.addComponent(btnProcurar)
									.addGap(56))))))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(btnExcluir)
					.addPreferredGap(ComponentPlacement.RELATED, 355, Short.MAX_VALUE)
					.addComponent(btnAtualizar)
					.addGap(18)
					.addComponent(btnEditar)
					.addGap(18)
					.addComponent(btnCancelar)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
							.addComponent(btnProcurar)
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCategoria)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnEditar)
						.addComponent(btnAtualizar)
						.addComponent(btnExcluir))
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(42, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new ConsultaProdutoTableModel(new ProdutoController().listarProduto()));
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(251);
		table.getColumnModel().getColumn(1).setPreferredWidth(79);
		table.getColumnModel().getColumn(2).setPreferredWidth(77);
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
