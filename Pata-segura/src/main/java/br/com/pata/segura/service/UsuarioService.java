package br.com.pata.segura.service;

import br.com.pata.segura.configuration.TokenService;
import br.com.pata.segura.domain.usuario.AtualizarUsuarioDTO;
import br.com.pata.segura.domain.usuario.CriarUsuarioDTO;
import br.com.pata.segura.domain.usuario.LoginUsuarioDTO;
import br.com.pata.segura.domain.usuario.Usuario;
import br.com.pata.segura.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario criarUsuario(CriarUsuarioDTO dados){
        Usuario novoUsuario = new Usuario(dados);
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dados.senha());
        novoUsuario.setSenha(senhaCriptografada);
        return usuarioRepository.save(novoUsuario);
    }

    public Usuario atualizarUsuario(AtualizarUsuarioDTO dados){
        var usuario = usuarioRepository.findById(dados.id()).orElseThrow(()-> new EntityNotFoundException("Usuário não localizado"));

        if (dados.nome() != null){
            usuario.setNome(dados.nome());
        }

        if (dados.email() != null){
            usuario.setEmail(dados.email());
        }

        if (dados.senha() != null){
            usuario.setSenha(dados.senha());
        }

        if (dados.telefone() != null){
            usuario.setTelefone(dados.telefone());
        }

        return usuarioRepository.save(usuario);
    }

    public void excluirUsuario(Long id){
        var usuario = usuarioRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Usuário não localizado"));
        usuarioRepository.deleteById(id);
    }

    public String login(LoginUsuarioDTO dados){
        var dadosUsuario = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var autenticacao = this.authenticationManager.authenticate(dadosUsuario);

        var token = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());
        return token;
    }

}
