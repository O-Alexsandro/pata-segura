package br.com.pata.segura.service;

import br.com.pata.segura.configuration.TokenService;
import br.com.pata.segura.domain.animal.*;
import br.com.pata.segura.domain.usuario.Usuario;
import br.com.pata.segura.repository.AnimalRepository;
import br.com.pata.segura.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    //Lista todos os animais cadastrados na plataforma - acesso público
    public Page<ResponsePublicoAnimalDTO> listarTodosOsAnimaisCadastrados(Pageable pageable){
        Page<Animal> animais = animalRepository.findAll(pageable);
        return animais.map(ResponsePublicoAnimalDTO::new);
    }

    //Lista todos os animais cadastrados na plataforma com filtros de busca - acesso público
    public Page<ResponseFiltroBuscaAnimalDTO> listarTodosOsAnimaisPorFiltro(FiltroBuscaAnimalDTO filtro, Pageable pageable){
        Specification<Animal> specs = Specification.where(null);

        if (filtro.nome() != null && !filtro.nome().isBlank()){
            specs = specs.and((root, query, builder) ->
                    builder.like(builder.lower(root.get("nome")), "%" + filtro.nome().toLowerCase() + "%"));
        }

        if (filtro.tipo() != null){
            specs = specs.and((root, query, builder) ->
                    builder.equal(builder.lower(root.get("tipo")), "%" + filtro.tipo().toString().toLowerCase() + "%"));
        }

        if (filtro.cor() != null && !filtro.cor().isBlank()){
            specs = specs.and((root, query, builder) ->
                    builder.like(builder.lower(root.get("cor")), "%" + filtro.cor().toLowerCase() + "%"));
        }

        StatusAnimal status = filtro.status() != null ? filtro.status() : StatusAnimal.PERDIDO;
        specs = specs.and((root, query, builder) ->
                builder.equal(root.get("status"), status));

        if (filtro.dataDesaparecimento() != null){
            specs = specs.and((root, query, builder) ->
                    builder.equal(builder.lower(root.get("dataDesaparecimento")), "%" + filtro.dataDesaparecimento() + "%"));
        }

        if (filtro.endereco() != null){
            if (filtro.endereco().cidade() != null && !filtro.endereco().cidade().isBlank()){
                specs = specs.and((root, query, builder) ->
                        builder.like(builder.lower(root.get("enderecoAnimal").get("cidade")), "%" + filtro.endereco().cidade().toLowerCase() + "%"));
            }

            if (filtro.endereco().cep() != null && !filtro.endereco().cep().isBlank()){
                specs = specs.and((root, query, builder) ->
                        builder.like(builder.lower(root.get("enderecoAnimal").get("cep")), "%" + filtro.endereco().cep().toLowerCase() + "%"));
            }
        }

        var retornoFiltro = animalRepository.findAll(specs, pageable);

        return retornoFiltro.map(ResponseFiltroBuscaAnimalDTO::fromEntity);
    }

    public ResponsePublicoAnimalDTO listarAnimalPorID(Long id){
        var animal = animalRepository.findById(id);

        if (animal.isEmpty()){
            throw new EntityNotFoundException("Animal não localizado");
        }

        var animalExtraido = animal.get();
        return new ResponsePublicoAnimalDTO(animalExtraido);
    }

    //Atualizar Animal
    public AtualizarAnimalDTO atualizarAnimalPorId(AtualizarAnimalDTO dados){
        var autenticacao = SecurityContextHolder.getContext().getAuthentication();
        var email = autenticacao.getName();

        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email);

        var animal = animalRepository.findById(dados.id()).orElseThrow(()-> new RuntimeException(""));

        if (usuario.getId().equals(animal.getUsuario().getId())){
            validarDadosDtoAtualizacao(dados);
        }

        animalRepository.save(animal);
        return new AtualizarAnimalDTO(animal);

    }

    public void validarDadosDtoAtualizacao(AtualizarAnimalDTO dados){

        var animal = animalRepository.findById(dados.id()).orElseThrow(()-> new RuntimeException(""));

        if (dados.nome() != null){
            animal.setNome(dados.nome());
        }
        if (dados.tipo() != null){
            animal.setTipo(dados.tipo());
        }
        if (dados.raca() != null){
            animal.setRaca(dados.raca());
        }
        if (dados.cor() != null){
            animal.setCor(dados.cor());
        }
        if (dados.porte() != null){
            animal.setPorte(dados.porte());
        }
        if (dados.status() != null){
            animal.setStatus(dados.status());
        }
        if (dados.descricao() != null){
            animal.setDescricao(dados.descricao());
        }
        if (dados.dataDesaparecimento() != null){
            animal.setDataDesaparecimento(dados.dataDesaparecimento());
        }
        if (dados.rua() != null){
            animal.getEnderecoAnimal().setRua(dados.rua());
        }

        if (dados.bairro() != null){
            animal.getEnderecoAnimal().setBairro(dados.bairro());
        }

        if (dados.cidade() != null){
            animal.getEnderecoAnimal().setCidade(dados.cidade());
        }

        if (dados.estado() != null){
            animal.getEnderecoAnimal().setEstado(dados.estado());
        }

        if (dados.cep() != null){
            animal.getEnderecoAnimal().setCep(dados.cep());
        }
    }

}
