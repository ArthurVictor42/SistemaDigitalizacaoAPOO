package Repositorio;

import Entidades.ClienteJuridico;
import Interfaces.IClienteJuridicoRepositorio;

import java.util.ArrayList;

import Conexao.conexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteJuridicoRepositorioJDBC implements IClienteJuridicoRepositorio {

    public void cadastrar(ClienteJuridico cliente) {
        String sql = "INSERT INTO clientejuridico (cod_clienteJ, nome_clienteJ, cnpj) VALUES (?, ?, ?)";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getCodigoCliente());
            stmt.setString(2, cliente.getNomeCliente());
            stmt.setString(3, cliente.getCNPJ());

            stmt.executeUpdate();

            System.out.println("Cliente Juridico cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar Cliente: " + e.getMessage());
        }
    }

    public boolean remover(int codigo) {
        String sql = "DELETE FROM clientejuridico WHERE cod_clienteJ = ?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }

        return false;
    }

    public ArrayList<ClienteJuridico> listarEmpresa() {
        ArrayList<ClienteJuridico> lista = new ArrayList<>();

        String sql = "SELECT * FROM clientejuridico";

        try (Connection conn = conexaoBD.conexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ClienteJuridico cliente = new ClienteJuridico(rs.getInt("cod_clienteJ"),
                        rs.getString("nome_clienteJ"),
                        rs.getString("cnpj"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar Cliente: " + e.getMessage());
        }

        return lista;
    }

    public boolean alteraCliente(ClienteJuridico cliente) {
        String sql = "UPDATE clientejuridico SET nome_clienteJ =?, cnpj =? WHERE cod_clienteJ =?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getCNPJ());
            stmt.setInt(3, cliente.getCodigoCliente());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Cliente: " + e.getMessage());
        }

        return false;
    }

    public ClienteJuridico buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM clientejuridico WHERE cod_clienteJ = ?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ClienteJuridico cliente = new ClienteJuridico(
                        rs.getInt("cod_clienteJ"),
                        rs.getString("nome_clienteJ"),
                        rs.getString("cnpj"));
                return cliente;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente jurídico: " + e.getMessage());
        }

        return null;
    }

}
