package br.disklanche.sc.Exception;

import java.sql.SQLException;

public class CloseConectionException extends Exception {

	@Override
	public String getMessage() {
		return "[ Erro ao tentar fechar conexão ] : "+super.getMessage();
	}
}
