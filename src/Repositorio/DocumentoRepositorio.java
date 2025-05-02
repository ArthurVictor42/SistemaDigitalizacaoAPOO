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

    public boolean remover(int id) {
        for (Documento documentos : listaDocumento) {
            if(documentos.getIdDocumento() == id){
                listaDocumento.remove(documentos);
                return true;
            }
        }

        return false;
    }

    public ArrayList<Documento> listarDocumento() {
        return listaDocumento;
    }

    public boolean alterarDocumento(int id, String novoNome, String novaDescricao) {
        for (Documento documento : listaDocumento) {
            if (documento.getIdDocumento() == id) {
                documento.setNomeDocumento(novoNome);
                documento.setDescricaoDocumento(novaDescricao);
                return true;
            }
        }
        return false;
    }

    public Documento buscarPorId(int id) {
        for(Documento documentos: listaDocumento){
            if(documentos.getIdDocumento() == id){
                return documentos;
            }
        }

        return null;
    }
}