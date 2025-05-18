package Interfaces;

import java.util.ArrayList;
import Entidades.Usuario;

public interface IUsuarioRepositorio {
    void cadastrar(Usuario usuario);

    boolean remover(int codigo);

    ArrayList<Usuario> listarUsuario();

    boolean alteraUsuario(Usuario usuario);

    Usuario buscarPorId(int id);
}
