package br.edu.iancl.conversormoeda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final float VALOR_DOLAR = 5.4f;
    private EditText mEntradaEdit;
    private Button mParaRealButton;
    private Button mParaDolarButton;
    private TextView mSaidaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEntradaEdit = findViewById(R.id.edit_entrada);
        mParaRealButton = findViewById(R.id.button_para_real);
        mParaDolarButton = findViewById(R.id.button_para_dolar);
        mSaidaText = findViewById(R.id.text_saida);

        mParaRealButton.setOnClickListener(this);
        mParaDolarButton.setOnClickListener(this);
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
            case R.id.button_para_dolar:
                calculo = entrada / VALOR_DOLAR;
                mSaidaText.setText("U$" + calculo);
                break;

            case R.id.button_para_real:
                calculo = entrada * VALOR_DOLAR;
                mSaidaText.setText("R$" + calculo);
                break;
        }


    }
}