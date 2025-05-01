package Repositorio;

import Entidades.Usuario;
import Interfaces.IUsuarioRepositorio;

import java.util.ArrayList;

public class UsuarioRepositorio implements IUsuarioRepositorio {
    private ArrayList<Usuario> listaUsuario;

    public UsuarioRepositorio(ArrayList<Usuario> usuarios) {
        this.listaUsuario = usuarios;
    }

    public void cadastrar(Usuario usuario) {
        this.listaUsuario.add(usuario);
    }

    public boolean remover(Usuario usuario) {
        return listaUsuario.remove(usuario);
    }

    public ArrayList<Usuario> listarUsuario() {
        return listaUsuario;
    }

    public boolean alteraSenha(String email, String novaSenha) {
        for (Usuario usuario : listaUsuario) {
            if (usuario.getEmailUsuario().equals(email)) {
                usuario.setSenhaUsuario(novaSenha);
                return true;
            }
        }
        return false;
    }
}
