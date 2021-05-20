package br.edu.iancl.medias_dmo.controller;

import java.util.Arrays;
import java.util.List;

import br.edu.iancl.medias_dmo.model.MediaAritmetica;
import br.edu.iancl.medias_dmo.model.MediaHarmonica;
import br.edu.iancl.medias_dmo.model.MediaPonderada;
import br.edu.iancl.medias_dmo.model.MediaStrategy;

public class MediaController {

    public static Double mediaPonderada(/*Indicar argumentos, se necessário*/){
        MediaPonderada ponderada = null;
        //TODO implementar o restante do método
        return media(ponderada);
    }

    public static Double mediaArimetica(List<Double> valores){
        MediaAritmetica aritmetica = null;
        //TODO implementar o restante do método
        double v1 = valores.get(0);
        double v2 = valores.get(1);
        double v3 = valores.get(2);
        double v4 = valores.get(3);
        double v5 = valores.get(4);
        aritmetica = new MediaAritmetica(v1, v2, v3, v4, v5);
        return media(aritmetica);
    }

    public static Double mediaHarmonica(/*Indicar argumentos, se necessário*/){
        MediaHarmonica harmonica = null;
        //TODO implementar o restante do método
        return media(harmonica);
    }

    private static Double media(MediaStrategy mediaStrategy){
        return mediaStrategy.calcularMedia();
    }

}
