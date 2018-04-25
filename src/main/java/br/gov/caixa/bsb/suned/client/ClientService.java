package br.gov.caixa.bsb.suned.client;

import java.util.List;

import javax.validation.Valid;

import br.gov.caixa.bsb.suned.client.Client;
import main.java.br.gov.caixa.bsb.suned.client.exception.ClientException;

public interface ClientService {

	Client save(Client client) throws ClientException;
	Client login(@Valid Client client) throws ClientException;

}
