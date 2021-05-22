package br.edu.iancl.medias_dmo.model;

import java.util.ArrayList;
import java.util.List;

public class MediaPonderada implements MediaStrategy{

    private Double value1;
    private Double value2;
    private Double value3;
    private Double value4;
    private Double value5;
    private int weight_value1;
    private int weight_value2;
    private int weight_value3;
    private int weight_value4;
    private int weight_value5;

    public MediaPonderada(Double value1, Double value2, Double value3, Double value4, Double value5, int weight_value1, int weight_value2, int weight_value3, int weight_value4, int weight_value5) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.weight_value1 = weight_value1;
        this.weight_value2 = weight_value2;
        this.weight_value3 = weight_value3;
        this.weight_value4 = weight_value4;
        this.weight_value5 = weight_value5;
    }

    @Override
    public Double calcularMedia() {
        Double sumValue = 0D;

        List<Double> valores = new ArrayList<>();
        valores.add(value1);
        valores.add(value2);
        valores.add(value3);
        valores.add(value4);
        valores.add(value5);

        List<Integer> pesos = new ArrayList<>();
        pesos.add(weight_value1);
        pesos.add(weight_value2);
        pesos.add(weight_value3);
        pesos.add(weight_value4);
        pesos.add(weight_value5);

        Integer sumWeight = 0;

        for(Integer peso: pesos){
            sumWeight += peso;
        }
        for(int i = 0; i < valores.size(); i++){
            sumValue += (valores.get(i) * pesos.get(i));
        }
        return sumValue/sumWeight;
    }
}
