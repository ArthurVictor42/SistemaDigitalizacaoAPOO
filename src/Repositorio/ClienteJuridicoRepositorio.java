package Repositorio;

import Entidades.ClienteJuridico;
import Interfaces.IClienteJuridicoRepositorio;

import java.util.ArrayList;

public class ClienteJuridicoRepositorio implements IClienteJuridicoRepositorio {
    private ArrayList<ClienteJuridico> listaClientes;

    public ClienteJuridicoRepositorio(ArrayList<ClienteJuridico> clientes) {
        this.listaClientes = clientes;
    }

    public void cadastrar(ClienteJuridico cliente) {
        listaClientes.add(cliente);
    }

    public boolean remover(int codigo) {
    for (ClienteJuridico cliente : listaClientes) {
        if (cliente.getCodigoCliente() == codigo) {
            listaClientes.remove(cliente);
            return true; // Remoção bem-sucedida
        }
    }
    return false; // Cliente não encontrado
}

    public ArrayList<ClienteJuridico> listarEmpresa() {
        return listaClientes;
    }

    public boolean alteraCliente(int id, String nomeCliente, String novoCNPJ) {
        for (ClienteJuridico cliente : listaClientes) {
            if (cliente.getCodigoCliente() == id) {
                cliente.setNomeCliente(nomeCliente);
                cliente.setCNPJ(novoCNPJ);
                return true;
            }
        }
        return false;
    }

    public ClienteJuridico buscarPorCodigo(int codigo) { 
    for (ClienteJuridico cliente : listaClientes) {
        if (cliente.getCodigoCliente() == codigo) {
            return cliente;
        }
    }
    return null; // Retorna null se não encontrar
}
}