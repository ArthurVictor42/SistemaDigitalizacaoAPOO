package Interfaces;

import java.util.ArrayList;
import Entidades.Usuario;

public interface IUsuarioRepositorio {
    void cadastrar(Usuario usuario);

    boolean remover(Usuario usuario);

    ArrayList<Usuario> listarUsuario();

    boolean alteraSenha(String email, String novaSenha);
}
