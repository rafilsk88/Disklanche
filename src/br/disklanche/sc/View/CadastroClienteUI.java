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

import br.disklanche.sc.Controller.ClienteController;
import br.disklanche.sc.Model.Cliente;
import br.disklanche.sc.Util.ClienteTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroClienteUI extends JInternalFrame {
	private JTextField jtNome;
	private JTextField jtCpf;
	private JTextField jtEndereco;
	private JTextField jtTelefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroClienteUI frame = new CadastroClienteUI(null);
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
	public CadastroClienteUI(final Cliente cliente) {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Cadastro de Cliente");
		setBounds(100, 100, 458, 257);

		JLabel lblNome = new JLabel("Nome :");

		JLabel lblCpf = new JLabel("Cpf :");

		jtNome = new JTextField();
		jtNome.setColumns(10);

		jtCpf = new JTextField();
		jtCpf.setColumns(10);

		JLabel lblEnd = new JLabel("End.:");

		jtEndereco = new JTextField();
		jtEndereco.setColumns(10);

		JLabel lblTel = new JLabel("Tel.:");

		jtTelefone = new JTextField();
		jtTelefone.setColumns(10);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});


		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(cliente != null){
					// Editar o cliente

					cliente.setNome(jtNome.getText());
					cliente.setCpf(jtCpf.getText());
					cliente.setEndereco(jtEndereco.getText());
					cliente.setTelefone(jtTelefone.getText());
					
					ClienteController cc = new ClienteController();
					
					try {
						cc.salvarCliente(cliente);
						JOptionPane.showMessageDialog(null, "Cliente : " +cliente.getNome()+ "\nEditado com sucesso");
						ConsultaClienteUI.obterInstancia().table.setModel(new ClienteTableModel(new ClienteController().listarClientes()));
						dispose();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				} else {
					// Salvar o cliente
					
					Cliente cliente = new Cliente();

					cliente.setNome(jtNome.getText());
					cliente.setCpf(jtCpf.getText());
					cliente.setEndereco(jtEndereco.getText());
					cliente.setTelefone(jtTelefone.getText());

					ClienteController cc = new ClienteController();
					
					try {
						cc.salvarCliente(cliente);
						JOptionPane.showMessageDialog(null, "Cliente : " +cliente.getNome()+ "\nCadastrado com sucesso");
						ConsultaClienteUI.obterInstancia().table.setModel(new ClienteTableModel(new ClienteController().listarClientes()));
						dispose();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				
				
			}

		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(23)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNome)
												.addComponent(lblCpf)
												.addComponent(lblTel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblEnd))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
																.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(jtNome, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
																		.addComponent(jtEndereco, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
																		.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
																				.addComponent(jtTelefone, Alignment.LEADING)
																				.addComponent(jtCpf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))))
																				.addPreferredGap(ComponentPlacement.UNRELATED))
																				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
																						.addContainerGap()
																						.addComponent(btnSalvar)
																						.addGap(18)
																						.addComponent(btnCancelar)
																						.addGap(7)))
																						.addGap(101))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(37)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCpf))
										.addGap(21)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(jtTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblTel))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(jtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblEnd))
														.addGap(18)
														.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																.addComponent(btnSalvar)
																.addComponent(btnCancelar))
																.addContainerGap(13, Short.MAX_VALUE))
				);
		getContentPane().setLayout(groupLayout);

		if(cliente != null){
			jtNome.setText(cliente.getNome());
			jtEndereco.setText(cliente.getEndereco());
			jtCpf.setText(cliente.getCpf());
			jtTelefone.setText(cliente.getTelefone());
		}

	}
}
