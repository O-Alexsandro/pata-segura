package br.com.pata.segura.controller;

import br.com.pata.segura.configuration.TokenService;
import br.com.pata.segura.domain.usuario.LoginUsuarioDTO;
import br.com.pata.segura.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid LoginUsuarioDTO dados){
        var login = usuarioService.login(dados);
        return ResponseEntity.ok(login);
    }
}
