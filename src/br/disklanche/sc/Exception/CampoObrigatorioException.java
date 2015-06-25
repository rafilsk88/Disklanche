package br.disklanche.sc.Exception;

public class CampoObrigatorioException extends Exception{
	private String message = "";
	
	
	public CampoObrigatorioException(String campo){
		this.message="Campo "+ campo +" obrigat�rio!";
	}
	
	public String getMessage(){
		return this.message;
	}
	


}
