package br.com.pata.segura.domain.animal;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizarAnimalDTO(
        @NotNull
        Long id,
        String nome,
        TipoAnimal tipo,
        String raca,
        String cor,
        PorteAnimal porte,
        StatusAnimal status,
        String descricao,
        LocalDate dataDesaparecimento,
        String rua,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
    public AtualizarAnimalDTO(Animal animal) {
        this(
                animal.getId(),
                animal.getNome(),
                animal.getTipo(),
                animal.getRaca(),
                animal.getCor(),
                animal.getPorte(),
                animal.getStatus(),
                animal.getDescricao(),
                animal.getDataDesaparecimento(),
                animal.getEnderecoAnimal().getRua(),
                animal.getEnderecoAnimal().getBairro(),
                animal.getEnderecoAnimal().getCidade(),
                animal.getEnderecoAnimal().getEstado(),
                animal.getEnderecoAnimal().getCep()
        );
    }
}