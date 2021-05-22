package br.edu.iancl.medias_dmo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.medias_dmo.R;
import br.edu.iancl.medias_dmo.controller.MediaController;

public class MediaHarmonicaActivity extends AppCompatActivity {

    private TextView valor1TextView;
    private TextView valor2TextView;
    private TextView valor3TextView;
    private TextView valor4TextView;
    private TextView valor5TextView;
    private TextView resultadoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_harmonica);

        valor1TextView = findViewById(R.id.view_valor1);
        valor2TextView = findViewById(R.id.view_valor2);
        valor3TextView = findViewById(R.id.view_valor3);
        valor4TextView = findViewById(R.id.view_valor4);
        valor5TextView = findViewById(R.id.view_valor5);
        resultadoTextView = findViewById(R.id.view_media);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        List<Double> valores = new ArrayList<>();
        valores.add(bundle.getDouble("v1", -1));
        valores.add(bundle.getDouble("v2", -1));
        valores.add(bundle.getDouble("v3", -1));
        valores.add(bundle.getDouble("v4", -1));
        valores.add(bundle.getDouble("v5", -1));
        valor1TextView.setText("Valor 1: " + valores.get(0));
        valor2TextView.setText("Valor 2: " + valores.get(1));
        valor3TextView.setText("Valor 3: " + valores.get(2));
        valor4TextView.setText("Valor 4: " + valores.get(3));
        valor5TextView.setText("Valor 5: " + valores.get(4));

        calcular(valores);
    }

    private void calcular(List<Double> valores){
        double mediaHarmonica = MediaController.mediaHarmonica(valores);
        resultadoTextView.setText(String.format("%.2f", mediaHarmonica));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}