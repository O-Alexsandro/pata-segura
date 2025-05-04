package br.com.pata.segura;

import br.com.pata.segura.configuration.TokenService;
import br.com.pata.segura.domain.usuario.CriarUsuarioDTO;
import br.com.pata.segura.domain.usuario.Usuario;
import br.com.pata.segura.repository.UsuarioRepository;
import br.com.pata.segura.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class PataSeguraApplicationTests {

	@Mock
	private UsuarioRepository usuarioRepository;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private TokenService tokenService;

	@Autowired
	@InjectMocks
	private UsuarioService usuarioService;

	@BeforeEach
	void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Deve retornar ok quando o usuário for criado")
	void createUser() {

		//Arrange
		CriarUsuarioDTO criarUsuario = new CriarUsuarioDTO("Ale", "ale@gmail.com", "123456", "11999999999");
		Usuario usuarioEsperado = new Usuario(criarUsuario);

		when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioEsperado);
		//Act
		var usuarioCriado = usuarioService.criarUsuario(criarUsuario);

		//Assert
		assertNotNull(criarUsuario);
		assertEquals("Ale", usuarioCriado.getNome());

		verify(usuarioRepository,times(1)).save(any(Usuario.class));
	}

	@Test
	@DisplayName("Deve retornar null quando o usuário for criado sem parametro")
	void createUserNull(){

		CriarUsuarioDTO usuarioCriacao = new CriarUsuarioDTO(null, null, "123",null);
		Usuario usuarioCriado = new Usuario(usuarioCriacao);

		when(usuarioRepository.save(any(Usuario.class))).thenReturn(null);

		var retorno = usuarioService.criarUsuario(usuarioCriacao);

		assertNull(retorno);
	}

}
