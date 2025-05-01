package Interfaces;

import java.util.ArrayList;
import Entidades.Fornecedor;

public interface IFornecedorRepositorio {
    void cadastrar(Fornecedor fornecedor);

    boolean remover(Fornecedor fornecedor);

    ArrayList<Fornecedor> listarFornecedor();

    boolean alteraCNPJ(String nomeFornecedor, String novoCNPJ);
}
