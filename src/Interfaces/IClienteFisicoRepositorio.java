package Interfaces;

import java.util.ArrayList;
import Entidades.ClienteFisico;

public interface IClienteFisicoRepositorio {
    void cadastrar(ClienteFisico cliente);

    boolean remover(int codigo);

    ArrayList<ClienteFisico> listarEmpresa();

    boolean alteraCliente(ClienteFisico cliente);

    ClienteFisico buscarPorCodigo(int codigo);
}
