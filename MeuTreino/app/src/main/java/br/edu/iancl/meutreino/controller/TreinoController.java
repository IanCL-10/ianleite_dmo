package br.edu.iancl.meutreino.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import br.edu.iancl.meutreino.adapter.ItemTreinosAdapter;
import br.edu.iancl.meutreino.dao.TreinoContract;
import br.edu.iancl.meutreino.dao.TreinoDao;
import br.edu.iancl.meutreino.model.Treino;
import br.edu.iancl.meutreino.view.RecyclerItemClickListener;
import br.edu.iancl.meutreino.view.TreinoActivity;

public class TreinoController {

    public static ItemTreinosAdapter getTreinosAdapter(Context context, RecyclerItemClickListener listener){
        List<Treino> treinos;
        TreinoDao dao = new TreinoDao(context);
        treinos = dao.recuperate();
        return new ItemTreinosAdapter(treinos, listener);
    }

    public static void updateAdapterDataSource(Context context, ItemTreinosAdapter adapter){
        List<Treino> treinos;
        TreinoDao dao = new TreinoDao(context);
        treinos = dao.recuperate();
        adapter.setDataSource(treinos);
    }

    public static boolean salvarTreino(Context context, String title, String img, String author){
        TreinoDao dao = new TreinoDao(context);
        return dao.insert(new Treino(title, img, author));
    }

    public static void mostrarDetalhes(Context context, ItemTreinosAdapter adapter, int position){
        Treino treino = adapter.getDataSource().get(position);
        Bundle bundle = new Bundle();
        bundle.putString(TreinoContract.TreinoEntry.COLUMN_NOME, treino.getNome());
        bundle.putString(TreinoContract.TreinoEntry.COLUMN_IMG, treino.getImg());
        bundle.putString(TreinoContract.TreinoEntry.COLUMN_INSTRUCOES, treino.getInstrucoes());
        bundle.putInt(TreinoContract.TreinoEntry._ID, treino.getId());
        Intent intent = new Intent(context, TreinoActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
