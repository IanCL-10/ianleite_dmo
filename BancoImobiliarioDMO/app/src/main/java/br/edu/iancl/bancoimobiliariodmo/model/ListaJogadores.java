package br.edu.iancl.bancoimobiliariodmo.model;

import java.util.Arrays;
import java.util.List;

public class ListaJogadores {

    private static final int MAX_JOGADORES = 6;
    private List<Jogador> listaJogadores;

    public ListaJogadores() {
        this.listaJogadores = Arrays.asList(new Jogador[MAX_JOGADORES]);
        for (int i = 0; i < listaJogadores.size(); i++){
            this.listaJogadores.get(i).setId(i+1);
        }
    }

    public Jogador getJogadorByID(int id){
        return listaJogadores.get(id);
    }
    /*public void addJogador(Jogador jogador){
        if(listaJogadores.size() < MAX_JOGADORES){
            listaJogadores.add(jogador);
        }
    }*/
}
