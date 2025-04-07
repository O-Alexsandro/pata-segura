package br.com.pata.segura.controller;

import br.com.pata.segura.domain.usuario.AtualizarUsuarioDTO;
import br.com.pata.segura.domain.usuario.CriarUsuarioDTO;
import br.com.pata.segura.domain.usuario.Usuario;
import br.com.pata.segura.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        var usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid CriarUsuarioDTO dados){
        var novoUsuario = usuarioService.criarUsuario(dados);
        return ResponseEntity.ok(novoUsuario);
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody @Valid AtualizarUsuarioDTO dados){
        var usuario = usuarioService.atualizarUsuario(dados);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> excluirUsuario(@PathVariable Long id){
        usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
