package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class UserDAO {
    private static final String URL = "jdbc:h2:~/desktop/制作SQL/user";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
        try {
            Class.forName("org.h2.Driver"); 
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBCドライバーが見つかりませんでした", e);
        }
    }

    public User findUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            Class.forName("org.h2.Driver"); 
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at")
                        );
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBCドライバーが見つかりませんでした", e);
        }
        return null;
    }

    public void updatePassword(String username, String newPasswordHash) throws SQLException {
        String sql = "UPDATE users SET password_hash = ? WHERE username = ?";
        try {
            Class.forName("org.h2.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, newPasswordHash);
                stmt.setString(2, username);
                stmt.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBCドライバーが見つかりませんでした", e);
        }
    }
}


