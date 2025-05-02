package Servico;

import Entidades.Cliente;
import Interfaces.IClienteRepositorio;

public class ClienteServico {
    private IClienteRepositorio clienteRepositorio;

    public ClienteServico(IClienteRepositorio empresaRepositorio) {
        this.clienteRepositorio = empresaRepositorio;
    }

    public void cadastra(Cliente empresa) {
        clienteRepositorio.cadastrar(empresa);
    }

    public void excluir(Cliente empresa) {
        clienteRepositorio.remover(empresa);
    }

    public void listar() {
        clienteRepositorio.listarEmpresa().forEach(System.out::println);
    }

    public boolean alteraCNPJ(String nomeCliente, String novoCPF) {
        return clienteRepositorio.alteraCPF(nomeCliente, novoCPF);
    }
}
