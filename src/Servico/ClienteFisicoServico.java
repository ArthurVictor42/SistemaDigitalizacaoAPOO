package Servico;

import Entidades.ClienteFisico;
import Interfaces.IClienteFisicoRepositorio;

import java.util.ArrayList;

public class ClienteFisicoServico {
    private IClienteFisicoRepositorio clienteRepositorio;

    public ClienteFisicoServico(IClienteFisicoRepositorio empresaRepositorio) {
        this.clienteRepositorio = empresaRepositorio;
    }

    public void cadastra(ClienteFisico cliente) {
        clienteRepositorio.cadastrar(cliente);
    }

    public boolean excluir(int codigo) {
        return clienteRepositorio.remover(codigo);
    }

    public ArrayList<ClienteFisico> listar() {
        return clienteRepositorio.listarEmpresa();
    }

    public boolean alteraCliente(int codigo, String nomeCliente, String novoCPF) {
        return clienteRepositorio.alteraCliente(codigo, nomeCliente, novoCPF);
    }

    public ClienteFisico buscarPorCodigo(int codigo){
        return clienteRepositorio.buscarPorCodigo(codigo);
    } 
}
