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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;
import javax.swing.JSpinner;

import br.jotas.sc.controller.AtualizaEstoqueController;
import br.jotas.sc.controller.ClienteController;
import br.jotas.sc.controller.ProdutoController;
import br.jotas.sc.dao.EstoqueDAO;
import br.jotas.sc.model.Estoque;
import br.jotas.sc.model.Produto;
import br.jotas.sc.util.AtualizaEstoqueTableModel;
import br.jotas.sc.util.ClienteTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AtualizaEstoque extends JInternalFrame {
	private JTable tableAtualizaEstoque;
	private JTextField jtfProduto;
	private JTextField jtfCategoria;
	private JTextField jtfQuantidade;

	private JSpinner spinner_InserirRemover;	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaClienteUI frame = new ConsultaClienteUI();
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
	public AtualizaEstoque() {
		setTitle("Atualizar Estoque");
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 672, 471);
		
		JLabel lblNome = new JLabel("");
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Resultado do estoque", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JButton btnRealizarCadastro = new JButton("Atualizar Estoque");
		btnRealizarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Estoque e1 = new Estoque();
				
					try{
						e1 = new  AtualizaEstoqueTableModel(new EstoqueDAO().AtualizarEstoque()).get(tableAtualizaEstoque.getSelectedRow());
						e1.setEstoqueAtual((Integer) spinner_InserirRemover.getValue() + e1.getEstoqueAtual());
					
						AtualizaEstoqueController aec = new AtualizaEstoqueController();
						aec.AtualizarEstoque(e1);
				 
						tableAtualizaEstoque.setModel(new AtualizaEstoqueTableModel(new EstoqueDAO().AtualizarEstoque()));
						jtfProduto.setText("");
						jtfCategoria.setText("");
						jtfQuantidade.setText("");
					
						spinner_InserirRemover.setValue(0);
						
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Selecione um produto para atualizar o estoque.");
						spinner_InserirRemover.setValue(0);
						}
					}
					
			});
		
		JLabel lblNewLabel = new JLabel("Produto");
		
		jtfProduto = new JTextField();
		jtfProduto.setEditable(false);
		jtfProduto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Categoria");
		
		jtfCategoria = new JTextField();
		jtfCategoria.setEditable(false);
		jtfCategoria.setColumns(10);
		
		JLabel lblQuantAtual = new JLabel("Quant. Atual");
		
		jtfQuantidade = new JTextField();
		jtfQuantidade.setEditable(false);
		jtfQuantidade.setColumns(10);
		
		JLabel lblInserirremover = new JLabel("Inserir/Remover");
		
		spinner_InserirRemover = new JSpinner();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(378)
					.addComponent(lblNome)
					.addContainerGap(278, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jtfCategoria))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jtfProduto, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblQuantAtual)
						.addComponent(lblInserirremover))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jtfQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_InserirRemover, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(144, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnRealizarCadastro)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE))
					.addGap(29))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNome)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(jtfProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuantAtual)
						.addComponent(jtfQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(jtfCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInserirremover)
						.addComponent(spinner_InserirRemover, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnRealizarCadastro)
					.addGap(124))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		
		tableAtualizaEstoque = new JTable();
		tableAtualizaEstoque.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Estoque e2 = new  AtualizaEstoqueTableModel(new EstoqueDAO().AtualizarEstoque()).get(tableAtualizaEstoque.getSelectedRow());
				jtfProduto.setText(e2.getProduto().getTitulo());
				jtfCategoria.setText(e2.getProduto().getCategoria().getDescricao());
				jtfQuantidade.setText(Integer.toString(e2.getEstoqueAtual()));
			}
		});
		
		tableAtualizaEstoque.setModel(new AtualizaEstoqueTableModel(new EstoqueDAO().AtualizarEstoque()));
		
		tableAtualizaEstoque.getColumnModel().getColumn(0).setPreferredWidth(235);
		tableAtualizaEstoque.getColumnModel().getColumn(1).setPreferredWidth(77);
		scrollPane.setViewportView(tableAtualizaEstoque);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
