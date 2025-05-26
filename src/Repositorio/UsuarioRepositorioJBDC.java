package Repositorio;

import Entidades.Usuario;
import Interfaces.IUsuarioRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import Conexao.conexaoBD;

public class UsuarioRepositorioJBDC implements IUsuarioRepositorio {

    public void cadastrar(Usuario usuario) {
        String sql = "INSERT INTO usuario (id_usuario, nome_usuario, email_usuario, senha_usuario, cpf_usuario, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getEmailUsuario());
            stmt.setString(4, usuario.getSenhaUsuario());
            stmt.setString(5, usuario.getCpf());
            stmt.setString(6, usuario.getTipoUsuario());

            stmt.executeUpdate();

            System.out.println("Usuario cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar Usuario: " + e.getMessage());
        }
    }

    public boolean remover(int codigo) {
        String sql = "DELETE FROM usuario WHERE id_usuario =?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);

            stmt.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Usuario: " + e.getMessage());
        }

        return false;
    }

    public ArrayList<Usuario> listarUsuario() {
        ArrayList<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuario";

        try (Connection conn = conexaoBD.conexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("id_usuario"),
                        rs.getString("nome_usuario"),
                        rs.getString("email_usuario"),
                        rs.getString("senha_usuario"),
                        rs.getString("cpf_usuario"),
                        rs.getString("tipo_usuario"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuarios: " + e.getMessage());
        }

        return lista;
    }

    public boolean alteraUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome_usuario =?, email_usuario =?, senha_usuario =?, cpf_usuario =?, tipo_usuario =? WHERE id_usuario =?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getEmailUsuario());
            stmt.setString(3, usuario.getSenhaUsuario());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getTipoUsuario());
            stmt.setInt(6, usuario.getId());

            stmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso.");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao altera Usuario: " + e.getMessage());
        }

        return false;
    }

    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";

        try (Connection conn = conexaoBD.conexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome_usuario"),
                        rs.getString("email_usuario"),
                        rs.getString("senha_usuario"),
                        rs.getString("cpf_usuario"),
                        rs.getString("tipo_usuario"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }

        return null;
    }
}
