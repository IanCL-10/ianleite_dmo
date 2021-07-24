package br.edu.iancl.meutreino.dao;

import android.provider.BaseColumns;

public class TreinoContract {

    private TreinoContract(){

    }

    public static class TreinoEntry implements BaseColumns {
        //Tabelas e colunas do banco de dados
        public static final String TABLE_NAME = "treinos";
        public static final String TABLE_NAME_OLD = "treinos_old";

        public static final String COLUMN_NOME = "nome";
        public static final String COLUMN_IMG = "img";
        public static final String COLUMN_INSTRUCOES= "instrucoes";
    }

    //Tabelas do banco de dados
    //Versão 1
    public static String createTable(){
        return "CREATE TABLE " + TreinoEntry.TABLE_NAME + " ("
                + TreinoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TreinoEntry.COLUMN_NOME + " TEXT NOT NULL, "
                + TreinoEntry.COLUMN_IMG + " TEXT NOT NULL, "
                + TreinoEntry.COLUMN_INSTRUCOES + " TEXT NOT NULL)";
    }
}
