package Interfaces;

import java.util.ArrayList;
import Entidades.Cliente;

public interface IClienteRepositorio {
    void cadastrar(Cliente empresa);

    boolean remover(Cliente empresa);

    ArrayList<Cliente> listarEmpresa();

    boolean alteraCPF(String nomeCliente, String novoCPF);
}
