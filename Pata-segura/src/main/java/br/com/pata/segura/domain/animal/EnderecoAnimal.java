package br.com.pata.segura.domain.animal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoAnimal {
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
