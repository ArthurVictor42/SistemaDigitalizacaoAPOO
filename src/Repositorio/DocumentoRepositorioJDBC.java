package Repositorio;

import Entidades.Categoria;
import Entidades.ClienteFisico;
import Entidades.ClienteJuridico;
import Entidades.Documento;
import Entidades.Fornecedor;
import Interfaces.IDocumentoRepositorio;
import Servico.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import Conexao.conexaoBD;

public class DocumentoRepositorioJDBC implements IDocumentoRepositorio {
    public static CategoriaRepositorioJDBC CateRepo = new CategoriaRepositorioJDBC();
    public static CategoriaServico CateServi = new CategoriaServico(CateRepo);

    public static ClienteFisicoRepositorioJDBC ClienteFisiRepo = new ClienteFisicoRepositorioJDBC();
    public static ClienteFisicoServico ClienteFisiServi = new ClienteFisicoServico(ClienteFisiRepo);

    public static FornecedorRepositorioJDBC FornRepo = new FornecedorRepositorioJDBC();
    public static FornecedorServico FornServi = new FornecedorServico(FornRepo);

    public static ClienteJuridicoRepositorioJDBC ClienteJuriRepo = new ClienteJuridicoRepositorioJDBC();
    public static ClienteJuridicoServico ClienteJuriServi = new ClienteJuridicoServico(ClienteJuriRepo);

    public void cadastrar(Documento documento) {
        String sql = "INSERT INTO documento (cod_documento, nome_documento, descricao, cod_cliente, cod_clienteJ, id_cate, cnpj, arquivo, caminho_arquivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, documento.getIdDocumento());
            stmt.setString(2, documento.getNomeDocumento());
            stmt.setString(3, documento.getDescricaoDocumento());

            if (documento.getClientefisico() != null) {
                stmt.setInt(4, documento.getClientefisico().getCodigoCliente());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            if (documento.getClientejuridico() != null) {
                stmt.setInt(5, documento.getClientejuridico().getCodigoCliente());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }

            stmt.setInt(6, documento.getCategoria().getId());

            if (documento.getFornecedor() != null) {
                stmt.setString(7, documento.getFornecedor().getCNPJ());
            } else {
                stmt.setNull(7, java.sql.Types.VARCHAR);
            }

            stmt.setBytes(8, documento.getArquivos());

            stmt.setString(9, documento.getCaminhoArquivo());

            stmt.executeUpdate();

            System.out.println("Documento cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar documento: " + e.getMessage());
        }
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM documento WHERE cod_documento =?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover documento: " + e.getMessage());
        }

        return false;
    }

    public ArrayList<Documento> listarDocumento() {
        ArrayList<Documento> lista = new ArrayList<>();

        String sql = "SELECT * FROM documento";

        try (Connection conn = conexaoBD.conexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int codDocumento = rs.getInt("cod_documento");
                String nome = rs.getString("nome_documento");
                String descricao = rs.getString("descricao");
                int idCategoria = rs.getInt("id_cate");
                int codClienteFisico = rs.getInt("cod_cliente");
                int codClienteJuridico = rs.getInt("cod_clienteJ");
                String cnpjFornecedor = rs.getString("cnpj");

                Categoria categoria = CateServi.buscarPorId(idCategoria);
                ClienteFisico clienteFisico = ClienteFisiServi.buscarPorCodigo(codClienteFisico);
                ClienteJuridico clienteJuridico = ClienteJuriServi.buscarPorCodigo(codClienteJuridico);
                Fornecedor fornecedor = FornServi.buscarPorCNPJ(cnpjFornecedor);

                Documento documento = new Documento(codDocumento,
                        nome,
                        descricao,
                        clienteFisico,
                        clienteJuridico,
                        categoria,
                        fornecedor);
                lista.add(documento);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar documento: " + e.getMessage());
        }

        return lista;
    }

    public boolean alterarDocumento(Documento documento) {
        String sql = "UPDATE documento SET nome_documento =?, descricao =?, cod_cliente =?, cod_clienteJ =?, id_cate =?, cnpj =? WHERE cod_documento =?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, documento.getNomeDocumento());
            stmt.setString(2, documento.getDescricaoDocumento());
            if (documento.getClientefisico() != null) {
                stmt.setInt(3, documento.getClientefisico().getCodigoCliente());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }

            if (documento.getClientejuridico() != null) {
                stmt.setInt(4, documento.getClientejuridico().getCodigoCliente());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            stmt.setInt(5, documento.getCategoria().getId());

            if (documento.getFornecedor() != null) {
                stmt.setString(6, documento.getFornecedor().getCNPJ());
            } else {
                stmt.setNull(6, java.sql.Types.VARCHAR);
            }
            stmt.setInt(7, documento.getIdDocumento());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Documento: " + e.getMessage());
        }

        return false;
    }

    public Documento buscarPorId(int id) {
        String sql = "SELECT * FROM documento WHERE cod_documento = ?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int codDocumento = rs.getInt("cod_documento");
                String nome = rs.getString("nome_documento");
                String descricao = rs.getString("descricao");

                int idCategoria = rs.getInt("id_cate");
                int codClienteFisico = rs.getInt("cod_cliente");
                int codClienteJuridico = rs.getInt("cod_clienteJ");
                String cnpjFornecedor = rs.getString("cnpj");

                byte[] arquivoBytes = rs.getBytes("arquivo"); // Recupera o arquivo como blob

                String caminho = rs.getString("caminho_arquivo");

                Categoria categoria = CateServi.buscarPorId(idCategoria);
                ClienteFisico clienteFisico = ClienteFisiServi.buscarPorCodigo(codClienteFisico);
                ClienteJuridico clienteJuridico = ClienteJuriServi.buscarPorCodigo(codClienteJuridico);
                Fornecedor fornecedor = FornServi.buscarPorCNPJ(cnpjFornecedor);

                Documento documento = new Documento(
                        codDocumento,
                        nome,
                        descricao,
                        clienteFisico,
                        clienteJuridico,
                        categoria,
                        fornecedor);

                documento.setArquivos(arquivoBytes);
                documento.setCaminhoArquivo(caminho);

                return documento;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar documento: " + e.getMessage());
        }

        return null;
    }
}