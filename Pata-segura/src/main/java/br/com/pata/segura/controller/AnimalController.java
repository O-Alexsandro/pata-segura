package br.com.pata.segura.controller;

import br.com.pata.segura.domain.animal.*;
import br.com.pata.segura.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<Animal> criarAnimal(@RequestBody @Valid CriarAnimalPerdidoDTO dados){
        var novoAnimal = animalService.criarAnimalPerdido(dados);
        return ResponseEntity.ok(novoAnimal);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseAnimalDTO>> listarAnimaisPorUsuario(Pageable pageable){
        var animais = animalService.listarAnimalPerdido(pageable);
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/publico")
    public ResponseEntity<Page<ResponsePublicoAnimalDTO>> listarTodosOsAnimais(Pageable pageable){
        var animais = animalService.listarTodosOsAnimaisCadastrados(pageable);
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/publico/{id}")
    public ResponseEntity<ResponsePublicoAnimalDTO> listarAnimaisPorId(@PathVariable long id){
        var animais = animalService.listarAnimalPorID(id);
        return ResponseEntity.ok(animais);
    }

    @PostMapping("/publico/filtros")
    public ResponseEntity<Page<ResponseFiltroBuscaAnimalDTO>> listarTodosOsAnimaisComFiltro(@RequestBody FiltroBuscaAnimalDTO filtro, Pageable pageable){
        var filtragem = animalService.listarTodosOsAnimaisPorFiltro(filtro, pageable);
        return ResponseEntity.ok(filtragem);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<AtualizarAnimalDTO> atualizarAnimalPorId(@RequestBody @Valid AtualizarAnimalDTO dados){
        var animalAtualizado = animalService.atualizarAnimalPorId(dados);
        return ResponseEntity.ok(animalAtualizado);
    }
}
