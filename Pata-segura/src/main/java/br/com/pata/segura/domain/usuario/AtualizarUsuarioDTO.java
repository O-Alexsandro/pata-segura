package br.com.pata.segura.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AtualizarUsuarioDTO(
        @NotNull
        Long id,
        String nome,
        @Email
        String email,
        String senha,
        String telefone
) {
}
