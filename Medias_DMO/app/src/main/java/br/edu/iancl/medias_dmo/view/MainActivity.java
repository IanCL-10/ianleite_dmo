package br.edu.iancl.medias_dmo.view;

import androidx.appcompat.app.AppCompatActivity;

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
    private Button mCalcularMediaButton;
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
        mCalcularMediaButton = findViewById(R.id.button_media);

        //Listener
        mCalcularMediaButton.setOnClickListener(this);

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
        if(view == mCalcularMediaButton){
            calcular();
        }
    }

    private void calcular(){
        boolean campoVazio = false;
        for(EditText edit : editTextList){
            if(TextUtils.isEmpty(edit.getText())){
                campoVazio = true;
            }
        }

        if(campoVazio){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
        else {
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
                double mediaAritimetica = MediaController.mediaArimetica(valores);
                Toast.makeText(this, String.format("Media: %.2f", mediaAritimetica), Toast.LENGTH_LONG).show();

            }catch (NumberFormatException ex) {
                Toast.makeText(this, "Valor não suportado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}