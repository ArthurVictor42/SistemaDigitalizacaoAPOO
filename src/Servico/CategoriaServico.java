package Servico;

import java.util.ArrayList;

import Entidades.Categoria;
import Interfaces.ICategoriaRepositorio;

public class CategoriaServico {
    private ICategoriaRepositorio categoriaRepositorio;

    public CategoriaServico(ICategoriaRepositorio categoriaRepositorio) {
        this.categoriaRepositorio = categoriaRepositorio;
    }

    public void cadastra(Categoria categoria) {
        categoriaRepositorio.cadastrar(categoria);
    }

    public boolean excluir(int id) {
        return categoriaRepositorio.remover(id);
    }

    public ArrayList<Categoria> listar() {
       return categoriaRepositorio.listarCategoria();
    }

    public boolean alteraCategoria(int id, String novoNome) {
        return categoriaRepositorio.alteraCategoria(id, novoNome);
    }

    public Categoria buscarPorId(int id){
        return categoriaRepositorio.buscarPorId(id);
    }
}