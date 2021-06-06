package br.edu.iancl.listadecontatos_dmo.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.listadecontatos_dmo.dao.ContatoDAO;
import br.edu.iancl.listadecontatos_dmo.model.Contato;

public class ContatoController {

    public static List<Contato> allContatos(){
        return ContatoDAO.getInstance().getContatos();
    }

    public static void addContato(String nome, String apelido, String telefone, String email){
        Contato novo = new Contato(nome, apelido);

        if(!(telefone.equals(""))){
            String[] telefones = telefone.trim().split(",");
            List<String> telefoneList = new ArrayList<>();
            for(String t: telefones){
                telefoneList.add(t);
            }
            novo.setTelefone(telefoneList);
        }
        if(!(email.equals(""))){
            String[] emails = email.trim().split(",");
            List<String> emailList = new ArrayList<>();
            for(String e: emails){
                emailList.add(e);
            }
            novo.setEmail(emailList);
        }

        ContatoDAO.getInstance().addContato(novo);
    }
}
