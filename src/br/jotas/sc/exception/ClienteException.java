package br.jotas.sc.exception;

public class ClienteException extends Exception{

	private String mensagem;
	
	public ClienteException(String mensagem){
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
