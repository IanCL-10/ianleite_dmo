package br.edu.iancl.medias_dmo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.medias_dmo.R;
import br.edu.iancl.medias_dmo.controller.MediaController;

public class MediaPonderadaActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView valor1TextView;
    private TextView valor2TextView;
    private TextView valor3TextView;
    private TextView valor4TextView;
    private TextView valor5TextView;
    private TextView resultadoTextView;

    private EditText peso1EditText;
    private EditText peso2EditText;
    private EditText peso3EditText;
    private EditText peso4EditText;
    private EditText peso5EditText;
    private Button calcularMediaButton;
    private List<Double> valores;

    private List<EditText> editTextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_ponderada);


        valor1TextView = findViewById(R.id.view_valor1);
        valor2TextView = findViewById(R.id.view_valor2);
        valor3TextView = findViewById(R.id.view_valor3);
        valor4TextView = findViewById(R.id.view_valor4);
        valor5TextView = findViewById(R.id.view_valor5);
        resultadoTextView = findViewById(R.id.view_media);

        peso1EditText = findViewById(R.id.edit_peso1);
        peso2EditText = findViewById(R.id.edit_peso2);
        peso3EditText = findViewById(R.id.edit_peso3);
        peso4EditText = findViewById(R.id.edit_peso4);
        peso5EditText = findViewById(R.id.edit_peso5);
        calcularMediaButton = findViewById(R.id.button_calcular_ponderada);

        calcularMediaButton.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        valores = new ArrayList<>();
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

        editTextList = new ArrayList<>();
        editTextList.add(peso1EditText);
        editTextList.add(peso2EditText);
        editTextList.add(peso3EditText);
        editTextList.add(peso4EditText);
        editTextList.add(peso5EditText);

    }

    private boolean checarCampos(){
        for(EditText edit : editTextList){
            if(TextUtils.isEmpty(edit.getText())){
                return true;
            }
        }
        return false;
    }

    private List<Integer> retornaPesos(){
        List<Integer> pesos = new ArrayList<>();
        try {
            pesos.add(Integer.parseInt(peso1EditText.getText().toString()));
            pesos.add(Integer.parseInt(peso2EditText.getText().toString()));
            pesos.add(Integer.parseInt(peso3EditText.getText().toString()));
            pesos.add(Integer.parseInt(peso4EditText.getText().toString()));
            pesos.add(Integer.parseInt(peso5EditText.getText().toString()));
        }catch (NumberFormatException ex) {
            Toast.makeText(this, "Valor não suportado", Toast.LENGTH_SHORT).show();
        }
        return pesos;
    }

    @Override
    public void onClick(View view) {
        if(view == calcularMediaButton){
            if(checarCampos()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
            else {
                List<Integer> pesos = retornaPesos();
                double mediaPonderada = MediaController.mediaPonderada(valores, pesos);
                resultadoTextView.setText(String.format("%.2f", mediaPonderada));
            }
        }
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