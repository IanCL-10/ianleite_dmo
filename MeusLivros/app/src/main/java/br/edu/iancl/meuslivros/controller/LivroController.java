package br.edu.iancl.meuslivros.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;

import java.util.List;

import br.edu.iancl.meuslivros.adapter.ItemLivrosAdapter;
import br.edu.iancl.meuslivros.constants.Constants;
import br.edu.iancl.meuslivros.dao.LivroDao;
import br.edu.iancl.meuslivros.model.Livro;
import br.edu.iancl.meuslivros.view.DetalhesActivity;
import br.edu.iancl.meuslivros.view.RecyclerItemClickListener;

public class LivroController {

    public static ItemLivrosAdapter getLivrosAdapter(Context context, RecyclerItemClickListener listener){
        List<Livro> livros;
        LivroDao dao = new LivroDao(context);
        livros = dao.recuperate();
        return new ItemLivrosAdapter(livros, listener);
    }

    public static void updateAdapterDataSource(Context context, ItemLivrosAdapter adapter){
        List<Livro> livros;
        LivroDao dao = new LivroDao(context);
        livros = dao.recuperate();
        adapter.setDataSource(livros);
    }

    public static boolean salvarLivro(Context context, String title, String author){
        LivroDao dao = new LivroDao(context);
        return dao.insert(new Livro(title, author));
    }

    public static void mostrarDetalhes(Context context, ItemLivrosAdapter adapter, int position){
        Livro livro = adapter.getDataSource().get(position);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_TITLE, livro.getTitulo());
        bundle.putString(Constants.KEY_AUTHOR, livro.getAutor());
        Intent intent = new Intent(context, DetalhesActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
