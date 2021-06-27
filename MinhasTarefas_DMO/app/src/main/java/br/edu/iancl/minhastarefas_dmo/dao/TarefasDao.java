package br.edu.iancl.minhastarefas_dmo.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.minhastarefas_dmo.model.PrioridadeEnum;
import br.edu.iancl.minhastarefas_dmo.model.Tarefa;

public class TarefasDao {

    private static TarefasDao instance = null;
    private List<Tarefa> tarefasList;

    private TarefasDao(){
        tarefasList = new ArrayList<>();
        tarefasList.add(new Tarefa("Limpar Casa", "Varrer e limpar tudo", "30/06/2021", PrioridadeEnum.BAIXA));
        tarefasList.add(new Tarefa("Pagar Contas", "Procurar o banco mais proximo", "10/07/2021", PrioridadeEnum.ALTA));
        tarefasList.add(new Tarefa("Fazer compras", "Não esquecer o papel higienico", "20/06/2021", PrioridadeEnum.MEDIA));
        tarefasList.add(new Tarefa("Consertar o fogão", "Consertar as partes quebradas", "30/06/2021", PrioridadeEnum.MEDIA));
    }

    public static synchronized TarefasDao getInstance(){
        if(instance == null){
            instance = new TarefasDao();
        }
        return instance;
    }

    public List<Tarefa> getTarefas(){
        return tarefasList;
    }

    public void addTarefa(Tarefa tarefa){
        if(tarefa != null){
            tarefasList.add(tarefa);
        }
    }

    public void updateTarefa(String oldTitle, String titulo, String descricao, String data, PrioridadeEnum prioridadeEnum){
        Tarefa alterar = find(oldTitle);
        if(alterar != null){
            alterar.setTitulo(titulo);
            alterar.setDescricao(descricao);
            alterar.setDataLimite(data);
            alterar.setPrioridade(prioridadeEnum);
        }
    }

    public Tarefa find(int i){
        return tarefasList.get(i);
    }

    public Tarefa find(String title){
        for (Tarefa t: tarefasList){
            if (t.getTitulo().equals(title)){
                return t;
            }
        }
        return null;
    }
}
