package br.disklanche.sc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import br.disklanche.sc.Controller.ClienteController;
import br.disklanche.sc.Controller.ProdutoController;
import br.disklanche.sc.Jdbc.ConnectionFactory;
import br.disklanche.sc.Model.Cliente;
import br.disklanche.sc.Model.Estoque;
import br.disklanche.sc.Model.ItensDoPedido;
import br.disklanche.sc.Model.RealizarPedido;

public class PedidoDAO {

	private static PedidoDAO instance;
	private Connection con;
	private ArrayList<RealizarPedido> listaPedido = new ArrayList<RealizarPedido>();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private ResultSet rs;
	private int gen_key;

	public PedidoDAO() {
		con = ConnectionFactory.getConnection();
	}

	public static PedidoDAO getInstance() {
		if (instance == null) {
			instance = new PedidoDAO();
		}
		return instance;
	}

	public ArrayList<RealizarPedido> listarPedidos() {
		String query = "Select * from pedido";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			ResultSet res = stm.executeQuery();
			ArrayList<RealizarPedido> listarPedidos = new ArrayList<RealizarPedido>();
			while (res.next()) {
				RealizarPedido pedido = new RealizarPedido();
				pedido.setId(res.getInt("id_pedido"));
				pedido.setCliente(new ClienteController().obterCliente(res.getInt("cliente")));
				pedido.setDataPedido(res.getDate("dataHoraPedido"));
				pedido.setValor(res.getDouble("valorPedido"));	
				listarPedidos.add(pedido);
			}
			return listarPedidos;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar os pedidos ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int numeroDePedidosPorClientePorPeriodo(int id, Date dataInicial, Date dataFinal) {
		String query = "SELECT COUNT(*) FROM pedido WHERE id_cliente = ? and dataHoraPedido between ? and ?";
		int quantidade = 0;
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.setString(2, sdf.format(dataInicial));
			stm.setString(3, sdf.format(dataFinal));
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				quantidade = res.getInt(1);
			}
			return quantidade;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar pedidos ] : " + e.getMessage());
			return -1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<RealizarPedido> listarPedidosClientePorPeriodo(int id, Date dataInicial, Date dataFinal) {
		String query = "Select * from pedido WHERE id_cliente = ? and dt_pedido between ? and ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.setString(2, sdf.format(dataInicial));
			stm.setString(3, sdf.format(dataFinal));
			ResultSet res = stm.executeQuery();
			ArrayList<RealizarPedido> listapedidos = new ArrayList<RealizarPedido>();
			while (res.next()) {
				RealizarPedido pedido = new RealizarPedido();
				pedido.setId(res.getInt("id_locacao"));
				pedido.setCliente(new ClienteController().obterCliente(res.getInt("id_cliente")));
				pedido.setProduto(new ProdutoController().obterProduto(res.getInt("id_produto")));
				pedido.setDataPedido(res.getDate("dt_pedido"));
				pedido.setValor(res.getDouble("vl_locacao"));
			}
			return listapedidos;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar os pedidos ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<RealizarPedido> listarPedidosPorCliente(int id) {
		String query = "Select * from pedido WHERE id_cliente = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			ArrayList<RealizarPedido> listaPedidos = new ArrayList<RealizarPedido>();
			while (res.next()) {
				RealizarPedido pedido = new RealizarPedido();
				pedido.setId(res.getInt("id_locacao"));
				pedido.setCliente(new ClienteController().obterCliente(res.getInt("id_cliente")));
				pedido.setProduto(new ProdutoController().obterProduto(res.getInt("id_produto")));
				pedido.setDataPedido(res.getDate("dt_pedido"));
				pedido.setValor(res.getDouble("vl_pedido"));
				listaPedidos.add(pedido);
			}
			return listaPedidos;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar os pedidos ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//REVER QUERY
	public double obterValorPorCliente(int id, Date dataInicio, Date dataFinal) {
		String query = "SELECT SUM(p.vl_pedido) FROM cliente c, pedido p WHERE c.id_cliente = p.id_cliente AND p.dt_pedido between ? and ? AND p.id_cliente = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, sdf.format(dataInicio));
			stm.setString(2, sdf.format(dataFinal));
			stm.setInt(3, id);
			ResultSet res = stm.executeQuery();
			double valor = 0;
			while (res.next()) {
				valor = res.getDouble(1);
			}
			return valor;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar obter valor por Cliente ] : " + e.getMessage());
			return -1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public RealizarPedido obterPedido(int id) {
		String query = "SELECT * FROM pedido WHERE id_pedido = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			ArrayList<RealizarPedido> listaPedidos = new ArrayList<RealizarPedido>();
			while (res.next()) {
				RealizarPedido pedido = new RealizarPedido();
				pedido.setId(res.getInt("id_locacao"));
				pedido.setCliente(new ClienteController().obterCliente(res.getInt("id_cliente")));
				pedido.setProduto(new ProdutoController().obterProduto(res.getInt("id_produto")));
				pedido.setDataPedido(res.getDate("dt_pedido"));
				pedido.setValor(res.getDouble("vl_pedido"));
				listaPedidos.add(pedido);
			}
			return listaPedidos.get(0);
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar obter os pedidos ] : " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	public void salvarPedido(ArrayList<ItensDoPedido> listaItens, Cliente cliente, double valorTotal) {
		String query_pedido = "INSERT INTO pedido (cliente, valorPedido) VALUES (?,?)";
		try {
			PreparedStatement stm = con.prepareStatement(query_pedido, PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setInt(1, cliente.getId());
			stm.setDouble(2, valorTotal);
			stm.execute();
			rs = stm.getGeneratedKeys();
			rs.next();
			gen_key = rs.getInt(1);
			for (int i=0; i<listaItens.size(); i++){
				try{
					String query = "INSERT INTO produto_has_pedido (produto_id_produto, pedido_id_pedido, quantidade) VALUES (?,?,?)";
					try {
						stm = con.prepareStatement(query);
						stm.setInt(1, listaItens.get(i).getProduto().getId());
						stm.setInt(2, gen_key);
						stm.setInt(3, listaItens.get(i).getQuantidade());
						stm.execute();
					} catch (SQLException e) {
						try {
							con.rollback();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						System.out.println("[ Erro ao tentar salvar o pedido ] : " + e.getMessage());
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("[ Erro ao tentar salvar o pedido ] : " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void salvarItensPedido(RealizarPedido pedido) {
		String query = "INSERT INTO produto_has_pedido (produto_id_produto, pedido_id_pedido, quantidade) VALUES (?,?,?)";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm = con.prepareStatement(query);
			stm.setInt(1, pedido.getProduto().getId());
			stm.setInt(2, gen_key);
			stm.setInt(3, pedido.getQuantidade());
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("[ Erro ao tentar salvar o pedido ] : " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void excluirPedido(int id) {
		String query = "DELETE FROM pedido WHERE id_pedido = ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("[ Erro ao tentar exlcuir o pedido ] : " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<RealizarPedido> TabelaPedido() {
		try {
			this.listaPedido = new ArrayList<RealizarPedido>();
			String query = "select * From pedido";
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			
			
			while (res.next()) {
												
				RealizarPedido pedido = new RealizarPedido();
				pedido.getProduto().setTitulo(res.getString("titulo"));
				pedido.getProduto().setValor(res.getDouble("valor"));
				pedido.setQuantidade(res.getInt("quantidade"));
				
				listaPedido.add(pedido);
			}
			
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("[ Erro ao tentar salvar o pedido ] : " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaPedido;

	}
	public ArrayList<Estoque> VerificaEstoque(){
		String query = "Select * from estoque";
		try {
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);
			ArrayList<Estoque> listaEstoque = new ArrayList<Estoque>();
			while (res.next()) {
				Estoque estoque = new Estoque();
				estoque.setEstoqueAtual(res.getInt("estoqueAtual"));
				listaEstoque.add(estoque);
			}
			return listaEstoque;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar clientes ] : " + e.getMessage());
			return null;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int quantidadeProdutosVendidosPorPeriodo(int id, Date dataInicial, Date dataFinal) {
		String query = "SELECT COUNT(*) from produto_has_pedido ph inner join pedido e on ph.pedido_id_pedido = e.id_pedido where ph.produto_id_produto = ? and e.dataHoraPedido between ? and ?;";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.setString(2, sdf.format(dataInicial));
			stm.setString(3, sdf.format(dataFinal));
			ResultSet res = stm.executeQuery();
			int quantidade = 0;
			while (res.next()) {
				quantidade += res.getInt(1);
			}
			return quantidade;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar pedidos ] : " + e.getMessage());
			return -1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int produtoVendidosPorPeriodo(int id, Date dataInicial, Date dataFinal) {
		String query = "SELECT titulo FROM pedido p join produto e join produto_has_pedido ph on ph.produto_id_produto = e.id_produto WHERE e.id_produto = ? and p.dataHoraPedido between ? and ?";
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.setString(2, sdf.format(dataInicial));
			stm.setString(3, sdf.format(dataFinal));
			ResultSet res = stm.executeQuery();
			int quantidade = 0;
			while (res.next()) {
				quantidade = res.getInt(1);
			}
			return quantidade;
		} catch (SQLException e) {
			System.out.println("[ Erro ao tentar listar pedidos ] : " + e.getMessage());
			return -1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
