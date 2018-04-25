package br.gov.caixa.bsb.suned.client;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.gov.caixa.bsb.suned.client.ClientRepository;
import br.gov.caixa.bsb.suned.client.ClientService;
import br.gov.caixa.bsb.suned.phone.PhoneRepository;
import main.java.br.gov.caixa.bsb.suned.client.exception.CadastroException;
import main.java.br.gov.caixa.bsb.suned.client.exception.LoginException;
import main.java.br.gov.caixa.bsb.suned.client.exception.ClientNotFoundException;
import br.gov.caixa.bsb.suned.client.Client;

public class ClientServiceImpl implements ClientService {
	
    @Autowired
    ClientRepository clientRepository;

    @Override
    @Transactional
    public Client save(Client client) throws CadastroException {
    	if (client.getName() == null || client.getName().equals(""))
    		throw new CadastroException("Nome do usuario nao informado");
    	
    	if (client.getEmail() == null || client.getEmail().equals(""))
    		throw new CadastroException("Email nao informado");
    	
    	if (client.getPassword() == null || client.getPassword().equals(""))
    		throw new CadastroException("Senha nao informada");
    	
    	if (clientRepository.findByEmail(client.getEmail()) != null)
    		throw new CadastroException("E-mail ja existente");
    	
    	client.setId(UUID.randomUUID().toString());
    	client.setCreated(new Date());
    	client.setModified(new Date());
    	client.setLast_login(new Date());
    	client.setToken(UUID.randomUUID().toString());
    	
    	client.getPhones().forEach(phone -> {
    		phone.setClient(client);
    	});
    	
    	return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client login(Client client) throws LoginException {
    	Client dbClient = clientRepository.findByEmail(client.getEmail());
    	if (dbClient == null)
    		throw new LoginException("Usuario e/ou senha invalidos");
    	
    	if (!dbClient.getPassword().equals(client.getPassword())) 
    		throw new LoginException("Usuario e/ou senha invalidos");
    	
    	dbClient.setLast_login(new Date());
    	dbClient.setToken(UUID.randomUUID().toString());
    	
    	return dbClient;
    }

    @Override
    @Transactional
    public Client getClientById(String token, String id) throws LoginException, ClientNotFoundException {
    	String[] tokenParts = token.split(" ");
    	
    	if (tokenParts[1] == null || tokenParts[1].equals(""))
    		throw new LoginException("Nao autorizado");
    	
    	Client dbClient = clientRepository.findById(id);
    	if (dbClient == null)
    		throw new ClientNotFoundException("Usuario nao encontrado para o id: " + id);
    	
    	if (!dbClient.getToken().equals(tokenParts[1])) 
    		throw new LoginException("Nao autorizado");
    	
    	long diff = (new Date()).getTime() - dbClient.getLast_login().getTime() ;
    	if (diff > 30* 60 * 1000)
    		throw new LoginException("Sessao invalida");
    	
    	return dbClient;
    }

}
