package br.com.pata.segura.domain.animal;

import br.com.pata.segura.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "animais")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private TipoAnimal tipo;
    private String raca;
    private String cor;
    private PorteAnimal porte;
    private StatusAnimal status;
    private String descricao;
    @Embedded
    private EnderecoAnimal enderecoAnimal;
    private LocalDate dataDesaparecimento;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Animal(CriarAnimalPerdidoDTO dados) {
        this.nome = dados.nome();
        this.tipo = dados.tipo();
        this.raca = dados.raca();
        this.cor = dados.cor();
        this.porte = dados.porte();
        this.status = dados.status();
        this.descricao = dados.descricao();
        this.dataDesaparecimento = dados.dataDesaparecimento();
        this.usuario = usuario;
        this.enderecoAnimal = new EnderecoAnimal(
                dados.endereco().rua(),
                dados.endereco().bairro(),
                dados.endereco().cidade(),
                dados.endereco().estado(),
                dados.endereco().cep()
        );
    }
}
