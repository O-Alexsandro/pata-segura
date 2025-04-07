package br.com.pata.segura.domain.animal;

import java.time.LocalDate;

public record ResponsePublicoAnimalDTO (
        String nome,
        TipoAnimal tipo,
        String raca,
        String cor,
        PorteAnimal porte,
        StatusAnimal status,
        String descricao,
        LocalDate dataDesaparecimento,
        String nomeUsuario,
        String emailUsuario,
        String telefoneUsuario,
        String rua,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
    public ResponsePublicoAnimalDTO(Animal animal) {
        this(
                animal.getNome(),
                animal.getTipo(),
                animal.getRaca(),
                animal.getCor(),
                animal.getPorte(),
                animal.getStatus(),
                animal.getDescricao(),
                animal.getDataDesaparecimento(),
                animal.getUsuario().getNome(),
                animal.getUsuario().getEmail(),
                animal.getUsuario().getTelefone(),
                animal.getEnderecoAnimal().getRua(),
                animal.getEnderecoAnimal().getBairro(),
                animal.getEnderecoAnimal().getCidade(),
                animal.getEnderecoAnimal().getEstado(),
                animal.getEnderecoAnimal().getCep()
        );
    }
}