package br.gov.caixa.bsb.suned.client;

import java.util.List;

import javax.validation.Valid;

import br.gov.caixa.bsb.suned.client.Client;
import main.java.br.gov.caixa.bsb.suned.client.exception.CadastroException;
import main.java.br.gov.caixa.bsb.suned.client.exception.LoginException;

public interface ClientService {

	Client save(Client client) throws CadastroException;
	Client login(@Valid Client client) throws LoginException;

}
