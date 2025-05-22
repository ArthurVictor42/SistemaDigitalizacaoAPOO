package Repositorio;

import Entidades.Categoria;
import Interfaces.ICategoriaRepositorio;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.conexaoBD;

public class CategoriaRepositorioJDBC implements ICategoriaRepositorio {

    public void cadastrar(Categoria categoria) {

        String sql = "INSERT INTO categoria (id_cate, nome_cate) VALUES (?, ?)";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoria.getId());
            stmt.setString(2, categoria.getNomeCategoria());

            stmt.executeUpdate();

            System.out.println("Categoria cadastrada com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar Categoria: " + e.getMessage());
        }
    }

    public boolean remover(int id) {

        String sql = "DELETE FROM categoria WHERE id_cate = ? ";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Categoria removida com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remove categoria: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Categoria> listarCategoria() {
        ArrayList<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Connection conn = conexaoBD.conexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getInt("id_cate"),
                        rs.getString("nome_cate"));

                lista.add(categoria);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar Categoiras: " + e.getMessage());
        }

        return lista;
    }

    public boolean alteraCategoria(Categoria categoria) {
        String sql = "UPDATE categoria SET nome_cate=? WHERE id_cate=?";
        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar categoria: " + e.getMessage());
        }

        return false;
    }

    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id_cate = ?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categoria categoria = new Categoria(
                    rs.getInt("id_cate"),
                    rs.getString("nome_cate"));
                return categoria;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar Categoria: " + e.getMessage());
        }

        return null;
    }
}
