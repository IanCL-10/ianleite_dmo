package br.edu.iancl.github_dmos5.model;

import java.io.Serializable;

public class Repositorio implements Serializable {

    private String name;

    public Repositorio(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                name +
                '}';
    }
}
