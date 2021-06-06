package br.edu.iancl.listadecontatos_dmo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.iancl.listadecontatos_dmo.R;
import br.edu.iancl.listadecontatos_dmo.model.Constantes;

public class ContatoActivity extends AppCompatActivity {

    private TextView nomeTextView;
    private TextView apelidoTextView;
    private TextView telefoneTextView;
    private TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        if(getActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        nomeTextView = findViewById(R.id.view_nome);
        apelidoTextView = findViewById(R.id.view_apelido);
        telefoneTextView = findViewById(R.id.view_telefone);
        emailTextView = findViewById(R.id.view_email);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            String nome = bundle.getString(Constantes.KEY_NOME);
            String apelido = bundle.getString(Constantes.KEY_APELIDO);

            if(bundle.containsKey(Constantes.KEY_TELEFONE) != false){
                String telefone = bundle.getString(Constantes.KEY_TELEFONE);
                telefoneTextView.setText(telefone);
            }else {
                telefoneTextView.setText("Nenhum telefone encontrado...");
            }

            if(bundle.containsKey(Constantes.KEY_EMAIL) != false){
                String email = bundle.getString(Constantes.KEY_EMAIL);
                emailTextView.setText(email);
            }else{
                emailTextView.setText("Nenhum email encontrado");
            }

            nomeTextView.setText(nome);
            apelidoTextView.setText(apelido);
        }
    }
}