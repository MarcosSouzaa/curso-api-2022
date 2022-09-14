package br.com.dicasdeumdev.api.services.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

// AQUI CRIAMOS OS MOCKS QUE PRECISAMOS PARA TESTAR OS MÉTODOS DA CLASSE UserServiceImpl

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID      = 1;
    public static final String NAME     = "Valdir";
    public static final String EMAIL    = "valdir@mail.com";
    public static final String PASSWORD = "123";


    @InjectMocks //Vai criar uma instância real de UserServiceImpl, mas vai criar os demais mocks de mentira.
    private UserServiceImpl service;

    @Mock //Não vou acessar o banco de verdade, não preciso de uma instância real de repository
    private UserRepository repository;

    @Mock // Vou mockar as respostas que o ModelMapper vai enviar pra gente
    private ModelMapper mapper;

    private User user; //Vou iniciar

    private UserDTO userDTO;

    private Optional<User> OptionalUser;

    @BeforeEach // Antes de tudo, faça o seguinte pra mim, realize um trecho de código que eu passar aqui dentro
    void setUp() {
        MockitoAnnotations.openMocks(this); //vai iniciar os mocks da classe que eu passar aqui
                                                    // vou passar o objeto this fazendo referência a classe que testo
           startUser(); // chama os métodos que cria os atributos da classe (está na parte debaixo).
            }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(OptionalUser);

        User response = service.findById(ID);

        assertNotNull(response); //Assegure pra mim que o meu response não será nulo/ depois verifico a classe aqui embaixo
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());//assegure pra mim que a resposta que vou ter aqui será igual ao ID que passei como parâmetro. Fará comparação entre os dois
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());

    }


    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        OptionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD)) ;

    }
}