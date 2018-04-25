package br.gov.caixa.bsb.suned.phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.caixa.bsb.suned.client.Client;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}