package main.java.br.gov.caixa.bsb.suned;

public class JSONError {

	private String mensagem;
	
	public JSONError(String message) {
		this.mensagem = message;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String message) {
		this.mensagem = message;
	}
	
}
