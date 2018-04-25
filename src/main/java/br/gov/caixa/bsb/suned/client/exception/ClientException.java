package main.java.br.gov.caixa.bsb.suned.client.exception;

public class ClientException extends Exception {

	private static final long serialVersionUID = 1L;

	private String mensagem;
	
	public ClientException(String message) {
		super();
		this.mensagem = message;
	}

	public String getMessage() {
		return mensagem;
	}

	public void setMessage(String message) {
		this.mensagem = message;
	}
	
}
