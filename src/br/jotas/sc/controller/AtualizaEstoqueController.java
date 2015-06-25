package br.jotas.sc.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.jotas.sc.dao.EstoqueDAO;
import br.jotas.sc.model.Estoque;
import br.jotas.sc.model.ItensDoPedido;

public class AtualizaEstoqueController {
	
	public void AtualizarEstoque(Estoque e1){
		Estoque estoque = new Estoque();
		
		if (  (estoque.getEstoqueAtual() - 1) >= e1.getEstoqueAtual()) 
		{
			JOptionPane.showMessageDialog(null, "Valor inferior do que há no estoque!");
		}
		else
		{
			
			EstoqueDAO.getInstance().inserirEstoque(e1);
		}
			
	}
	
	public void RemoveDoPedido(ArrayList<ItensDoPedido> listaItens){
		
		
			EstoqueDAO.getInstance().Atualizar2(listaItens);
		}
	

	public void Atualizar(Estoque e1){
		EstoqueDAO.getInstance().inserirEstoque(e1);
	}

}

