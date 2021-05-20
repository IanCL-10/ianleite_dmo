package br.edu.iancl.loginhardcore.controller;

import br.edu.iancl.loginhardcore.constantes.Constantes;
import br.edu.iancl.loginhardcore.data.UsuarioDAO;
import br.edu.iancl.loginhardcore.model.Usuario;

public class LoginController {

    public static boolean checkLogin(String username, int passwd){
        UsuarioDAO usuarioDAO;
        Usuario usuario;
        usuarioDAO = new UsuarioDAO();
        usuario = usuarioDAO.recuperate(username);
        if(usuario != null){
            return usuario.validate(username, passwd);
        }
        return false;
    }
}
