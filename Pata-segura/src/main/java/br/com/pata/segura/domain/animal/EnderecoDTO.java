package br.com.pata.segura.domain.animal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
        @NotNull
        String rua,
        @NotNull
        String bairro,
        @NotNull
        String cidade,
        @NotNull
        String estado,
        @NotNull
        @Pattern(regexp = "\\d{5}-\\d{3}")
        String cep
) {
}
