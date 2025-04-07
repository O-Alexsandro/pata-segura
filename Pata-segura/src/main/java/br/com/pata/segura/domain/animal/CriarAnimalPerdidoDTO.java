package br.com.pata.segura.domain.animal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CriarAnimalPerdidoDTO(
        String nome,
        @NotNull
        TipoAnimal tipo,
        @NotNull
        String raca,
        @NotNull
        String cor,
        @NotNull
        PorteAnimal porte,
        @NotNull
        StatusAnimal status,
        @NotNull
        String descricao,
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataDesaparecimento,
        @NotNull
        EnderecoDTO endereco
) {
}
