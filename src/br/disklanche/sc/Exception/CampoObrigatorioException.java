package br.disklanche.sc.Exception;

public class CampoObrigatorioException extends Exception
{
	private String mensagem ;
	
	
	public CampoObrigatorioException(String string)
	{
		this.mensagem=" Campo "+ string +" obrigatório!";
	}
	
	public String getMessage(){
		return this.mensagem;
	}
	


}
