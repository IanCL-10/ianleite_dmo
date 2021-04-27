package br.edu.iancl.conversortemperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEntradaEdit;
    private Button mParaCelsiusButton;
    private Button mParaFahrenheitButton;
    private TextView mSaidaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEntradaEdit = findViewById(R.id.edit_entrada);
        mParaCelsiusButton = findViewById(R.id.button_para_celcius);
        mParaFahrenheitButton = findViewById(R.id.button_para_fahrenheit);
        mSaidaText = findViewById(R.id.text_saida);

        mParaCelsiusButton.setOnClickListener(this);
        mParaFahrenheitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        float entrada, calculo;
        try {
            entrada = Float.valueOf(mEntradaEdit.getText().toString());
        } catch (NumberFormatException e) {
            entrada = 0;
        }
        switch (view.getId()) {
            case R.id.button_para_celcius:
                calculo = (entrada - 32) / 1.8f;
                mSaidaText.setText(String.format("%.2f ºC", calculo));
                break;

            case R.id.button_para_fahrenheit:
                calculo = 1.8f * entrada + 32;
                mSaidaText.setText(String.format("%.2f ºF", calculo));
                break;
        }
    }
}