package Repositorio;

import Entidades.ClienteFisico;
import Interfaces.IClienteFisicoRepositorio;

import java.util.ArrayList;

import Conexao.conexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteFisicoRepositorio implements IClienteFisicoRepositorio {

    public void cadastrar(ClienteFisico cliente) {
        String sql = "INSERT INTO clientefisico (cod_cliente, nome_cliente, cpf) VALUES (?, ?, ?)";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getCodigoCliente());
            stmt.setString(2, cliente.getNomeCliente());
            stmt.setString(3, cliente.getCPF());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar Cliente: " + e.getMessage());
        }
    }

    public boolean remover(int codigo) {
        String sql = "DELETE FROM clientefisico WHERE cod_cliente = ?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }

        return false;
    }

    public ArrayList<ClienteFisico> listarEmpresa() {
        ArrayList<ClienteFisico> lista = new ArrayList<>();

        String sql = "SELECT * FROM clientefisico";

        try (Connection conn = conexaoBD.conexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ClienteFisico cliente = new ClienteFisico(rs.getInt("cod_cliente"),
                        rs.getString("nome_cliente"),
                        rs.getString("cpf"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar Cliente: " + e.getMessage());
        }

        return lista;
    }

    public boolean alteraCliente(ClienteFisico cliente) {
        String sql = "UPDATE clientefisico SET nome_cliente =?, cpf =? WHERE cod_cliente =?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getCPF());
            stmt.setInt(3, cliente.getCodigoCliente());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Cliente: " + e.getMessage());
        }

        return false;
    }

    public ClienteFisico buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM clientefisico WHERE cod_cliente = ?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ClienteFisico cliente = new ClienteFisico(
                        rs.getInt("cod_cliente"),
                        rs.getString("nome_cliente"),
                        rs.getString("cpf"));
                return cliente;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente físico: " + e.getMessage());
        }

        return null;
    }

}
