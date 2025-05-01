package Servico;

import Entidades.Fornecedor;
import Interfaces.IFornecedorRepositorio;

public class FornecedorServico {
    private IFornecedorRepositorio fornecedorRepositorio;

    public FornecedorServico(IFornecedorRepositorio fornecedorRepositorio) {
        this.fornecedorRepositorio = fornecedorRepositorio;
    }

    public void cadastra(Fornecedor fornecedor) {
        fornecedorRepositorio.cadastrar(fornecedor);
    }

    public void excluir(Fornecedor fornecedor) {
        fornecedorRepositorio.remover(fornecedor);
    }

    public void listar() {
        fornecedorRepositorio.listarFornecedor().forEach(System.out::println);
    }

    public boolean alteraCNPJ(String nomeFornecedor, String novoCNPJ) {
        return fornecedorRepositorio.alteraCNPJ(nomeFornecedor, novoCNPJ);
    }
}
