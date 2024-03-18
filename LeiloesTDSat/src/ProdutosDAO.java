

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    
    // Outros métodos...

    public boolean venderProduto(int id) {
        conn = new conectaDAO().connectDB(); // Conecta ao banco de dados

        try {
            // Prepara a consulta SQL para atualizar o status do produto
            String query = "UPDATE produtos SET status = ? WHERE id = ?";
            prep = conn.prepareStatement(query);
            
            // Define os parâmetros da consulta
            prep.setString(1, "Vendido");
            prep.setInt(2, id);
            
            // Executa a consulta
            int rowsAffected = prep.executeUpdate();
            
            // Verifica se a atualização foi bem-sucedida
            if (rowsAffected > 0) {
                return true; // Retorna true se a venda foi realizada com sucesso
            } else {
                return false; // Retorna false se o ID do produto não existe no banco de dados
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de exceção
        } finally {
            // Fecha os recursos
            try {
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

    
    
