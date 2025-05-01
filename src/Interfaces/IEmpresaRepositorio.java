package Interfaces;

import java.util.ArrayList;
import Entidades.Empresa;

public interface IEmpresaRepositorio {
    void cadastrar(Empresa empresa);

    boolean remover(Empresa empresa);

    ArrayList<Empresa> listarEmpresa();

    boolean alteraCNPJ(String nomeEmpresa, String novoCNPJ);
}
