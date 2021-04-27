package br.edu.iancl.conversortemperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEntradaEdit;
    private Button mParaCelsiusButton;
    private TextView mSaidaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEntradaEdit = findViewById(R.id.edit_entrada);
        mParaCelsiusButton = findViewById(R.id.button_para_celcius);
        mSaidaText = findViewById(R.id.text_saida);

        mParaCelsiusButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mParaCelsiusButton){
            float entrada, calculo;
            try {
                entrada = Float.valueOf(mEntradaEdit.getText().toString());
            } catch (NumberFormatException e) {
                entrada = 0;
            }
            calculo = (entrada - 32) /1.8f;
            mSaidaText.setText(String.format("%.2f ºC", calculo));
        }
    }
}