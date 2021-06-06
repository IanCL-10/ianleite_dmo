package br.edu.iancl.listadecontatos_dmo.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.listadecontatos_dmo.model.Contato;

public class ContatoDAO {

    private static ContatoDAO instance = null;
    private List<Contato> contatoList;

    private ContatoDAO(){
        contatoList = new ArrayList<>();
        contatoList.add(new Contato("Ian CL ", "Ian"/*, List.of("987654321", "123456789"), List.of("ta@dificil.com", "bora@bora.com")*/));
        contatoList.add(new Contato("Lucas BR", "Lurgas"));
        contatoList.add(new Contato("Cleber BB", "Mutante"));
        contatoList.add(new Contato("Marcos CB", "Bola"));
        contatoList.add(new Contato("João TM", "Trevisan"));
        contatoList.add(new Contato("Igor CA", "Igão"));
        contatoList.add(new Contato("Marrinus F", "Mano"));
        contatoList.add(new Contato("João VG", "Jão"));
        contatoList.add(new Contato("Leonardo TM", "Lele"));
    }

    public static synchronized ContatoDAO getInstance(){
        if(instance == null){
            instance = new ContatoDAO();
        }
        return instance;
    }

    public List<Contato> getContatos(){
        return contatoList;
    }

    public void addContato(Contato contato){
        if(contato != null){
            contatoList.add(contato);
        }
    }

    public Contato find(int i){
        return contatoList.get(i);
    }

    public Contato find(String apelido){
        for (Contato c: contatoList){
            if (c.getApelido().equals(apelido)){
                return c;
            }
        }
        return null;
    }
}
