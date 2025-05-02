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

    public boolean remover(int codigo) {
        for(Usuario usuarios: listaUsuario){
            if(usuarios.getId() == codigo){
                listaUsuario.remove(usuarios);
                return true;
            }
        }

        return false;
    }

    public ArrayList<Usuario> listarUsuario() {
        return listaUsuario;
    }

    public boolean alteraUsuario(int id, String nome, String email, String novaSenha, String cpf) {
        for (Usuario usuario : listaUsuario) {
            if (usuario.getId() == id) {
                usuario.setNomeUsuario(nome);
                usuario.setEmailUsuario(email);
                usuario.setSenhaUsuario(novaSenha);
                usuario.setCpf(cpf);
                return true;
            }
        }
        return false;
    }

    public Usuario buscarPorId(int id) {
        for(Usuario usuarios: listaUsuario){
            if(usuarios.getId() == id){
                return usuarios;
            }
        }

        return null;
    }
}
