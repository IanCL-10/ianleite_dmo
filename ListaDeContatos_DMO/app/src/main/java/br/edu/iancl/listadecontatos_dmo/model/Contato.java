package br.edu.iancl.listadecontatos_dmo.model;

import java.util.List;

public class Contato {

    private String nome;
    private String apelido;
    private List<String> telefone;
    private List<String> email;
    private boolean favorite;

    public Contato(String nome, String apelido) {
        this.setNome(nome);
        this.setApelido(apelido);
        this.setTelefone(null);
        this.setEmail(null);
        this.favorite = false;
    }

    public Contato(String nome, String apelido, List<String> telefone, List<String> email) {
        this.setNome(nome);
        this.setApelido(apelido);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.favorite = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public List<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<String> telefone) {
        this.telefone = telefone;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
