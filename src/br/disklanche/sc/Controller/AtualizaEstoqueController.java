package br.disklanche.sc.Controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.disklanche.sc.DAO.EstoqueDAO;
import br.disklanche.sc.Model.Estoque;
import br.disklanche.sc.Model.ItensDoPedido;
import br.disklanche.sc.Model.Produto;



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
	
	public Object quantidadeDoProdutoNoEstoque(Produto produto){
		EstoqueDAO.getInstance().quantidadeDoProdutoNoEstoque(produto);
		return quantidadeDoProdutoNoEstoque(produto);
	}

	public void Atualizar(Estoque e1){
		EstoqueDAO.getInstance().inserirEstoque(e1);
	}

}

