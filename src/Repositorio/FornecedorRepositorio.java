package Repositorio;

import Entidades.Fornecedor;
import Interfaces.IFornecedorRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import Conexao.conexaoBD;

public class FornecedorRepositorio implements IFornecedorRepositorio {

    public void cadastrar(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (cnpj, nome_fornecedor, endereco_fornecedor) VALUES (?, ?, ?)";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getCNPJ());
            stmt.setString(2, fornecedor.getNomeFornecedor());
            stmt.setString(3, fornecedor.getEnderecoFornecedor());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar Fornecedor: " + e.getMessage());
        }
    }

    public boolean remover(String CNPJ) {
        String sql = "DELETE FROM fornecedor WHERE cnpj =?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, CNPJ);

            System.out.println("Fornecedor removido com sucesso!");
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remove fornecedor: " + e.getMessage());
        }

        return false;
    }

    public ArrayList<Fornecedor> listarFornecedor() {
        ArrayList<Fornecedor> lista = new ArrayList<>();

        String sql = "SELECT * FROM fornecedor";

        try (Connection conn = conexaoBD.conexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(rs.getString("cnpj"),
                        rs.getString("nome_fornecedor"),
                        rs.getString("endereco_fornecedor"));
                lista.add(fornecedor);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar fornecedores: " + e.getMessage());
        }

        return lista;
    }

    public boolean alteraFornecedor(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET nome_fornecedor =?, endereco_fornecedor =? WHERE cnpj =?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getNomeFornecedor());
            stmt.setString(2, fornecedor.getEnderecoFornecedor());
            stmt.setString(3, fornecedor.getCNPJ());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Fornecedor: " + e.getMessage());
        }

        return false;
    }

    public Fornecedor buscarPorCNPJ(String cnpj) {
        String sql = "SELECT * FROM fornecedor WHERE cnpj = ?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        rs.getString("cnpj"),
                        rs.getString("nome_fornecedor"),
                        rs.getString("endereco_fornecedor"));
                return fornecedor;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar fornecedor: " + e.getMessage());
        }

        return null;
    }
}
