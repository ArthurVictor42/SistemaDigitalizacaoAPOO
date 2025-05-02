package Interfaces;

import java.util.ArrayList;

import Entidades.ClienteJuridico;

public interface IClienteJuridicoRepositorio {
    void cadastrar(ClienteJuridico cliente);

    boolean remover(int codigoe);

    ArrayList<ClienteJuridico> listarEmpresa();

    boolean alteraCliente(int id, String nomeCliente, String novoCNPJ);

    ClienteJuridico buscarPorCodigo(int codigo);
    
} 
