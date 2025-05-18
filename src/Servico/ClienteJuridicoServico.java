package Servico;

import Interfaces.IClienteJuridicoRepositorio;

import java.util.ArrayList;

import Entidades.ClienteJuridico;

public class ClienteJuridicoServico {
    private IClienteJuridicoRepositorio clienteRepositorio;

    public ClienteJuridicoServico(IClienteJuridicoRepositorio ClienteRepositorio) {
        this.clienteRepositorio = ClienteRepositorio;
    }

    public void cadastra(ClienteJuridico cliente) {
        clienteRepositorio.cadastrar(cliente);
    }

    public boolean excluir(int codigo) {
        return clienteRepositorio.remover(codigo);
    }

    public ArrayList<ClienteJuridico> listar() {
        return clienteRepositorio.listarEmpresa();
    }

    public boolean alteraCliente(ClienteJuridico cliente) {
        return clienteRepositorio.alteraCliente(cliente);
    }

    public ClienteJuridico buscarPorCodigo(int codigo){
        return clienteRepositorio.buscarPorCodigo(codigo);
    } 
}