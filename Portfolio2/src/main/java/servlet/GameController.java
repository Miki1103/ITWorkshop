
package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.QuestionDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Question;

@WebServlet("/GameController")
public class GameController extends HttpServlet {
    private QuestionDAO questionDAO;

    @Override
    public void init() throws ServletException {
        try {
            questionDAO = new QuestionDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize QuestionDAO", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("submitAll".equals(action)) {
            handleAllAnswers(request, response);
        } else if ("startGame".equals(action)) {
            startGame(request, response);
        }
    }

    private void startGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Question> questions = questionDAO.getAllQuestions();
            if (questions == null || questions.isEmpty()) {
                System.out.println("Questions list is null or empty in startGame method");
            } else {
                System.out.println("Questions size in startGame method: " + questions.size());
            }
            request.setAttribute("questions", questions);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/QuestionView.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Failed to start the game: " + e.getMessage());
            throw new ServletException("Failed to start the game", e);
        }
    }



    private void handleAllAnswers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Question> questions = questionDAO.getAllQuestions();
            int correctCount = 0;
            Map<Integer, Boolean> results = new HashMap<>();

            for (Question question : questions) {
                String userAnswer = request.getParameter("answer_" + question.getId());
                boolean isCorrect = question.getCorrectAnswer().equals(userAnswer);
                results.put(question.getId(), isCorrect);
                if (isCorrect) {
                    correctCount++;
                }
            }

            request.setAttribute("results", results);
            request.setAttribute("score", correctCount);
            request.setAttribute("total", questions.size());

            // スコアによる条件分岐
            int passScore = 7; // ゲームクリアの基準スコア
            if (correctCount >= passScore) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/GameClearView.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/GameOverView.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Failed to handle all answers", e);
        }
    }
}



