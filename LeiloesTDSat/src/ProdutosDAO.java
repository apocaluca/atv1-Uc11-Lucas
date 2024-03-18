import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultSet;
    
    // Outros métodos...

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        conn = new ConectaDAO().connectDB(); // Conecta ao banco de dados

        ArrayList<ProdutosDTO> produtosVendidos = new ArrayList<>();

        try {
            // Prepara a consulta SQL para buscar produtos com status "Vendido"
            String query = "SELECT * FROM produtos WHERE status = ?";
            prep = conn.prepareStatement(query);
            prep.setString(1, "Vendido");
            
            // Executa a consulta
            resultSet = prep.executeQuery();
            
            // Itera sobre o resultado da consulta e adiciona os produtos à lista
            while (resultSet.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getDouble("valor"));
                produto.setStatus(resultSet.getString("status"));
                
                produtosVendidos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha os recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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

        return produtosVendidos;
    }
}
