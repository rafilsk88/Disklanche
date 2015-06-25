package br.jotas.sc.model;

import java.util.ArrayList;
import java.util.List;

public enum StatusProdutoEnum {

	ATIVO(1), INATIVO(0);

	final int codigo;
	
	StatusProdutoEnum(int codigo){
		this.codigo = codigo;
				
	}
	
	public int getCodigo(){
		return codigo;
	}
		
	public static StatusProdutoEnum getValue(int codigo){
		for (StatusProdutoEnum s : StatusProdutoEnum.getValues()) {
			if(s.getCodigo() == codigo){
				return s;
			}
		}
		return null;
	}
	
	public static List<StatusProdutoEnum> getValues(){
		List<StatusProdutoEnum> values = new ArrayList<StatusProdutoEnum>();
		for (StatusProdutoEnum statusExemplarEnum : values()) {
			values.add(statusExemplarEnum);
		}
		return values;
	}
}
