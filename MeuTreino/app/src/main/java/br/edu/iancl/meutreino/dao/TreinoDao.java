package br.edu.iancl.meutreino.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.meutreino.model.Treino;

public class TreinoDao {

    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public TreinoDao(@Nullable Context context) {
        mHelper = new SQLiteHelper(context);
    }

    public boolean insert(Treino treino){
        long linhas;
        ContentValues values = new ContentValues();
        values.put(TreinoContract.TreinoEntry.COLUMN_NOME, treino.getNome());
        values.put(TreinoContract.TreinoEntry.COLUMN_IMG, treino.getImg());
        values.put(TreinoContract.TreinoEntry.COLUMN_INSTRUCOES, treino.getInstrucoes());

        try {
            mDatabase = mHelper.getWritableDatabase();
            linhas = mDatabase.insert(
                    TreinoContract.TreinoEntry.TABLE_NAME,
                    null,
                    values
            );
            mDatabase.close();
        }catch (Exception e){
            linhas = -1;
        }finally {
            mDatabase.close();
        }
        return ! (linhas == -1);
    }

    public List<Treino> recuperate(){
        Treino mTreino;
        List<Treino> mTreinos = new ArrayList<>();

        Cursor mCursor = null;

        String mColunas[] = new String[]{
                TreinoContract.TreinoEntry._ID,
                TreinoContract.TreinoEntry.COLUMN_NOME,
                TreinoContract.TreinoEntry.COLUMN_IMG,
                TreinoContract.TreinoEntry.COLUMN_INSTRUCOES
        };
        try {
            mDatabase = mHelper.getReadableDatabase();
            mCursor = mDatabase.query(
                    TreinoContract.TreinoEntry.TABLE_NAME,
                    mColunas,
                    null,
                    null,
                    null,
                    null,
                    TreinoContract.TreinoEntry.COLUMN_NOME
            );

            while (mCursor.moveToNext()) {
                mTreino = new Treino(
                        mCursor.getInt(0),
                        mCursor.getString(1),
                        mCursor.getString(2),
                        mCursor.getString(3)
                );
                mTreinos.add(mTreino);
            }
        }catch (Exception e){
            mTreinos = null;
        }finally {
            mCursor.close();
            mDatabase.close();
        }
        return mTreinos;
    }

//    public boolean update(Treino treino){
//        boolean deuCerto = true;
//        ContentValues values = new ContentValues();
//        values.put(TreinoContract.TreinoEntry.COLUMN_NOME, treino.getNome());
//
//        String where = TreinoContract.TreinoEntry._ID + " = ?";
//        String whereArgs[] = {String.valueOf(treino.getId())};
//
//        try{
//            mDatabase = mHelper.getWritableDatabase();
//            mDatabase.update(TreinoContract.TreinoEntry.TABLE_NAME, values, where, whereArgs);
//        }catch (Exception e){
//            deuCerto = false;
//        }finally {
//            mDatabase.close();
//        }
//        return deuCerto;
//    }
}
