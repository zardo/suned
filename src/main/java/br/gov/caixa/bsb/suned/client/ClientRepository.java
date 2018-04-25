package br.gov.caixa.bsb.suned.client;

import org.springframework.stereotype.Repository;

import br.gov.caixa.bsb.suned.client.Client;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Client findByEmail(String email);
	Client findById(String id);

}
