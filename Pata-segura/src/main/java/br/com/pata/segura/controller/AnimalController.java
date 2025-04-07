package br.com.pata.segura.controller;

import br.com.pata.segura.domain.animal.Animal;
import br.com.pata.segura.domain.animal.CriarAnimalPerdidoDTO;
import br.com.pata.segura.domain.animal.ResponseAnimalDTO;
import br.com.pata.segura.domain.animal.ResponsePublicoAnimalDTO;
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
}
