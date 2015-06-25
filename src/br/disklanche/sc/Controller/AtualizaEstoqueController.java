package br.disklanche.sc.Controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;



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

