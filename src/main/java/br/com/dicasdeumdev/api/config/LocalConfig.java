package br.com.dicasdeumdev.api.config;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean//para subir sempre que esse perfil estiver ativo
    public void startDB() {
        User u1 = new User(null, "Marcos","marcos@mail.com", "123");
        User u2 = new User(null, "Luis", "luis@mail.com", "124");
        User U3 = new User(null, "Renata", "renata@mail.com", "125");

        repository.saveAll(List.of(u1, u2));
    }
}