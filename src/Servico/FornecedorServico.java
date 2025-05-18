package Servico;

import java.util.ArrayList;

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

    public boolean excluir(String CNPJ) {
        return fornecedorRepositorio.remover(CNPJ);
    }

    public ArrayList<Fornecedor> listar() {
        return fornecedorRepositorio.listarFornecedor();
    }

    public boolean alteraFornecedor(Fornecedor fornecedor) {
        return fornecedorRepositorio.alteraFornecedor(fornecedor);
    }

    public Fornecedor buscarPorCNPJ(String CNPJ){
        return fornecedorRepositorio.buscarPorCNPJ(CNPJ);
    }
}
