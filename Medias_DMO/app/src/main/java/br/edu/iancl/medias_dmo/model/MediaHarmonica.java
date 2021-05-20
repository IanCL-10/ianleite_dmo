package br.edu.iancl.medias_dmo.model;

import java.util.List;

public class MediaHarmonica implements MediaStrategy{

    private List<Double> values;

    public MediaHarmonica(List<Double> values) {
        this.values = values;
    }

    @Override
    public Double calcularMedia() {
        //TODO implementar o método.
        return null;
    }
}
