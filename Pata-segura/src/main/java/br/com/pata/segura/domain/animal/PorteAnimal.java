package br.com.pata.segura.domain.animal;

public enum PorteAnimal {
    PEQUENO("Pequeno"),
    MEDIO("Medio"),
    GRANDE("Grande");

    String porteAnimal;

    PorteAnimal(String porteAnimal) {
        this.porteAnimal = porteAnimal;
    }
}
