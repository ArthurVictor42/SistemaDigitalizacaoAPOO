package Repositorio;

import Entidades.Empresa;
import Interfaces.IEmpresaRepositorio;

import java.util.ArrayList;

public class EmpresaRepositorio implements IEmpresaRepositorio {
    private ArrayList<Empresa> listaEmpresa;

    public EmpresaRepositorio(ArrayList<Empresa> empresas) {
        this.listaEmpresa = empresas;
    }

    public void cadastrar(Empresa empresa) {
        listaEmpresa.add(empresa);
    }

    public boolean remover(Empresa empresa) {
        return listaEmpresa.remove(empresa);
    }

    public ArrayList<Empresa> listarEmpresa() {
        return listaEmpresa;
    }

    public boolean alteraCNPJ(String nomeEmpresa, String novoCNPJ) {
        for (Empresa empresa : listaEmpresa) {
            if (empresa.getNomeEmpresa().equals(nomeEmpresa)) {
                empresa.setCNPJ(novoCNPJ);
                return true;
            }
        }
        return false;
    }
}
