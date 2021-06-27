package br.edu.iancl.minhastarefas_dmo.model;

public class Tarefa implements Comparable<Tarefa>{

    private String titulo;
    private String descricao;
    private String dataLimite;
    private PrioridadeEnum prioridade;

    public Tarefa(String titulo, String descricao, String dataLimite, PrioridadeEnum prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.prioridade = prioridade;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDataLimite() {
        return dataLimite;
    }

    public PrioridadeEnum getPrioridade() {
        return prioridade;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataLimite(String dataLimite) {
        this.dataLimite = dataLimite;
    }

    public void setPrioridade(PrioridadeEnum prioridade) {
        this.prioridade = prioridade;
    }


    @Override
    public int compareTo(Tarefa tarefa) {
        return this.getTitulo().compareTo(tarefa.getTitulo());
    }
}
