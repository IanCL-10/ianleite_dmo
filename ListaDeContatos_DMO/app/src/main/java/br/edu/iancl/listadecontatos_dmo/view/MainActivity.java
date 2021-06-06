package br.edu.iancl.listadecontatos_dmo.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.listadecontatos_dmo.R;
import br.edu.iancl.listadecontatos_dmo.adapter.ItemContatoAdapter;
import br.edu.iancl.listadecontatos_dmo.controller.ContatoController;
import br.edu.iancl.listadecontatos_dmo.model.Constantes;
import br.edu.iancl.listadecontatos_dmo.model.Contato;

public class MainActivity extends AppCompatActivity {

    private List<Contato> mContatos;
    private List<Contato> mContatosFavoritos;

    private RecyclerView mContatosRecyclerView;
    private ItemContatoAdapter mItemContatoAdapter;
    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContatos = ContatoController.allContatos();
        mContatosFavoritos = new ArrayList<>();
        mItemContatoAdapter = new ItemContatoAdapter(this, mContatos, mContatosFavoritos);
        mItemContatoAdapter.setClickListener(position -> detalharContato(position));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mContatosRecyclerView = findViewById(R.id.recycler_contatos);
        mContatosRecyclerView.setLayoutManager(layoutManager);
        mContatosRecyclerView.setAdapter(mItemContatoAdapter);

        mActionButton = findViewById(R.id.fab_add_contato);
        mActionButton.setOnClickListener(v -> newContato());
    }

    private void newContato() {
        Intent intent = new Intent(this, NovoContatoActivity.class);
        startActivityForResult(intent, Constantes.REQUEST_CODE_NEW_CONTATO);
    }

    private void detalharContato(int position){
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.KEY_NOME, mContatos.get(position).getNome());
        bundle.putString(Constantes.KEY_APELIDO, mContatos.get(position).getApelido());
        if(mContatos.get(position).getTelefone() != null){
            List<String> telefone = mContatos.get(position).getTelefone();
            StringBuilder telefoneString = new StringBuilder();
            for(String t: telefone){
                telefoneString.append("\n").append(t);
            }
            bundle.putString(Constantes.KEY_TELEFONE, String.valueOf(telefoneString));
        }
        if(mContatos.get(position).getEmail() != null){
            List<String> email = mContatos.get(position).getEmail();
            StringBuilder emailString = new StringBuilder();
            for(String e: email){
                emailString.append("\n").append(e);
            }
            bundle.putString(Constantes.KEY_EMAIL, String.valueOf(emailString));
        }
        Intent intent = new Intent(this, ContatoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void updateAdapter(){
        mItemContatoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constantes.REQUEST_CODE_NEW_CONTATO:
                    ContatoController.addContato(
                            data.getStringExtra(Constantes.KEY_NOME),
                            data.getStringExtra(Constantes.KEY_APELIDO),
                            data.getStringExtra(Constantes.KEY_TELEFONE),
                            data.getStringExtra(Constantes.KEY_EMAIL)
                    );
                    break;
            }
            updateAdapter();
        }
    }
}