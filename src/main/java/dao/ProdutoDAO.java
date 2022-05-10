package dao;

import connection.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Produto;

public class ProdutoDAO {

	public void create(Produto produto) {
        Connection connection = ConexaoMySQL.iniciarConexao(); //Abre conexão com BD
        PreparedStatement stmt = null; //Cria um prepared statement

        try {
            stmt = connection.prepareStatement("INSERT INTO produto (nome, preco, quantidadeEstoque) VALUES (?,?,?)"); //SQL
            stmt.setString(1, produto.getNome()); //Pega os valores dos atributos do objeto e joga para os VALUES
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());

            stmt.executeUpdate(); //Executa o SQL

        } catch (SQLException e) { //Caso retorne algum erro do BD
            e.printStackTrace();
        } finally { //Finaliza conexão com BD
            ConexaoMySQL.encerrarConexao(connection, stmt);
        }
    }

    public ArrayList<Produto> read(){
        Connection connection = ConexaoMySQL.iniciarConexao(); //Inicia conexão
        PreparedStatement stmt = null; //Cria um Prepared Statement
        ResultSet rs = null; //Cria um Result Set
        ArrayList<Produto> listaprodutos = new ArrayList<>(); //Cria ArrayList para retorno da função
        try {
            stmt = connection.prepareStatement("SELECT * FROM produto"); //SQL
            rs = stmt.executeQuery(); //Executa o SQL e armazena o resultado no ResultSet

            while (rs.next()){ //Percorre o ResultSet para passar os valores dele para a ArrayList
                model.Produto produto = new model.Produto(); //Cria o objeto que irá compor a ArrayList
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome")); //Pega valores do ResultSet e armazena no atributo do objeto
                produto.setPreco(rs.getDouble("preco")); //O columnLabel deve corresponder à coluna do BD
                produto.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
                listaprodutos.add(produto); //Por fim ao jogar os valores para os atributos do objeto, adiciona ele na lista
            }

        } catch (SQLException e) { //Caso retorne algum erro do BD
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt, rs);
        }
        return listaprodutos;
    }

    public void update(Produto produto) {
        Connection connection = ConexaoMySQL.iniciarConexao(); //Abre conexão com BD
        PreparedStatement stmt = null; //Cria um prepared statement

        try {
            stmt = connection.prepareStatement("UPDATE produto SET nome = ?, preco = ?, quantidadeEstoque = ? WHERE id = ?"); //SQL
            stmt.setString(1, produto.getNome()); //Pega os valores dos atributos do objeto e substitui no SQL
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());
            stmt.setInt(4, produto.getId());

            stmt.executeUpdate(); //Executa o SQL

        } catch (SQLException e) { //Caso retorne algum erro do BD
            e.printStackTrace();
        } finally { //Finaliza conexão com BD
            ConexaoMySQL.encerrarConexao(connection, stmt);
        }
    }
    public void delete(Produto produto) {
        Connection connection = ConexaoMySQL.iniciarConexao(); //Abre conexão com BD
        PreparedStatement stmt = null; //Cria um prepared statement

        try {
            stmt = connection.prepareStatement("DELETE FROM produto WHERE id = ?"); //SQL
            stmt.setInt(1, produto.getId()); //Pega o valor do atributo ID e joga para o SQL

            stmt.executeUpdate(); //Executa o SQL

        } catch (SQLException e) { //Caso retorne algum erro do BD
            e.printStackTrace();
        } finally { //Finaliza conexão com BD
            ConexaoMySQL.encerrarConexao(connection, stmt);
        }
    }
    public Produto selecionarProduto(Produto produto){
        Connection connection = ConexaoMySQL.iniciarConexao(); //Inicia conexão
        PreparedStatement stmt = null; //Cria um Prepared Statement
        ResultSet rs = null; //Cria um Result Set
        ArrayList<Produto> listaprodutos = new ArrayList<>(); //Cria ArrayList para retorno da função
        try {
            stmt = connection.prepareStatement("SELECT * FROM produto WHERE id = ?"); //SQL
            stmt.setInt(1, produto.getId()); //Pega o valor do atributo ID e joga para o SQL
            rs = stmt.executeQuery(); //Executa o SQL e armazena o resultado no ResultSet

            while (rs.next()){ //Percorre o ResultSet para passar os valores dele para a ArrayList
                //model.Produto produto = new model.Produto(); //Cria o objeto que irá compor a ArrayList
                //produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome")); //Pega valores do ResultSet e armazena no atributo do objeto
                produto.setPreco(rs.getDouble("preco")); //O columnLabel deve corresponder à coluna do BD
                produto.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
                listaprodutos.add(produto); //Por fim ao jogar os valores para os atributos do objeto, adiciona ele na lista
            }

        } catch (SQLException e) { //Caso retorne algum erro do BD
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt, rs);
        }
        return produto;
    }
	
}
