package Repositorio;

import Entidades.Fornecedor;
import Interfaces.IFornecedorRepositorio;

import java.util.ArrayList;

public class FornecedorRepositorio implements IFornecedorRepositorio {
    private ArrayList<Fornecedor> listaFornecedores;

    public FornecedorRepositorio(ArrayList<Fornecedor> fornecedores) {
        this.listaFornecedores = fornecedores;
    }

    public void cadastrar(Fornecedor fornecedor) {
        listaFornecedores.add(fornecedor);
    }

    public boolean remover(Fornecedor fornecedor) {
        return listaFornecedores.remove(fornecedor);
    }

    public ArrayList<Fornecedor> listarFornecedor() {
        return listaFornecedores;
    }

    public boolean alteraCNPJ(String nomeFornecedor, String novoCNPJ) {
        for (Fornecedor fornecedor : listaFornecedores) {
            if (fornecedor.getNomeFornecedor().equals(nomeFornecedor)) {
                fornecedor.setCNPJ(novoCNPJ);
                return true;
            }
        }
        return false;
    }
}
