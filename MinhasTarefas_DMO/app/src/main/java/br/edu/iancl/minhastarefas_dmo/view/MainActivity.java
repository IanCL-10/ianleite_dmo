package br.edu.iancl.minhastarefas_dmo.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.iancl.minhastarefas_dmo.R;
import br.edu.iancl.minhastarefas_dmo.adapter.ItemTarefaAdapter;
import br.edu.iancl.minhastarefas_dmo.adapter.ItemTarefaConcluidaAdapter;
import br.edu.iancl.minhastarefas_dmo.controller.TarefaController;
import br.edu.iancl.minhastarefas_dmo.model.Constantes;
import br.edu.iancl.minhastarefas_dmo.model.PrioridadeEnum;
import br.edu.iancl.minhastarefas_dmo.model.Tarefa;

public class MainActivity extends AppCompatActivity {

    private List<Tarefa> mTarefas;
    private List<Tarefa> mTarefasConcluidas;

    private RecyclerView mTarefasRecyclerView;
    private ItemTarefaAdapter mitemTarefaAdapter;
    private ItemTarefaConcluidaAdapter mItemTarefaConcluidaAdapter;

    private FloatingActionButton mNovaTarefaActionButton;
    private FloatingActionButton mTarefasConcluidasActionButton;

    private int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTarefas = TarefaController.allTarefas();
        mTarefasConcluidas = new ArrayList<>();
        mitemTarefaAdapter = new ItemTarefaAdapter(this, mTarefas, mTarefasConcluidas);
        mitemTarefaAdapter.setClickListener(position -> detalharTarefa(position));

        Collections.sort(mTarefas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mTarefasRecyclerView = findViewById(R.id.recycler_tarefas);
        mTarefasRecyclerView.setLayoutManager(layoutManager);
        mTarefasRecyclerView.setAdapter(mitemTarefaAdapter);

        mNovaTarefaActionButton = findViewById(R.id.fab_add_tarefa);
        mNovaTarefaActionButton.setOnClickListener(v -> newTarefa());

        mTarefasConcluidasActionButton = findViewById(R.id.fab_lista_concluidas);
        mTarefasConcluidasActionButton.setOnClickListener(v -> mostrarConcluidas());
        contador = 0;
    }

    private void newTarefa() {
        Intent intent = new Intent(this, TarefaActivity.class);
        startActivityForResult(intent, Constantes.REQUEST_CODE_NEW_TAREFA);
    }

    private void detalharTarefa(int position){
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.KEY_TITULO, mTarefas.get(position).getTitulo());
        bundle.putString(Constantes.KEY_DESCRICAO, mTarefas.get(position).getDescricao());
        bundle.putString(Constantes.KEY_DATA, mTarefas.get(position).getDataLimite());

        Intent intent = new Intent(this, TarefaActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Constantes.REQUEST_CODE_UPDATE_TAREFA);
    }

    private void mostrarConcluidas(){
        if(contador % 2 == 0){
            mItemTarefaConcluidaAdapter = new ItemTarefaConcluidaAdapter(this, mTarefasConcluidas);
            mItemTarefaConcluidaAdapter.setClickListener(position -> detalharTarefa(position));
            mTarefasRecyclerView.setAdapter(mItemTarefaConcluidaAdapter);
        } else{
            mTarefasRecyclerView.setAdapter(mitemTarefaAdapter);
        }
        updateAdapter();
        contador++;
    }

    private void updateAdapter(){
        Collections.sort(mTarefas);
        mitemTarefaAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constantes.REQUEST_CODE_NEW_TAREFA:
                    TarefaController.addTarefa(
                            this,
                            data.getStringExtra(Constantes.KEY_TITULO),
                            data.getStringExtra(Constantes.KEY_DESCRICAO),
                            data.getStringExtra(Constantes.KEY_DATA),
                            PrioridadeEnum.BAIXA
                    );
                    break;

                case Constantes.REQUEST_CODE_UPDATE_TAREFA:
                    String old = data.getStringExtra(Constantes.KEY_OLD_TITLE);
                    String titulo = data.getStringExtra(Constantes.KEY_TITULO);
                    String descricao = data.getStringExtra(Constantes.KEY_DESCRICAO);
                    String dataLim = data.getStringExtra(Constantes.KEY_DATA);
                    PrioridadeEnum prioridadeEnum = PrioridadeEnum.valueOf(data.getStringExtra(Constantes.KEY_PRIORIDADE));
                    TarefaController.updateTarefa(old, titulo, descricao, dataLim, prioridadeEnum);
                    break;
            }
            updateAdapter();
        }
    }
}