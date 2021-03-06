package br.disklanche.sc.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import br.disklanche.sc.DAO.PedidoDAO;
import br.disklanche.sc.Exception.CampoObrigatorioException;
import br.disklanche.sc.Model.Cliente;
import br.disklanche.sc.Model.ItensDoPedido;
import br.disklanche.sc.Model.RealizarPedido;
import br.disklanche.sc.Util.DataUtil;

public class RealizarPedidoController {

	public void salvarPedido(ArrayList<ItensDoPedido> listaItens, Cliente cliente, double valorTotal) throws Exception {
		PedidoDAO dao = new PedidoDAO();
		dao.salvarPedido(listaItens, cliente, valorTotal);
	}
	
	public void salvarItensPedido(RealizarPedido pedido) throws Exception {
		validaDados(pedido);
		PedidoDAO dao = new PedidoDAO();
		dao.salvarItensPedido(pedido);
	}

	public RealizarPedido obterPedido(int id) {
		PedidoDAO dao = new PedidoDAO();
		return dao.obterPedido(id);
	}

	
	public double valorPorCliente(int id, Date dataPeriodo){
		PedidoDAO dao = new PedidoDAO();
		Date dataInicio = DataUtil.criarDataNoPrimeiroDiaMesNoPrimeiroSegundo(dataPeriodo);
		Date dataFinal = DataUtil.criarDataNoUltimoDiaMesNoUltimoSegundo(dataPeriodo);
		return dao.obterValorPorCliente(id, dataInicio, dataFinal);
	}

	public ArrayList<RealizarPedido> listarPedidos() {
		PedidoDAO dao = new PedidoDAO();
		return dao.listarPedidos();
	}

	public ArrayList<RealizarPedido> listarPedidoPorCliente(int id) {
		PedidoDAO dao = new PedidoDAO();
		return dao.listarPedidosPorCliente(id);
	}

	public ArrayList<RealizarPedido> listarPedidosClientePorPeriodo(int id, Date periodo) {
		PedidoDAO dao = new PedidoDAO();
		Date dataInicial = DataUtil.criarDataNoPrimeiroDiaMesNoPrimeiroSegundo(periodo);
		Date dataFinal = DataUtil.criarNoUltimoSegundo(periodo);
		if (!DataUtil.mesmoMesAtual(periodo)) {
			dataFinal = DataUtil.criarDataNoUltimoDiaMesNoUltimoSegundo(periodo);
		}
		return dao.listarPedidosClientePorPeriodo(id, dataInicial, dataFinal);
	}

	public int numeroDePedidosPorClientePorPeriodo(int id, Date periodo) {
		PedidoDAO dao = new PedidoDAO();
		Date dataInicial = DataUtil.criarDataNoPrimeiroDiaMesNoPrimeiroSegundo(periodo);
		Date dataFinal = DataUtil.criarDataNoUltimoDiaMesNoUltimoSegundo(periodo);
		return dao.numeroDePedidosPorClientePorPeriodo(id, dataInicial, dataFinal);
	}

	public void excluirPedido(int id) throws SQLException {
		PedidoDAO dao = new PedidoDAO();
		dao.excluirPedido(id);
	}

	public void validaDados(RealizarPedido pedido) throws CampoObrigatorioException, Exception {
		if (pedido.getCliente() == null || pedido.getCliente().getId() == 0) {
			throw new CampoObrigatorioException("Cliente");
		}
		if (pedido.getProduto() == null) {
			throw new CampoObrigatorioException("Produto");
		}
		if (pedido.getDataPedido() == null) {
			throw new NullPointerException("Campo Produto obrigat�rio!");
		} else if (pedido.getDataPedido().before(DataUtil.criarNoPrimeiroSegundo(new Date()))) {
			throw new Exception("Data do pedido n�o pode ser anterior a data atual!");
		}
	}
	
	public int quantidadeProdutosVendidosPorPeriodo(int id, Date dataInicio, Date dataFim){
		PedidoDAO dao = new PedidoDAO();
		Date dataInicial = DataUtil.criarDataNoPrimeiroDiaMesNoPrimeiroSegundo(dataInicio);
		Date dataFinal = DataUtil.criarDataNoUltimoDiaMesNoUltimoSegundo(dataFim);
		if (!DataUtil.mesmoMesAtual(dataFim)) {
			dataFinal = DataUtil.criarDataNoUltimoDiaMesNoUltimoSegundo(dataFim);
		}
		if(dao.quantidadeProdutosVendidosPorPeriodo(id, dataInicial, dataFinal) == 0){
			JOptionPane.showMessageDialog(null, "Nenhum dado encontrado nesse per�odo!");
		} else {	
			return dao.quantidadeProdutosVendidosPorPeriodo(id, dataInicial, dataFinal);
		}
		return id;
		}

	public int produtoVendidosPorPeriodo(int id, Date dataInicio, Date dataFim){
		PedidoDAO dao = new PedidoDAO();
		Date dataInicial = DataUtil.criarDataNoPrimeiroDiaMesNoPrimeiroSegundo(dataInicio);
		Date dataFinal = DataUtil.criarNoUltimoSegundo(dataFim);
		if (!DataUtil.mesmoMesAtual(dataFim)) {
			dataFinal = DataUtil.criarDataNoUltimoDiaMesNoUltimoSegundo(dataFim);
		}
		return dao.produtoVendidosPorPeriodo(id, dataInicial, dataFinal);
	}
	
	
	
}
