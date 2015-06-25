package br.jotas.sc.exception;

public class CampoObrigatorioException extends Exception{
	private String message = "";
	
	
	public CampoObrigatorioException(String campo){
		this.message="Campo "+campo+" obrigatório!";
	}
	
	public String getMessage(){
		return this.message;
	}
	


}
