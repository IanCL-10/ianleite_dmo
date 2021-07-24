package br.edu.iancl.meutreino.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.edu.iancl.meutreino.R;
import br.edu.iancl.meutreino.adapter.ItemTreinosAdapter;
import br.edu.iancl.meutreino.controller.TreinoController;

public class MainActivity extends AppCompatActivity {

    private ItemTreinosAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private FloatingActionButton mAddActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.list_excs);
        mAddActionButton = findViewById(R.id.fab_add_exc);

        mAddActionButton.setOnClickListener(v -> addTreino());

        mAdapter = TreinoController.getTreinosAdapter(this, position -> openDetails(position));

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TreinoController.updateAdapterDataSource(this, mAdapter);
    }

    private void addTreino(){
        Intent intent = new Intent(this, NovoTreinoActivity.class);
        startActivity(intent);
    }

    private void openDetails(int position){
        TreinoController.mostrarDetalhes(this, mAdapter, position);
    }
}