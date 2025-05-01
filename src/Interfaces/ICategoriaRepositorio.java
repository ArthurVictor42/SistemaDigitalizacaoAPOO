package Interfaces;

import java.util.ArrayList;
import Entidades.Categoria;

public interface ICategoriaRepositorio {
    void cadastrar(Categoria categoria);

    boolean remover(Categoria categoria);

    ArrayList<Categoria> listarCategoria();

    boolean alteraNome(int id, String novoNome);
}