package br.edu.iancl.meutreino.model;

public class Treino {

    private int id;
    private String nome;
    private String img;
    private String instrucoes;

    public Treino(String nome, String img, String instrucoes) {
        this.id = 0;
        this.nome = nome;
        this.img = img;
        this.instrucoes = instrucoes;
    }

    public Treino(int id, String nome, String img, String instrucoes) {
        this.nome = nome;
        this.img = img;
        this.instrucoes = instrucoes;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome.toUpperCase();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    @Override
    public String toString() {
        return getNome();
    }
}


