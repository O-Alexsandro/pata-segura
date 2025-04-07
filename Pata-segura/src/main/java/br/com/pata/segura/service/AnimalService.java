package br.com.pata.segura.service;

import br.com.pata.segura.configuration.TokenService;
import br.com.pata.segura.domain.animal.Animal;
import br.com.pata.segura.domain.animal.CriarAnimalPerdidoDTO;
import br.com.pata.segura.domain.animal.ResponseAnimalDTO;
import br.com.pata.segura.domain.animal.ResponsePublicoAnimalDTO;
import br.com.pata.segura.domain.usuario.LoginResponseDTO;
import br.com.pata.segura.domain.usuario.Usuario;
import br.com.pata.segura.repository.AnimalRepository;
import br.com.pata.segura.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cria um animal perdido a partir do usuário logado
    public Animal criarAnimalPerdido(CriarAnimalPerdidoDTO dados){
        var autenticacao = SecurityContextHolder.getContext().getAuthentication();
        var email = autenticacao.getName();

        var usuario = usuarioRepository.findByEmail(email);

        Animal novoAnimal = new Animal(dados);
        novoAnimal.setUsuario((Usuario) usuario);
        return animalRepository.save(novoAnimal);
    }

    //Lista todos os animais cadastrados pelo usuario logado
    public Page<ResponseAnimalDTO> listarAnimalPerdido(Pageable pageable){
        var autenticacao = SecurityContextHolder.getContext().getAuthentication();
        var email = autenticacao.getName();
        var usuario = usuarioRepository.findByEmail(email);
        Page<Animal> animais = animalRepository.findByUsuario((Usuario) usuario, pageable);
        return animais.map(ResponseAnimalDTO::new);
    }

    //Lista todos os animais cdastrados na plataforma para acesso público
    public Page<ResponsePublicoAnimalDTO> listarTodosOsAnimaisCadastrados(Pageable pageable){
        Page<Animal> animais = animalRepository.findAll(pageable);
        return animais.map(ResponsePublicoAnimalDTO::new);
    }
}
