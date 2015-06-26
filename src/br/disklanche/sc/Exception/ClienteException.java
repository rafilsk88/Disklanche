package br.disklanche.sc.Exception;

public class ClienteException extends Exception{

	private String mensagem;
	
	public ClienteException(String String){
		this.mensagem = "Cliente já "+ String ;
	}

	public String getMessage(){
		return this.mensagem;
	}
	
	
	
}
