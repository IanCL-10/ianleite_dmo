package br.edu.iancl.bancoimobiliariodmo.model;

public class Jogador {

    private int id;
    private double dinheiro;

    public Jogador(int id) {
        this.id = id;
        this.dinheiro = 25000;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(double dinheiro) {
        this.dinheiro = dinheiro;
    }

    public void enviar(double valor){
        this.dinheiro -= valor;
    }

    public void receber(double valor){
        this.dinheiro += valor;
    }
}
