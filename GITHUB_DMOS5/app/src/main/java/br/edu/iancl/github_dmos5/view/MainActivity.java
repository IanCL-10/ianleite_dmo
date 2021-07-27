package br.edu.iancl.github_dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.iancl.github_dmos5.R;
import br.edu.iancl.github_dmos5.constants.Constants;

public class MainActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private Button mBuscarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsernameEditText = findViewById(R.id.edit_username);
        mBuscarButton = findViewById(R.id.button_buscar);
        mBuscarButton.setOnClickListener(v -> buscarRepositorios());
    }

    private void buscarRepositorios(){
        if(!mUsernameEditText.getText().toString().isEmpty()) {
            String username = mUsernameEditText.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.KEY_USERNAME, username);

            Intent intent = new Intent(this, RepositorioActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Preencha o campo", Toast.LENGTH_SHORT).show();
        }


    }
}