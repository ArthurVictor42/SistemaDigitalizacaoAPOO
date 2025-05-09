package Servico;

import java.util.ArrayList;

import Entidades.Documento;
import Interfaces.IDocumentoRepositorio;

public class DocumentoServico {
    private IDocumentoRepositorio documentoRepositorio;

    public DocumentoServico(IDocumentoRepositorio documentoRepositorio) {
        this.documentoRepositorio = documentoRepositorio;
    }

    public void cadastra(Documento documento) {
        documentoRepositorio.cadastrar(documento);
    }

    public boolean excluir(int id) {
       return documentoRepositorio.remover(id);
    }

    public ArrayList<Documento> listar() {
       return documentoRepositorio.listarDocumento();
    }

    public boolean alteraDescricao(int idDocumento, String novaDescricao, String novoNome) {
        return documentoRepositorio.alterarDocumento(idDocumento, novoNome,novaDescricao);
    }

    public Documento buscarPorId(int id){
        return documentoRepositorio.buscarPorId(id);
    }
}
