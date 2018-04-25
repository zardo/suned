package br.gov.caixa.bsb.suned;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.gov.caixa.bsb.suned.client.ClientService;
import br.gov.caixa.bsb.suned.client.ClientServiceImpl;

@Configuration
public class Config {

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
    
    @Bean
    @ConditionalOnProperty(name = "ClientService", matchIfMissing = true)
    public ClientService clientService() {
        return new ClientServiceImpl();
    }
}