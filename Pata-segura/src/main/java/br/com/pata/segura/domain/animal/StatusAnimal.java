package br.com.pata.segura.domain.animal;

public enum StatusAnimal {
    PERDIDO("Perdido"),
    ENCONTRADO("Encontrado");

    String statusAnimal;

    StatusAnimal(String statusAnimal) {
        this.statusAnimal = statusAnimal;
    }
}
