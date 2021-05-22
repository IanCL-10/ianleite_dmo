package br.edu.iancl.loginhardcore.data;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.loginhardcore.model.Usuario;

public class UsuarioDAO {

    private List<Usuario> usuarioList;

    public UsuarioDAO(){
        usuarioList = new ArrayList<>(10);
        usuarioList.add(new Usuario( "edinilsonrossi@ifsp.edu.br",12345));
        usuarioList.add(new Usuario( "pernalonga@ifsp.edu.br",12345));
        usuarioList.add(new Usuario( "dick_vigarista@ifsp.edu.br",12345));
        usuarioList.add(new Usuario( "bob_esponja@ifsp.edu.br",12345));
        usuarioList.add(new Usuario( "chapeuvermelho@ifsp.edu.br",12345));
        usuarioList.add(new Usuario( "tom@ifsp.edu.br",12345));
        usuarioList.add(new Usuario( "luluka@ifsp.edu.br",12345));
        usuarioList.add(new Usuario( "luna@ifsp.edu.br",12345));
        usuarioList.add(new Usuario( "sherek@ifsp.edu.br",12345));
        usuarioList.add(new Usuario( "lobomal@ifsp.edu.br",12345));
    }

    public Usuario recuperate(String username){
        for(Usuario u : usuarioList){
            if(u.getUsername().equalsIgnoreCase(username)){
                return u;
            }
        }
        return null;
    }

}
