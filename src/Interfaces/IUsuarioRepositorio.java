package Interfaces;

import java.util.ArrayList;
import Entidades.Usuario;

public interface IUsuarioRepositorio {
    void cadastrar(Usuario usuario);

    boolean remover(int codigo);

    ArrayList<Usuario> listarUsuario();

    boolean alteraUsuario(int id, String nome, String email, String novaSenha, String cpf);

    Usuario buscarPorId(int id);
}
