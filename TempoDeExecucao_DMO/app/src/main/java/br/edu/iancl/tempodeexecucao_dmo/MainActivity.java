package br.edu.iancl.tempodeexecucao_dmo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.time.Duration;
import java.time.LocalDateTime;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView criacaoTextView;
    private TextView pausadoTextView;
    private TextView paradoTextView;
    private Button execucaoButton;

    private LocalDateTime tempoPausado;
    private long segundosPausado;
    private LocalDateTime tempoParado;
    private long segundosParado;
    private LocalDateTime tempoExecutando;
    private long segundosExecutando;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalDateTime tempoInicio = LocalDateTime.now();
        segundosPausado = 0;
        segundosParado = 0;
        segundosExecutando = 0;

        criacaoTextView = findViewById(R.id.view_criacao);
        pausadoTextView = findViewById(R.id.view_pausado);
        paradoTextView = findViewById(R.id.view_parado);

        execucaoButton = findViewById(R.id.button_execucao);
        execucaoButton.setOnClickListener(this);

        LocalDateTime tempoFinal = LocalDateTime.now();

        Duration d = Duration.between(tempoInicio, tempoFinal);
        long segundosCriado = d.getSeconds();
        criacaoTextView.setText(getText(R.string.view_criacao) + " " + segundosCriado + "s");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPause() {
        super.onPause();
        tempoPausado = LocalDateTime.now();
        if(tempoExecutando != null){
            LocalDateTime tempoPausa = LocalDateTime.now();
            Duration d = Duration.between(tempoExecutando, tempoPausa);
            segundosExecutando += d.getSeconds();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        LocalDateTime tempoResumo = LocalDateTime.now();
        if(tempoPausado != null){
            Duration d = Duration.between(tempoPausado, tempoResumo);
            segundosPausado += d.getSeconds();
            pausadoTextView.setText(getText(R.string.view_pausado) + " " + segundosPausado + "s");
        }
        tempoExecutando = LocalDateTime.now();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStop() {
        super.onStop();
        tempoParado = LocalDateTime.now();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onRestart() {
        super.onRestart();
        if(tempoParado != null){
            LocalDateTime tempoResumo = LocalDateTime.now();
            Duration d = Duration.between(tempoParado, tempoResumo);
            segundosParado += d.getSeconds();
            paradoTextView.setText(getText(R.string.view_parado) + " " + segundosParado + "s");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();
        if(tempoExecutando == null){
            tempoExecutando = LocalDateTime.now();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        if(view == execucaoButton){
            mostrarTempoExecucao();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void mostrarTempoExecucao(){
        if(tempoExecutando != null){
            LocalDateTime tempoPausa = LocalDateTime.now();
            Duration d = Duration.between(tempoExecutando, tempoPausa);
            segundosExecutando += d.getSeconds();
            tempoExecutando = LocalDateTime.now();
        }
        Toast.makeText(this,String.format("O tempo de execução é de %ds", segundosExecutando), Toast.LENGTH_SHORT).show();
    }
}