package br.disklanche.sc.Exception;

public class ClienteException extends Exception{

	private String mensagem;
	
	public ClienteException(String String){
		this.mensagem = "Cliente j� "+ String ;
	}

	public String getMessage(){
		return this.mensagem;
	}
	
	
	
}
