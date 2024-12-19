package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Character;

public class CharacterDAO {
    private static final String URL = "jdbc:h2:~/desktop/制作SQL/user";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String DRIVER_CLASS = "org.h2.Driver"; 

    public CharacterDAO() {
        try {
            Class.forName(DRIVER_CLASS); 
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBCドライバーが見つかりませんでした", e);
        }
    }

    public Character getCharacterById(int id) throws SQLException {
        String query = "SELECT * FROM Characters WHERE id = " + id;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return new Character(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("max_hp"),
                    rs.getInt("hp")
                );
            }
        }
        return null;
    }

    public void updateCharacter(Character character) throws SQLException {
        String query = String.format(
            "UPDATE Characters SET hp = %d WHERE id = %d",
            character.getHp(), character.getId());
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        }
    }

    public void addCharacter(Character character) throws SQLException {
        String query = String.format(
            "INSERT INTO Characters (name, max_hp, hp) VALUES ('%s', %d, %d)",
            character.getName(), character.getMaxHp(), character.getHp());
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        }
    }
}

