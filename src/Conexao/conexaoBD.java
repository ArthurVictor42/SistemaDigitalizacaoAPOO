package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexaoBD {
    public static String url = "jdbc:mysql://localhost:3306/projetoapoo";

    public static String usuario = "root";

    public static String senha = "";

    public static Connection conexao() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }
}
