package Repositorio;

import Entidades.Documento;
import Interfaces.IDocumentoRepositorio;

import java.util.ArrayList;

public class DocumentoRepositorio implements IDocumentoRepositorio {
    private ArrayList<Documento> listadocumento;

    public DocumentoRepositorio(ArrayList<Documento> documentos){
        this.listadocumento = documentos;
    }

    public void Cadastrar(Documento documento){
        listadocumento.add(documento);
    }

    public boolean Remover(Documento documento){
        return listadocumento.remove(documento);
    }

    public ArrayList<Documento> ListarDocumento(){
        return listadocumento;
    }

    public void AlteraDescricao(Documento documento){
        for(Documento documentos: listadocumento){
            if(documentos.getDescricaoDocumento().equals(documento.getDescricaoDocumento())){
                documentos.setDescricaoDocumento(documento.getDescricaoDocumento());
                break;
            }
        }
    }


}