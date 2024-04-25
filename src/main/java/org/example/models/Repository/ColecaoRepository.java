package org.example.models.Repository;



import org.example.models.Modal.Carta;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColecaoRepository {

    List<Carta> cartas = new ArrayList<>();

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

}
