package Repositorio;

import Entidades.Documento;
import Interfaces.IDocumentoRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import Conexao.conexaoBD;

public class DocumentoRepositorio implements IDocumentoRepositorio {

    public void cadastrar(Documento documento) {
        String sql = "INSERT INTO documento (cod_documento, nome_documento, descricao ) VALUES (?, ?, ?)";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, documento.getIdDocumento());
            stmt.setString(2, documento.getNomeDocumento());
            stmt.setString(3, documento.getDescricaoDocumento());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar documento: " + e.getMessage());
        }
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM documento WHERE cod_documento =?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Documento removido com sucesso!");

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
                Documento documento = new Documento(rs.getInt("cod_documento"),
                        rs.getString("nome_documento"),
                        rs.getString("descricao"));
                lista.add(documento);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar documento: " + e.getMessage());
        }

        return lista;
    }

    public boolean alterarDocumento(Documento documento) {
        String sql = "UPDATE documento SET nome_documento =?, descricao =? WHERE cod_documento =?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, documento.getNomeDocumento());
            stmt.setString(2, documento.getDescricaoDocumento());
            stmt.setInt(3, documento.getIdDocumento());

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
                Documento documento = new Documento(
                        rs.getInt("cod_documento"),
                        rs.getString("nome_documento"),
                        rs.getString("descricao"));
                return documento;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar documento: " + e.getMessage());
        }

        return null;
    }
}