package Repositorio;

import Entidades.Cliente;
import Interfaces.IClienteRepositorio;

import java.util.ArrayList;

public class ClienteRepositorio implements IClienteRepositorio {
    private ArrayList<Cliente> listaClientes;

    public ClienteRepositorio(ArrayList<Cliente> clientes) {
        this.listaClientes = clientes;
    }

    public void cadastrar(Cliente empresa) {
        listaClientes.add(empresa);
    }

    public boolean remover(Cliente empresa) {
        return listaClientes.remove(empresa);
    }

    public ArrayList<Cliente> listarEmpresa() {
        return listaClientes;
    }

    public boolean alteraCPF(String nomeCliente, String novoCPF) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNomeCliente().equals(nomeCliente)) {
                cliente.setCPF(novoCPF);
                return true;
            }
        }
        return false;
    }
}
