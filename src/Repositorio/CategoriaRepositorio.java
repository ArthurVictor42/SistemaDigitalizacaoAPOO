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

    public boolean remover(int id) {
       for(Categoria categorias: listaCategoria){
            if(categorias.getId() == id){
                listaCategoria.remove(categorias);
                return true;
            }
       }

       return false;
    }

    public ArrayList<Categoria> listarCategoria() {
        return listaCategoria;
    }

    public boolean alteraCategoria(int id, String novoNome) {
        for (Categoria categoria : listaCategoria) {
            if (categoria.getId() == id) {
                categoria.setNomeCategoria(novoNome);
                return true;
            }
        }
        return false;
    }

    public Categoria buscarPorId(int id){
        for(Categoria categorias : listaCategoria){
            if(categorias.getId() == id){
                return categorias;
            }
        }

        return null;
    }
}
