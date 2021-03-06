package main.java.br.gov.caixa.bsb.suned.client.exception;

public class LoginException extends Exception {

	private static final long serialVersionUID = 1L;

	private String mensagem;
	
	public LoginException(String message) {
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
