package br.edu.iancl.meutreino.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.meutreino.R;
import br.edu.iancl.meutreino.controller.TreinoController;

public class NovoTreinoActivity extends AppCompatActivity {

    private EditText mNomeEditText;
    private EditText mInstrucoesEditText;
    private EditText mImagemEditText;
    private Button mSalvarButton;

    private List<EditText> campos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_treino);

        if(getActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mNomeEditText = findViewById(R.id.edit_nome_exc);
        mInstrucoesEditText = findViewById(R.id.edit_instrucoes_exc);
        mImagemEditText = findViewById(R.id.edit_imagem_exc);
        mSalvarButton = findViewById(R.id.button_salvar);

        mSalvarButton.setOnClickListener(v -> salvar());

        campos.add(mNomeEditText);
        campos.add(mInstrucoesEditText);
        campos.add(mImagemEditText);
    }

    private void salvar(){
        if(checarCampos()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
        else{
            if(TreinoController.salvarTreino(this, mNomeEditText.getText().toString(),
                    mImagemEditText.getText().toString(), mInstrucoesEditText.getText().toString())){
                showToast(getString(R.string.message_success));
            }else{
                showToast(getString(R.string.message_erros));
            }
            finish();
        }
    }

    private boolean checarCampos(){
        for(EditText edit : campos){
            if(TextUtils.isEmpty(edit.getText())){
                return true;
            }
        }
        return false;
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}