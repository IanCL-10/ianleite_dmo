package br.edu.iancl.medias_dmo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.medias_dmo.R;
import br.edu.iancl.medias_dmo.controller.MediaController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mValor1EditText;
    private EditText mValor2EditText;
    private EditText mValor3EditText;
    private EditText mValor4EditText;
    private EditText mValor5EditText;
    private Button mMediaAritimeticaButton;
    private Button mMediaHarmonicaButton;
    private Button mMediaPonderadaButton;
    private List<EditText> editTextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencias
        mValor1EditText = findViewById(R.id.edit_valor1);
        mValor2EditText = findViewById(R.id.edit_valor2);
        mValor3EditText = findViewById(R.id.edit_valor3);
        mValor4EditText = findViewById(R.id.edit_valor4);
        mValor5EditText = findViewById(R.id.edit_valor5);
        mMediaAritimeticaButton = findViewById(R.id.button_aritimetica);
        mMediaHarmonicaButton = findViewById(R.id.button_harmonica);
        mMediaPonderadaButton = findViewById(R.id.button_ponderada);

        //Listener
        mMediaAritimeticaButton.setOnClickListener(this);
        mMediaHarmonicaButton.setOnClickListener(this);
        mMediaPonderadaButton.setOnClickListener(this);

        //Lista
        editTextList = new ArrayList<>();
        editTextList.add(mValor1EditText);
        editTextList.add(mValor2EditText);
        editTextList.add(mValor3EditText);
        editTextList.add(mValor4EditText);
        editTextList.add(mValor5EditText);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_aritimetica:
                calcularMediaAritimetica();
                break;
            case R.id.button_harmonica:
                calcularMediaHarmonica();
                break;
            case R.id.button_ponderada:
                calcularMediaPonderada();
                break;
        }
    }

    private boolean checarCampos(){
        for(EditText edit : editTextList){
            if(TextUtils.isEmpty(edit.getText())){
                return true;
            }
        }
        return false;
    }

    private List<Double> retornaValores(){
        double v1 = 0, v2 = 0, v3 = 0, v4 = 0, v5 = 0;
        List<Double> valores = new ArrayList<>();
        try {
            v1 = Double.parseDouble(mValor1EditText.getText().toString());
            v2 = Double.parseDouble(mValor2EditText.getText().toString());
            v3 = Double.parseDouble(mValor3EditText.getText().toString());
            v4 = Double.parseDouble(mValor4EditText.getText().toString());
            v5 = Double.parseDouble(mValor5EditText.getText().toString());
            valores.add(v1);
            valores.add(v2);
            valores.add(v3);
            valores.add(v4);
            valores.add(v5);
        }catch (NumberFormatException ex) {
            Toast.makeText(this, "Valor não suportado", Toast.LENGTH_SHORT).show();
        }
        return valores;
    }

    private Bundle retornaBundle(){
        List<Double> valores = retornaValores();
        Bundle valoresBundle = new Bundle();
        valoresBundle.putDouble("v1", valores.get(0));
        valoresBundle.putDouble("v2", valores.get(1));
        valoresBundle.putDouble("v3", valores.get(2));
        valoresBundle.putDouble("v4", valores.get(3));
        valoresBundle.putDouble("v5", valores.get(4));
        return  valoresBundle;
    }

    private void calcularMediaAritimetica(){
        if(checarCampos()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
        else {
            Bundle valoresBundle = retornaBundle();
            Intent intent = new Intent(this, MediaAritimeticaActivity.class);
            intent.putExtras(valoresBundle);
            startActivity(intent);
        }
    }

    private void calcularMediaHarmonica(){
        if(checarCampos()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
        else {
            Bundle valoresBundle = retornaBundle();
            Intent intent = new Intent(this, MediaHarmonicaActivity.class);
            intent.putExtras(valoresBundle);
            startActivity(intent);
        }
    }

    private void calcularMediaPonderada(){
        if(checarCampos()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
        else {
            Bundle valoresBundle = retornaBundle();
            Intent intent = new Intent(this, MediaPonderadaActivity.class);
            intent.putExtras(valoresBundle);
            startActivity(intent);
        }
    }

}

/*
Muito bom! Parabéns.

Fique atento a construção do layout, por exemplo, o activity de média ponderada sobrepõe elementos
em telas menores.

Nota 10.
 */