package Repositorio;

import Entidades.Documento;
import Interfaces.IDocumentoRepositorio;

import java.util.ArrayList;

public class DocumentoRepositorio implements IDocumentoRepositorio {
    private ArrayList<Documento> listaDocumento;

    public DocumentoRepositorio(ArrayList<Documento> documentos) {
        this.listaDocumento = documentos;
    }

    public void cadastrar(Documento documento) {
        listaDocumento.add(documento);
    }

    public boolean remover(Documento documento) {
        return listaDocumento.remove(documento);
    }

    public ArrayList<Documento> listarDocumento() {
        return listaDocumento;
    }

    public boolean alteraDescricao(int id, String novaDescricao) {
        for (Documento documento : listaDocumento) {
            if (documento.getIdDocumento() == id) {
                documento.setDescricaoDocumento(novaDescricao);
                return true;
            }
        }
        return false;
    }
}