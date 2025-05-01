package Servico;

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

    public void excluir(Categoria categoria) {
        categoriaRepositorio.remover(categoria);
    }

    public void listar() {
        categoriaRepositorio.listarCategoria().forEach(System.out::println);
    }

    public boolean alteraNome(int id, String novoNome) {
        return categoriaRepositorio.alteraNome(id, novoNome);
    }
}