package br.com.pata.segura.domain.animal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ResponseFiltroBuscaAnimalDTO(
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
    public static ResponseFiltroBuscaAnimalDTO fromEntity(Animal animal){
        return new ResponseFiltroBuscaAnimalDTO(
                animal.getNome(),
                animal.getTipo(),
                animal.getRaca(),
                animal.getCor(),
                animal.getPorte(),
                animal.getStatus(),
                animal.getDescricao(),
                animal.getDataDesaparecimento(),
                new FiltroBuscaEnderecoAnimalDTO(
                        animal.getEnderecoAnimal().getCidade(),
                        animal.getEnderecoAnimal().getCep()
                )
        );
    }
}
