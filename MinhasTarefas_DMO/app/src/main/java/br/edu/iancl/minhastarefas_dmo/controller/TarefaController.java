package br.edu.iancl.minhastarefas_dmo.controller;

import android.content.Context;

import java.util.List;

import br.edu.iancl.minhastarefas_dmo.dao.TarefasDao;
import br.edu.iancl.minhastarefas_dmo.model.PrioridadeEnum;
import br.edu.iancl.minhastarefas_dmo.model.Tarefa;

public class TarefaController {

    public static List<Tarefa> allTarefas(){
        return TarefasDao.getInstance().getTarefas();
    }

    public static void addTarefa(Context context, String titulo, String descricao, String dataLimite, PrioridadeEnum prioridade){
        Tarefa novo = new Tarefa(titulo, descricao, dataLimite, prioridade);

        TarefasDao.getInstance().addTarefa(novo);
    }

    public static void updateTarefa(String oldtitle,String titulo, String descricao, String data, PrioridadeEnum prioridadeEnum){
        TarefasDao.getInstance().updateTarefa(oldtitle, titulo, descricao, data, prioridadeEnum);
    }
}
