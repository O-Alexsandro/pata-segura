package br.com.pata.segura.service;

import br.com.pata.segura.configuration.TokenService;
import br.com.pata.segura.domain.animal.*;
import br.com.pata.segura.domain.usuario.RoleUsuario;
import br.com.pata.segura.domain.usuario.Usuario;
import br.com.pata.segura.repository.AnimalRepository;
import br.com.pata.segura.repository.UsuarioRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AnimalService animalService;

    @Test
    @DisplayName("Deve listar todos os animais cadastrados por um usuário")
    void listarAnimalPerdido() {

        Usuario usuarioMock  = new Usuario(1L, "ale","alexsandro@gmail.com", "123456", "11 000000000", RoleUsuario.ADMINISTRADOR);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuarioMock, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR")));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CriarAnimalPerdidoDTO animalDTO = new CriarAnimalPerdidoDTO("lua", TipoAnimal.GATO, "sem raça", "preto", PorteAnimal.MEDIO, StatusAnimal.PERDIDO, "gato perdido", LocalDate.EPOCH, new EnderecoDTO("Rua 1", "bairro 1", "Cidade 1", "SP", "00000000"));
        Animal animalMock = new Animal(animalDTO);

        when(animalRepository.save(any(Animal.class))).thenReturn(animalMock);
        when(animalRepository.findByUsuario(any(), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(animalMock)));

        var animalCriado = animalService.criarAnimalPerdido(animalDTO);

        assertNotNull(animalCriado);
        //assertNotNull(animalCriado.getUsuario());
        //assertEquals(usuarioMock.getId(), animalCriado.getUsuario().getId());
    }
}