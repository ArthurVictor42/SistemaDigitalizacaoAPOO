package Interfaces;

import java.util.ArrayList;
import Entidades.Fornecedor;

public interface IFornecedorRepositorio {
    void cadastrar(Fornecedor fornecedor);

    boolean remover(String CNPJ);

    ArrayList<Fornecedor> listarFornecedor();

    boolean alteraFornecedor(Fornecedor fornecedor);

    Fornecedor buscarPorCNPJ(String CNPJ);
}
