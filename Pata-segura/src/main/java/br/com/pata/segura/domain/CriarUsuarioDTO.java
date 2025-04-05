package br.com.pata.segura.domain;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CriarUsuarioDTO(
        @NotNull
        String nome,
        @NotNull
        @Email
        String email,
        @NotNull
        String senha,
        @NotNull
        String telefone) {}
