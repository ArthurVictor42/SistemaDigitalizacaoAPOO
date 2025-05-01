package Interfaces;

import java.util.ArrayList;
import Entidades.Documento;

public interface IDocumentoRepositorio {
    void cadastrar(Documento documento);

    boolean remover(Documento documento);

    ArrayList<Documento> listarDocumento();

    boolean alteraDescricao(int id, String novaDescricao);
}