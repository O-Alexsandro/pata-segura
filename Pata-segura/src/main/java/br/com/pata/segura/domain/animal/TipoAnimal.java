package br.com.pata.segura.domain.animal;

public enum TipoAnimal {
    CACHORRO("Cachorro"),
    GATO("Gato");

    String tipoAnimal;

    TipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
}
