package Servico;

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

    public void excluir(Documento documento) {
        documentoRepositorio.remover(documento);
    }

    public void listar() {
        documentoRepositorio.listarDocumento().forEach(System.out::println);
    }

    public boolean alteraDescricao(int idDocumento, String novaDescricao) {
        return documentoRepositorio.alteraDescricao(idDocumento, novaDescricao);
    }
}
