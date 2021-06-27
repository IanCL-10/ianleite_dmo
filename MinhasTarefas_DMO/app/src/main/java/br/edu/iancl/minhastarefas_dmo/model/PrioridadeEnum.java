package br.edu.iancl.minhastarefas_dmo.model;

public enum PrioridadeEnum {

    BAIXA(1),
    MEDIA(2),
    ALTA(3);

    private final int valor;
    PrioridadeEnum(int valorEnum) {
        valor = valorEnum;
    }
}
