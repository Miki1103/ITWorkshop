
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Question;

public class QuestionDAO {
    private static final String URL = "jdbc:h2:~/desktop/制作SQL/questions";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String DRIVER_CLASS = "org.h2.Driver"; // JDBCドライバーのクラス名

    public QuestionDAO() {
        try {
            Class.forName(DRIVER_CLASS); // JDBCドライバーをロード
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBCドライバーが見つかりませんでした", e);
        }
    }

    public List<Question> getAllQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM Questions";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                List<String> choices = List.of(rs.getString("choices").split(","));
                questions.add(new Question(
                    rs.getInt("id"),
                    rs.getString("text"),
                    choices,
                    rs.getString("correct_answer"),
                    rs.getInt("weight")
                ));
            }

            if (questions.isEmpty()) {
                System.out.println("No questions found in the database.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            throw e;
        }

        return questions;
    }
}
    



