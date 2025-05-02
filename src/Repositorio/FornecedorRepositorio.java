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

    public boolean remover(String CNPJ) {
        for(Fornecedor fornecedores: listaFornecedores){
            if(fornecedores.getCNPJ().equals(CNPJ)){
                listaFornecedores.remove(fornecedores);
                return true;
            }
        }
        
        return false;
    }

    public ArrayList<Fornecedor> listarFornecedor() {
        return listaFornecedores;
    }

    public boolean alteraFornecedor(String CNPJ, String nomeFornecedor, String enderecoFornecedor) {
        for (Fornecedor fornecedor : listaFornecedores) {
            if (fornecedor.getClass().equals(CNPJ)) {
                fornecedor.setNomeFornecedor(nomeFornecedor);
                fornecedor.setEnderecoFornecedor(enderecoFornecedor);
                return true;
            }
        }
        return false;
    }

    public Fornecedor buscarPorCNPJ(String CNPJ){
        for(Fornecedor fornecedores: listaFornecedores){
            if(fornecedores.getCNPJ().equals(CNPJ)){
                return fornecedores;
            }
        }

        return null;
    }
}
