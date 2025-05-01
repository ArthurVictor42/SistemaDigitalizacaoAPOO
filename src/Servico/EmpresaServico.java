package Servico;

import Entidades.Empresa;
import Interfaces.IEmpresaRepositorio;

public class EmpresaServico {
    private IEmpresaRepositorio empresaRepositorio;

    public EmpresaServico(IEmpresaRepositorio empresaRepositorio) {
        this.empresaRepositorio = empresaRepositorio;
    }

    public void cadastra(Empresa empresa) {
        empresaRepositorio.cadastrar(empresa);
    }

    public void excluir(Empresa empresa) {
        empresaRepositorio.remover(empresa);
    }

    public void listar() {
        empresaRepositorio.listarEmpresa().forEach(System.out::println);
    }

    public boolean alteraCNPJ(String nomeEmpresa, String novoCNPJ) {
        return empresaRepositorio.alteraCNPJ(nomeEmpresa, novoCNPJ);
    }
}
