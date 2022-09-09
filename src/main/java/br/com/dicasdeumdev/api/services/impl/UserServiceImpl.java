package br.com.dicasdeumdev.api.services.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import br.com.dicasdeumdev.api.services.UserService;
import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    // Método FindById
    @Override
    public User findById(Integer id) {
       Optional<User> obj = repository.findById(id); //Pode ser que encontre ou não, por isso é opcional
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); //retorna pra mim esse objeto, se não encontrar, retorna exceção
    }
    //Método findALL

    public List<User> findAll(){
        return repository.findAll();
    }
}
