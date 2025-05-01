package Repositorio;

import Entidades.Categoria;
import Interfaces.ICategoriaRepositorio;

import java.util.ArrayList;

public class CategoriaRepositorio implements ICategoriaRepositorio {
    private ArrayList<Categoria> listaCategoria;

    public CategoriaRepositorio(ArrayList<Categoria> categorias) {
        this.listaCategoria = categorias;
    }

    public void cadastrar(Categoria categoria) {
        listaCategoria.add(categoria);
    }

    public boolean remover(Categoria categoria) {
        return listaCategoria.remove(categoria);
    }

    public ArrayList<Categoria> listarCategoria() {
        return listaCategoria;
    }

    public boolean alteraNome(int id, String novoNome) {
        for (Categoria categoria : listaCategoria) {
            if (categoria.getId() == id) {
                categoria.setNomeCategoria(novoNome);
                return true;
            }
        }
        return false;
    }
}
