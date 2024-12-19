package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Game;

public class GameDAO {
    private static final String URL = "jdbc:h2:~/desktop/制作SQL/user";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String DRIVER_CLASS = "org.h2.Driver";

    public GameDAO() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBCドライバーが見つかりませんでした", e);
        }
    }

    public void saveGameStatus(Game game) throws SQLException {
        String query = """
            MERGE INTO GameStatus (id, current_question_id, player_character_id, enemy_character_id)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, game.getId());
            stmt.setInt(2, game.getCurrentQuestion().getId());
            stmt.setInt(3, game.getPlayerCharacter().getId());
            stmt.setInt(4, game.getEnemyCharacter().getId());

            stmt.executeUpdate();
        }
    }
}


