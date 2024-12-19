package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Question;

public class QuestionDAO {
    private static final String URL = "jdbc:h2:~/desktop/制作SQL/user";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String DRIVER_CLASS = "org.h2.Driver";

    public QuestionDAO() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBCドライバーが見つかりませんでした", e);
        }
    }

    public Question getQuestionById(int id) throws SQLException {
        String query = "SELECT * FROM Questions WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                List<String> choices = parseChoices(rs.getString("choices"));
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
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                List<String> choices = parseChoices(rs.getString("choices"));
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
        String query = "INSERT INTO Questions (text, choices, correct_answer, weight) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, question.getText());
            stmt.setString(2, String.join(",", question.getChoices()));
            stmt.setString(3, question.getCorrectAnswer());
            stmt.setInt(4, question.getWeight());

            stmt.executeUpdate();
        }
    }

    public String getCorrectAnswer(int questionId) throws SQLException {
        String query = "SELECT correct_answer FROM Questions WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, questionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("correct_answer");
            }
        }
        return null;
    }

    public boolean isCorrect(int questionId, String answer) throws SQLException {
        String correctAnswer = getCorrectAnswer(questionId);
        return correctAnswer != null && correctAnswer.equals(answer);
    }

    private List<String> parseChoices(String choices) {
        List<String> parsedChoices = new ArrayList<>();
        if (choices != null && !choices.isEmpty()) {
            for (String choice : choices.split(",")) {
                parsedChoices.add(choice.trim());
            }
        }
        return parsedChoices;
    }
}


