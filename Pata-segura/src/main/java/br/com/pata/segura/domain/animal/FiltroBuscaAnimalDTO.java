package br.com.pata.segura.domain.animal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record FiltroBuscaAnimalDTO(
        String nome,
        TipoAnimal tipo,
        String raca,
        String cor,
        PorteAnimal porte,
        StatusAnimal status,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataDesaparecimento,
        FiltroBuscaEnderecoAnimalDTO endereco
) {
}
