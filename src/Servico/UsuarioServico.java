package Servico;

import java.util.ArrayList;

import Entidades.Usuario;
import Interfaces.IUsuarioRepositorio;

public class UsuarioServico {
    private IUsuarioRepositorio usuarioRepositorio;

    public UsuarioServico(IUsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public void cadastra(Usuario usuario) {
        usuarioRepositorio.cadastrar(usuario);
    }

    public boolean excluir(int codigo) {
        return usuarioRepositorio.remover(codigo);
    }

    public ArrayList<Usuario> listar() {
        return usuarioRepositorio.listarUsuario();
    }

    public boolean alteraUsuario(int id, String nome, String email, String novaSenha, String cpf) {
        return usuarioRepositorio.alteraUsuario(id, nome, email, novaSenha, cpf);
    }

    public Usuario buscarPorId(int id){
        return usuarioRepositorio.buscarPorId(id);
    }
}
