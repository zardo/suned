package br.gov.caixa.bsb.suned.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.bsb.suned.model.Cliente;

@RestController
public class ClienteController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Cliente greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Cliente();
    }
    
}