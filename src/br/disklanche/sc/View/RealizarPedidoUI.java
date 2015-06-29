package br.disklanche.sc.View;

import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SpinnerNumberModel;

import br.disklanche.sc.Controller.AtualizaEstoqueController;
import br.disklanche.sc.Controller.ClienteController;
import br.disklanche.sc.Controller.ProdutoController;
import br.disklanche.sc.Controller.RealizarPedidoController;
import br.disklanche.sc.DAO.EstoqueDAO;
import br.disklanche.sc.Exception.ClienteException;
import br.disklanche.sc.Model.Cliente;
import br.disklanche.sc.Model.Estoque;
import br.disklanche.sc.Model.ItensDoPedido;
import br.disklanche.sc.Model.Produto;
import br.disklanche.sc.Model.RealizarPedido;
import br.disklanche.sc.Util.RealizaPedidoTableModel;


public class RealizarPedidoUI extends JInternalFrame {
	private JTable table;
	private JTextField jtTotal;
	private double valorTotal = 0;
	private ItensDoPedido idp;
	private JSpinner spinner_Quantidade;
	private JComboBox comboBox_Clientes, comboBox_Produtos;
	private RealizaPedidoTableModel rpt = new RealizaPedidoTableModel();
	private ArrayList<Cliente> listarClientes = new ClienteController().listarClientes();
	private ArrayList<Produto> listarProdutos = new ProdutoController().listarProduto();
	private ArrayList<ItensDoPedido> listaItens = new ArrayList<ItensDoPedido>();
	private ArrayList<Estoque> ListarEstoque;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					RealizarPedidoUI frame = new RealizarPedidoUI();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RealizarPedidoUI() 
	{
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Realizar Pedido");
		setBounds(100, 100, 662, 500);

		final ArrayList<ItensDoPedido> itensPedido = new ArrayList<ItensDoPedido>();

		/*
		 * Setando os valores dos Clientes no comboBox
		 */
		DefaultComboBoxModel<Cliente> modelListarCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cliente : listarClientes) {
			modelListarCliente.addElement(cliente);
		}
		
		/*
		 * Setando os valores dos Produtos no comboBox
		 */
		
		DefaultComboBoxModel<Produto> modelListarProduto = new DefaultComboBoxModel<Produto>();
		for (Produto produto : listarProdutos) {
			modelListarProduto.addElement(produto);
		}
		
		JLabel lblCliente = new JLabel("Cliente :");
		JLabel lblProduto = new JLabel("Produto  :");
		
		comboBox_Clientes = new JComboBox();
		comboBox_Clientes.setModel(modelListarCliente);

		comboBox_Produtos = new JComboBox();
		comboBox_Produtos.setModel(modelListarProduto);
		
		/*
		 * Boão Inserir um Objeto produto numa listaItens para armazenar no pedido.
		 */
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Produto produto = (Produto) comboBox_Produtos.getSelectedItem();
				idp = new ItensDoPedido(produto, (Integer) spinner_Quantidade.getValue(), (produto.getValor() * (Integer) spinner_Quantidade.getValue()));
				boolean encontrado= false;
				
				//Tentando verificar se a quantidade de estoque é = ou superior a quantidade tentando ser pedida... Mas não consigo pagar o produto como valor dentro da "quantidadeDoProdutoNoEstoque().
				/*if(new AtualizaEstoqueController().quantidadeDoProdutoNoEstoque() >= spinner_Quantidade.getValue()){
					JOptionPane.showMessageDialog(null, "Quantidade do produto no estoque é insuficiente para realizar esse pedido!");
				}*/
				
					
				try
				{
					/*
					 * Verifica se o campo quantidade esta nulo.
					 */
					if ((Integer)spinner_Quantidade.getValue() == 0) 
					{
						JOptionPane.showMessageDialog(null, "Favor informar a quantidade do produto !");
					}
					else
					{
						//if()
						}
						if(!listaItens.isEmpty())
						{
							for (int i=0; i<listaItens.size(); i++) 
							{
								if (listaItens.get(i).getProduto().getTitulo().equals(idp.getProduto().getTitulo()))
								{
									listaItens.get(i).setQuantidade(listaItens.get(i).getQuantidade()+(Integer) spinner_Quantidade.getValue());
									listaItens.get(i).setValorUnitario(listaItens.get(i).getProduto().getValor()*listaItens.get(i).getQuantidade());
									encontrado = true;
									break;
								}
								else
								{
									encontrado = false;
								}
							}
							if (!encontrado)
							{
								listaItens.add(idp);
							}
						}
						else
						{
							listaItens.add(idp);
						}
						table.setModel(new RealizaPedidoTableModel(listaItens));
						
						valorTotal += produto.getValor() * (Integer) spinner_Quantidade.getValue();
						jtTotal.setText(Double.toString(valorTotal));
						spinner_Quantidade.setValue(0);
					}
				//}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			
		});

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Pedido", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		JButton btnNewButton_1 = new JButton("Finalizar Pedido");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Cliente cliente = (Cliente) comboBox_Clientes.getSelectedItem();
				RealizarPedido pedido = new RealizarPedido();
				AtualizaEstoqueController aec = new AtualizaEstoqueController();
				idp = new ItensDoPedido();
				
				pedido.setCliente(cliente);
				pedido.setValor(valorTotal);
				pedido.setDataPedido(new Date());
				
				RealizarPedidoController rc = new RealizarPedidoController();
				if(!listaItens.isEmpty()){
					try {
						aec.RemoveDoPedido(listaItens);
						rc.salvarPedido(listaItens, cliente, valorTotal);
						JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso.");
						dispose();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Favor inserir produtos!");
				}
			}
		});
		
		spinner_Quantidade = new JSpinner();
		spinner_Quantidade.setModel(new SpinnerNumberModel(new Integer(0),new Integer(0), null, new Integer(1)));

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});


		JLabel lblQuantidade = new JLabel("Quantidade :");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCliente)
							.addGap(18)
							.addComponent(comboBox_Clientes, GroupLayout.PREFERRED_SIZE, 493, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProduto)
							.addGap(18)
							.addComponent(comboBox_Produtos, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(lblQuantidade)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spinner_Quantidade, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnInserir)))
					.addContainerGap(68, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(421, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(18)
					.addComponent(btnNewButton_1)
					.addGap(25))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(11, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 625, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCliente)
						.addComponent(comboBox_Clientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduto)
						.addComponent(comboBox_Produtos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInserir)
						.addComponent(lblQuantidade)
						.addComponent(spinner_Quantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addContainerGap())
		);


		JScrollPane scrollPane = new JScrollPane();

		JLabel lblTotal = new JLabel("Total :");

		jtTotal = new JTextField();
		jtTotal.setEditable(false);
		jtTotal.setColumns(10);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ItensDoPedido idp = listaItens.get(table.getSelectedRow());
				valorTotal -= idp.getValorUnitario();
				
				jtTotal.setText(Double.toString(valorTotal));
				listaItens.remove(idp);
				table.setModel(new RealizaPedidoTableModel(listaItens));
			}
		});

		if (table == null) {
			table = new JTable();
			table.setModel(new RealizaPedidoTableModel(itensPedido));

			table.getColumnModel().getColumn(0).setPreferredWidth(170);
			table.getColumnModel().getColumn(1).setPreferredWidth(85);
			table.getColumnModel().getColumn(2).setPreferredWidth(85);
		}
		scrollPane.setViewportView(table);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED, 403, Short.MAX_VALUE)
							.addComponent(lblTotal)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jtTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir)
						.addComponent(jtTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotal))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
