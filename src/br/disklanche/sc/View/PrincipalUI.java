package br.disklanche.sc.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrincipalUI extends JFrame {

	private JPanel contentPane;
	private static PrincipalUI instancia;
	
	
	public static PrincipalUI obterInstancia() {
		if (instancia == null) {
			instancia = new PrincipalUI();
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
					PrincipalUI frame = PrincipalUI.obterInstancia();
					frame.setExtendedState(MAXIMIZED_BOTH);
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
	public PrincipalUI() {
		setTitle("Disk Lanche");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 368);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPedido = new JMenu("Pedido");
		menuBar.add(mnPedido);
		
		JMenuItem mntmRealizarPedido = new JMenuItem("Realizar Pedido");
		mntmRealizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RealizarPedidoUI realizarPedidoUI = new RealizarPedidoUI();
				
				realizarPedidoUI.setFocusable(true);
				realizarPedidoUI.moveToFront();
				realizarPedidoUI.requestFocus();
				getContentPane().add(realizarPedidoUI, 0);
				realizarPedidoUI.setVisible(true);
			}
		});
		mnPedido.add(mntmRealizarPedido);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadastroClienteUI cadastroClienteUI = new CadastroClienteUI(null);
				
				cadastroClienteUI.setFocusable(true);
				cadastroClienteUI.moveToFront();
				cadastroClienteUI.requestFocus();
				getContentPane().add(cadastroClienteUI, 0);
				cadastroClienteUI.setVisible(true);
			}
		});
		mnCadastro.add(mntmCliente);
		
		JMenuItem mntmProduto = new JMenuItem("Produto");
		mntmProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CadastroProdutoUI cadastroProduto = new CadastroProdutoUI(null);
				
				cadastroProduto.setFocusable(true);
				cadastroProduto.moveToFront();
				cadastroProduto.requestFocus();
				getContentPane().add(cadastroProduto, 0);
				cadastroProduto.setVisible(true);
			}
		});
		mnCadastro.add(mntmProduto);
		
		JMenuItem mntmCategoria = new JMenuItem("Categoria");
		mntmCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CadastroCategoriaUI cadastroCategoria = new CadastroCategoriaUI();
				
				cadastroCategoria.setFocusable(true);
				cadastroCategoria.moveToFront();
				cadastroCategoria.requestFocus();
				getContentPane().add(cadastroCategoria, 0);
				cadastroCategoria.setVisible(true);
			}
		});
		mnCadastro.add(mntmCategoria);
		
		JMenu mnEstoque = new JMenu("Estoque");
		menuBar.add(mnEstoque);
		
		JMenuItem mntmAtualizar = new JMenuItem("Atualizar");
		mntmAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AtualizaEstoque atualizaEstoque = new AtualizaEstoque();
				
				atualizaEstoque.setFocusable(true);
				atualizaEstoque.moveToFront();
				atualizaEstoque.requestFocus();
				getContentPane().add(atualizaEstoque, 0);
				atualizaEstoque.setVisible(true);
			}
		});
		mnEstoque.add(mntmAtualizar);
		
		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);
		
		JMenuItem mntmCliente_1 = new JMenuItem("Cliente");
		mntmCliente_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConsultaClienteUI consultaClienteUI = new ConsultaClienteUI();
				
				consultaClienteUI.setFocusable(true);
				consultaClienteUI.moveToFront();
				consultaClienteUI.requestFocus();
				getContentPane().add(consultaClienteUI, 0);
				
				try {
					consultaClienteUI.setSelected(true);
				}
				catch(PropertyVetoException e1){
					e1.printStackTrace();
				}
				consultaClienteUI.setVisible(true);
			}
		});
		mnConsulta.add(mntmCliente_1);
		
		JMenuItem mntmProduto_1 = new JMenuItem("Produto");
		mntmProduto_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ConsultaProdutoUI consultaProdutoUI = new ConsultaProdutoUI();
				
				consultaProdutoUI.setFocusable(true);
				consultaProdutoUI.moveToFront();
				consultaProdutoUI.requestFocus();
				getContentPane().add(consultaProdutoUI, 0);
				consultaProdutoUI.setVisible(true);
				
			}
		});
		mnConsulta.add(mntmProduto_1);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatrio);
		
		JMenuItem mntmVendas = new JMenuItem("Vendas");
		mntmVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GerarRelatorioVendasUI relatorioClienteUI = new GerarRelatorioVendasUI();
				
				relatorioClienteUI.setFocusable(true);
				relatorioClienteUI.moveToFront();
				relatorioClienteUI.requestFocus();
				getContentPane().add(relatorioClienteUI, 0);
				relatorioClienteUI.setVisible(true);
			}
		});
		mnRelatrio.add(mntmVendas);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		menuBar.add(mnSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 251, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}
}