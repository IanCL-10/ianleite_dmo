package br.edu.iancl.listadecontatos_dmo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.listadecontatos_dmo.R;
import br.edu.iancl.listadecontatos_dmo.model.Constantes;

public class NovoContatoActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText mNomeEditText;
    private EditText mApelidoEditText;
    private EditText mTelefoneEditText;
    private EditText mEmailEditText;
    private Button mSalvarButton;

    private List<EditText> editTextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_contato);

        if(getActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mNomeEditText = findViewById(R.id.edit_nome);
        mApelidoEditText = findViewById(R.id.edit_apelido);
        mTelefoneEditText = findViewById(R.id.edit_telefone);
        mEmailEditText = findViewById(R.id.edit_email);
        mSalvarButton = findViewById(R.id.button_save);
        mSalvarButton.setOnClickListener(this);

        editTextList = new ArrayList<>();
        editTextList.add(mNomeEditText);
        editTextList.add(mApelidoEditText);
    }

    @Override
    public void onClick(View view) {
        if(view == mSalvarButton){
            Intent intent = new Intent();
            if(checarCampos()){
                Toast.makeText(this, "Preencha o nome e o apelido", Toast.LENGTH_SHORT).show();
            }
            else {
                intent.putExtra(Constantes.KEY_NOME, mNomeEditText.getText().toString());
                intent.putExtra(Constantes.KEY_APELIDO, mApelidoEditText.getText().toString());
                if(!TextUtils.isEmpty(mTelefoneEditText.getText())){
                    intent.putExtra(Constantes.KEY_TELEFONE, mTelefoneEditText.getText().toString());
                }
                else {
                    intent.putExtra(Constantes.KEY_TELEFONE, "");
                }
                if(!TextUtils.isEmpty(mEmailEditText.getText())){
                    intent.putExtra(Constantes.KEY_EMAIL, mEmailEditText.getText().toString());
                }
                else {
                    intent.putExtra(Constantes.KEY_EMAIL, "");
                }


                setResult(Activity.RESULT_OK, intent);
                Toast.makeText(this, getString(R.string.success_message), Toast.LENGTH_SHORT).show();
                finish();
            }
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}