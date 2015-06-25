package br.jotas.sc.exception;

public class NaoEncontradoException extends Exception {

private String message = "";
	
	
	public NaoEncontradoException(String message){
		this.message= message;
	}
	
	public String getMessage(){
		return this.message;
	}
}
