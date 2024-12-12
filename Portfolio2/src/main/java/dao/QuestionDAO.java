package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Question;

public class QuestionDAO {
    private Connection connection;

    public QuestionDAO(Connection connection) {
        this.connection = connection;
    }

    public Question getQuestionById(int id) throws SQLException {
        String query = "SELECT * FROM Questions WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Question(
                    rs.getInt("id"),
                    rs.getString("text"),
                    rs.getString("choices"),
                    rs.getString("correct_answer")
                );
            }
        }
        return null;
    }

    public List<Question> getAllQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM Questions";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                questions.add(new Question(
                    rs.getInt("id"),
                    rs.getString("text"),
                    rs.getString("choices"),
                    rs.getString("correct_answer")
                ));
            }
        }
        return questions;
    }

    public void addQuestion(Question question) throws SQLException {
        String query = "INSERT INTO Questions (text, choices, correct_answer) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, question.getText());
            stmt.setString(2, question.getChoices());
            stmt.setString(3, question.getCorrectAnswer());
            stmt.executeUpdate();
        }
    }
}

