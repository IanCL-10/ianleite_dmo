package br.edu.iancl.meuslivros.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.edu.iancl.meuslivros.R;
import br.edu.iancl.meuslivros.adapter.ItemLivrosAdapter;
import br.edu.iancl.meuslivros.controller.LivroController;

//errei o nome do commit do git
public class MainActivity extends AppCompatActivity {

    private ItemLivrosAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private FloatingActionButton mAddActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.list_livros);
        mAddActionButton = findViewById(R.id.fab_add_book);

        mAddActionButton.setOnClickListener(v -> addLivro());

        mAdapter = LivroController.getLivrosAdapter(this, position -> openDetails(position));

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LivroController.updateAdapterDataSource(this, mAdapter);
    }

    private void openDetails(int position){
        LivroController.mostrarDetalhes(this, mAdapter, position);
    }

    private void addLivro(){
        Intent intent = new Intent(this, DetalhesActivity.class);
        startActivity(intent);
    }
}