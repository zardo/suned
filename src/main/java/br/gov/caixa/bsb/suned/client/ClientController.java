package br.gov.caixa.bsb.suned.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.bsb.suned.client.ClientRepository;
import main.java.br.gov.caixa.bsb.suned.JSONError;
import main.java.br.gov.caixa.bsb.suned.client.exception.ClientException;
import br.gov.caixa.bsb.suned.client.Client;

@RestController
public class ClientController {
	
    @Autowired
    @Qualifier("clientService")
    ClientService clientService;

    @ResponseStatus(code=HttpStatus.CREATED)
    @PostMapping(path = "cadastro", headers={"content-type=application/json"})
    public Client register(@Valid @RequestBody Client client) throws ClientException {
        return clientService.save(client);
    }

    @ResponseStatus(code=HttpStatus.CREATED)
    @PostMapping(path = "login", headers={"content-type=application/json"})
    public Client login(@Valid @RequestBody Client client) throws ClientException {
        return clientService.login(client);
    }

    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ClientException.class})
    public JSONError databaseError(HttpServletRequest req, ClientException ex) {
    	return new JSONError(ex.getMessage());
    }

    @ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public JSONError serverError(HttpServletRequest req, Exception ex) {
    	return new JSONError(ex.getMessage());
    }
    
}