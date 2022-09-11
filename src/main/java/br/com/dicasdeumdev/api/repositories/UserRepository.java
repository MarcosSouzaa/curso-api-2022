package br.com.dicasdeumdev.api.repositories;

import br.com.dicasdeumdev.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalInt;

// Ele espera qual é a classe e o argumento que vamos persistir O nosso User(classe)
                // Espera o tipo primitivo do obj identificador da entity e setamos como PK o Id e o tipo Integer
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
                    Optional<User> findByEmail(String email);
                }







































