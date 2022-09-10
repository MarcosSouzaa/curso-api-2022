package br.com.dicasdeumdev.api.services.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import br.com.dicasdeumdev.api.services.UserService;
import br.com.dicasdeumdev.api.services.exceptions.DataIntegratyViolationException;
import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private  ModelMapper mapper;

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

   @Override
    public User create(UserDTO obj) { //Não salvo DTO, salvo tipo USER, converto aqui em
        findByEmail(obj); //Vai chamar o método e fazer a validação antes de retornar

        return repository.save(mapper.map(obj, User.class)); //RETORNA ESSA ENTITADE PARA RESOURCE
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj); //Antes de atualizar, vou chamar o método passando obj como parâmetro
        return repository.save(mapper.map(obj, User.class)); // Salvo as informações do usuário no Banco
    }
       //Método findByEmail para atualização do email
    private void findByEmail(UserDTO obj){ //Vou verificar se já existe o email igual "obj" no banco
        Optional<User> user = repository.findByEmail(obj.getEmail()); //FAÇO A BUSCA
        if(user.isPresent() && !user.get().getId().equals(obj.getId())){ //senão segue a vida
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
        }
    }
}
