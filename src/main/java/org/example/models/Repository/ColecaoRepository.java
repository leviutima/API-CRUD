package org.example.models.Repository;



import org.example.models.Modal.Carta;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColecaoRepository {


    private static final Logger logger = LogManager.getLogger(ColecaoRepository.class);

    public static List<Carta> cartas = new ArrayList<>();

    public static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm553580";
    public static final String PASSWORD = "180804";

    public ColecaoRepository() {
    }

    public List<Carta> getCartas() {
        try {
            var conn = DriverManager.getConnection(URL, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM COLECAO");
            var rs = stat.executeQuery();
            while (rs.next()) {
                var carta = new Carta();
                carta.setId(rs.getInt("ID"));
                carta.setNome(rs.getString("NOME"));
                carta.setDescricao(rs.getString("DESCRICAO"));
                carta.setPreco(rs.getDouble("PRECO"));
                cartas.add(carta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartas;
    }

    public Carta getCarta(int id) {
        Carta cartas = null;
        try {
            var conn = DriverManager.getConnection(URL, USER, PASSWORD);
            var stat = conn.prepareStatement("SELECT * FROM COLECAO WHERE ID = ?");
            stat.setInt(1, id);
            var rs = stat.executeQuery();
            if (rs.next()) {
                cartas = new Carta();
                cartas.setId(rs.getInt("ID"));
                cartas.setNome(rs.getString("NOME"));
                cartas.setDescricao(rs.getString("DESCRICAO"));
                cartas.setPreco(rs.getDouble("PRECO"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartas;
    }

    public void createCarta (Carta carta) {
        try {
            var conn = DriverManager.getConnection(URL, USER, PASSWORD);
            var stat = conn.prepareStatement("INSERT INTO COLECAO(ID, NOME, DESCRICAO, PRECO) VALUES (?,?,?,?)");
            stat.setInt(1,carta.getId());
            stat.setString(2,carta.getNome());
            stat.setString(3,carta.getDescricao());
            stat.setDouble(4, carta.getPreco());
            stat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCarta(Carta carta) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE COLECAO SET NOME = ?, DESCRICAO = ?, PRECO = ? WHERE ID = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, carta.getNome());
                statement.setString(2, carta.getDescricao());
                statement.setDouble(3, carta.getPreco());
                statement.setInt(4, carta.getId());

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated == 0) {
                    throw new RuntimeException("A carta com o ID " + carta.getId() + " não foi encontrada");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar carta", e);
        }
    }

    public void deleteCarta(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM COLECAO WHERE ID = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, id);
                int delete = statement.executeUpdate();
                if (delete == 0) {
                    throw new RuntimeException("A carta com o ID " + id + " não foi encontrada");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir carta", e);
        }
    }
}

