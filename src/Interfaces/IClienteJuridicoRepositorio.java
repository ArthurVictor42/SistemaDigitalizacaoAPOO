package Interfaces;

import java.util.ArrayList;

import Entidades.ClienteJuridico;

public interface IClienteJuridicoRepositorio {
    void cadastrar(ClienteJuridico cliente);

    boolean remover(int codigo);

    ArrayList<ClienteJuridico> listarEmpresa();

    boolean alteraCliente(ClienteJuridico cliente);

    ClienteJuridico buscarPorCodigo(int codigo);
    
} 
