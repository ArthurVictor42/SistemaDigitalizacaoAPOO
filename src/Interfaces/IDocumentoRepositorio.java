package Interfaces;

import java.util.ArrayList;
import Entidades.Documento;

public interface IDocumentoRepositorio {
    void cadastrar(Documento documento);

    boolean remover(int id);

    ArrayList<Documento> listarDocumento();

    boolean alterarDocumento(Documento documento);

    Documento buscarPorId(int id);
}