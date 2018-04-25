package br.gov.caixa.bsb.suned.client;

import java.util.List;

import javax.validation.Valid;

import br.gov.caixa.bsb.suned.client.Client;
import main.java.br.gov.caixa.bsb.suned.client.exception.CadastroException;
import main.java.br.gov.caixa.bsb.suned.client.exception.LoginException;
import main.java.br.gov.caixa.bsb.suned.client.exception.ClientNotFoundException;

public interface ClientService {

	Client save(Client client) throws CadastroException;
	Client login(Client client) throws LoginException;
	Client getClientById(String token, String id) throws LoginException, ClientNotFoundException;

}
