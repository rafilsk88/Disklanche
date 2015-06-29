package br.disklanche.sc.Exception;

public class ProdutoException extends Exception{

	private String mensagem;

	public ProdutoException(String String) {
		this.mensagem = "Produto já " + String;
	}

	public String getMessage() {
		return this.mensagem;
	}

}
