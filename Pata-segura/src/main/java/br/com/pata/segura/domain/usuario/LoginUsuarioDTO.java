package br.com.pata.segura.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginUsuarioDTO(
        @NotNull
        @Email
        String email,
        @NotNull
        String senha
) {
}
