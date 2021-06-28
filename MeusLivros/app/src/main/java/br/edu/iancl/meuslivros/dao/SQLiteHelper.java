package br.edu.iancl.meuslivros.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    //Configurações gerais do banco de dados
    public static final String DATABASE_NAME = "livros.db";
    public static final int DATABASE_VERSION = 1;

    //Tabelas e colunas do banco de dados
    public static final String TABLE_NAME_BOOK = "livros";
    public static final String ATTR_TITLE = "titulo";
    public static final String ATTR_AUTHOR = "autor";

    public SQLiteHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Definição do sql que cria a tabela
        String sql = "CREATE TABLE " + TABLE_NAME_BOOK + "(";
        sql += ATTR_TITLE + " TEXT NOT NULL, ";
        sql += ATTR_AUTHOR + " TEXT NOT NULL)";

        //Executa o comando
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
