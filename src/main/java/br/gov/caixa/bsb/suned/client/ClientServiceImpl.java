package br.gov.caixa.bsb.suned.client;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.gov.caixa.bsb.suned.client.ClientRepository;
import br.gov.caixa.bsb.suned.client.ClientService;
import br.gov.caixa.bsb.suned.phone.PhoneRepository;
import main.java.br.gov.caixa.bsb.suned.client.exception.ClientException;
import br.gov.caixa.bsb.suned.client.Client;

public class ClientServiceImpl implements ClientService {
	
    @Autowired
    ClientRepository clientRepository;

    @Override
    @Transactional
    public Client save(Client client) throws ClientException {
    	if (client.getName() == null || client.getName().equals(""))
    		throw new ClientException("Nome do usuario nao informado");
    	
    	if (client.getEmail() == null || client.getEmail().equals(""))
    		throw new ClientException("Email nao informado");
    	
    	if (client.getPassword() == null || client.getPassword().equals(""))
    		throw new ClientException("Senha nao informada");
    	
    	if (clientRepository.findByEmail(client.getEmail()) != null)
    		throw new ClientException("E-mail ja existente");
    	
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
    public Client login(Client client) throws ClientException {
    }
    }

}
