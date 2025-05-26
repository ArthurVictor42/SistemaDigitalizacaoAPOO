package Servico;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import Entidades.Documento;
import Interfaces.IDocumentoRepositorio;

public class DocumentoServico {
    private IDocumentoRepositorio documentoRepositorio;

    public DocumentoServico(IDocumentoRepositorio documentoRepositorio) {
        this.documentoRepositorio = documentoRepositorio;
    }

    public void cadastra(Documento documento) {
        try {
            Path caminho = Paths.get(documento.getCaminhoArquivo());
            byte[] arquivoBytes = Files.readAllBytes(caminho); // Lê o arquivo em bytes
            documento.setArquivos(arquivoBytes);

            documentoRepositorio.cadastrar(documento);

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    public boolean excluir(int id) {
        Documento doc = documentoRepositorio.buscarPorId(id);

        if (doc != null && doc.getCaminhoArquivo() != null) {
            Path caminhoArquivo = Paths.get(doc.getCaminhoArquivo());
            try {
                Files.deleteIfExists(caminhoArquivo); // deleta o arquivo físico
            } catch (IOException e) {
                System.out.println("Erro ao excluir o arquivo do sistema: " + e.getMessage());
            }
        }

        return documentoRepositorio.remover(id); // remove da lista
    }

    public ArrayList<Documento> listar() {
        return documentoRepositorio.listarDocumento();
    }

    public boolean alteraDocumento(Documento documento) {
        Documento existente = documentoRepositorio.buscarPorId(documento.getIdDocumento());

        if (existente != null) {
            documento.setArquivos(existente.getArquivos());
            return documentoRepositorio.alterarDocumento(documento);
        } else {
            System.out.println("Documento com ID " + documento.getIdDocumento() + " não encontrado.");
            return false;
        }
    }

    public Documento buscarPorId(int id) {
        return documentoRepositorio.buscarPorId(id);
    }

    // Simula o download do arquivo
    public boolean baixarDocumento(int id, String caminhoDestino) {
        Documento documento = documentoRepositorio.buscarPorId(id);

        if (documento != null && documento.getArquivos() != null) {
            try {
                Path destino = Paths.get(caminhoDestino);
                Files.write(destino, documento.getArquivos());

                System.out.println("Documento baixado com sucesso para " + destino.toString());
                return true;
            } catch (IOException e) {
                System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Documento não encontrado ou sem arquivo.");
            return false;
        }
    }
}
