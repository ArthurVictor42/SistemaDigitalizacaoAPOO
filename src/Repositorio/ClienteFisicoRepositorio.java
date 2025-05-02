package Repositorio;

import Entidades.ClienteFisico;
import Interfaces.IClienteFisicoRepositorio;

import java.util.ArrayList;

public class ClienteFisicoRepositorio implements IClienteFisicoRepositorio {
    private ArrayList<ClienteFisico> listaClientes;

    public ClienteFisicoRepositorio(ArrayList<ClienteFisico> clientes) {
        this.listaClientes = clientes;
    }

    public void cadastrar(ClienteFisico cliente) {
        listaClientes.add(cliente);
    }

    public boolean remover(int codigo) {
        for (ClienteFisico cliente : listaClientes) {
            if (cliente.getCodigoCliente() == codigo) {
                listaClientes.remove(cliente);
                return true; // Remoção bem-sucedida
            }
        }
        return false; // Cliente não encontrado
    }

    public ArrayList<ClienteFisico> listarEmpresa() {
        return listaClientes;
    }

    public boolean alteraCliente(int codigo, String nomeCliente, String novoCPF) {
        for (ClienteFisico cliente : listaClientes) {
            if (cliente.getCodigoCliente() == codigo) {
                cliente.setNomeCliente(nomeCliente);
                cliente.setCPF(novoCPF);
                return true;
            }
        }
        return false;
    }

    public ClienteFisico buscarPorCodigo(int codigo) {
        for (ClienteFisico cliente : listaClientes) {
            if (cliente.getCodigoCliente() == codigo) {
                return cliente;
            }
        }
        return null; // Retorna null se não encontrar
    }
}
