package connection;

import java.sql.*;

public class ConexaoMySQL {

	private static String DRIVER = "com.mysql.cj.jdbc.Driver"; //indica o nome da classe dentro do JDBC
    private static String URL = "jdbc:mysql://localhost:3306/tdsi202112"; //endereço de acesso
    private static String USER = "root"; //usuário do bd
    private static String PASS = "lordsith23"; //senha do bd

    public static Connection iniciarConexao() { //método para conectar bd - retorna objeto do tipo Connection
        try { //TENTATIVA DE LOCALIZAÇÃO DA CLASSE DE CONEXÃO
            Class.forName(DRIVER); //será realizada uma busca pela classe
            return DriverManager.getConnection(URL, USER, PASS); //retorna conexao
        } catch (ClassNotFoundException | SQLException e) { //CASO NÃO ENCONTRE A CLASSE
            throw new RuntimeException("Erro na conexão: " + e); //Impressão do erro
        }
    }

    public static void encerrarConexao(Connection connection) {
        if (connection != null) { //SE CONEXÃO FOR DIFERENTE DE NULO
            try {
                connection.close(); //finaliza conexão
            } catch (SQLException e) { //SE TENTAR FINALIZAR CONEXÃO INEXISTENTE
                e.printStackTrace();//exibe a mensagem de erro
            }
        }
    }

    public static void encerrarConexao(Connection connection, PreparedStatement stmt) {
        encerrarConexao(connection);
        try {
            stmt.close();
        } catch (SQLException e) { //SE TENTAR FINALIZAR PREPAREDSTATEMENT INEXISTENTE
            e.printStackTrace();//exibe a mensagem de erro
        }
    }

    public static void encerrarConexao(Connection connection, PreparedStatement stmt, ResultSet rs) {
        encerrarConexao(connection, stmt);
        try {
            rs.close();
        } catch (SQLException e) { //SE TENTAR FINALIZAR RESULTSET INEXISTENTE
            e.printStackTrace();//exibe a mensagem de erro
        }
    }
	
}
