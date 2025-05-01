package Servico;

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

    public void excluir(Usuario usuario) {
        usuarioRepositorio.remover(usuario);
    }

    public void listar() {
        usuarioRepositorio.listarUsuario().forEach(System.out::println);
    }

    public boolean alteraSenha(String email, String novaSenha) {
        return usuarioRepositorio.alteraSenha(email, novaSenha);
    }
}
