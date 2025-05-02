package Interfaces;

import java.util.ArrayList;
import Entidades.Categoria;

public interface ICategoriaRepositorio {
    void cadastrar(Categoria categoria);

    boolean remover(int id);

    ArrayList<Categoria> listarCategoria();

    boolean alteraCategoria(int id, String novoNome);

    Categoria buscarPorId(int id);

}