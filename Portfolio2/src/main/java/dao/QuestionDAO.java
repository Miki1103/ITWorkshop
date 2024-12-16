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
    private static final String URL = "jdbc:h2:~/desktop/制作SQL/user";
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

    public Question getQuestionById(int id) throws SQLException {
        String query = "SELECT * FROM Questions WHERE id = " + id;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                List<String> choices = List.of(rs.getString("choices").split(","));
                return new Question(
                    rs.getInt("id"),
                    rs.getString("text"),
                    choices,
                    rs.getString("correct_answer"),
                    rs.getInt("weight")
                );
            }
        }
        return null;
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
        }
        return questions;
    }

    public void addQuestion(Question question) throws SQLException {
        String query = String.format(
            "INSERT INTO Questions (text, choices, correct_answer, weight) VALUES ('%s', '%s', '%s', %d)",
            question.getText(), String.join(",", question.getChoices()), question.getCorrectAnswer(), question.getWeight());
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        }
    }

    public boolean isCorrect(int questionId, String answer) throws SQLException {
        Question question = getQuestionById(questionId);
        return question != null && question.getCorrectAnswer().equals(answer);
    }
}

    



