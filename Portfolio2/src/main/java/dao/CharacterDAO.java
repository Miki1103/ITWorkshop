package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Character;

public class CharacterDAO {
    private Connection connection;

    public CharacterDAO(Connection connection) {
        this.connection = connection;
    }

    public Character getCharacterById(int id) throws SQLException {
        String query = "SELECT * FROM Characters WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
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
        String query = "UPDATE Characters SET hp = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, character.getHp());
            stmt.setInt(2, character.getId());
            stmt.executeUpdate();
        }
    }
}

