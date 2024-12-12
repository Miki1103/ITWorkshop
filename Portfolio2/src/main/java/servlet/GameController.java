package servlet;

import java.io.IOException;
import java.sql.Connection;

import dao.CharacterDAO;
import dao.QuestionDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Question;

public class GameController extends HttpServlet {
    private QuestionDAO questionDAO;
    private CharacterDAO characterDAO;

    @Override
    public void init() {
        questionDAO = new QuestionDAO((Connection) getServletContext().getAttribute("DBConnection"));
        characterDAO = new CharacterDAO((Connection) getServletContext().getAttribute("DBConnection"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("answer".equals(action)) {
            handleAnswer(request, response);
        }
    }

    private void handleAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String answer = request.getParameter("answer");

        try {
            Question question = questionDAO.getQuestionById(questionId);
            if (question.getCorrectAnswer().equals(answer)) {
                request.setAttribute("result", "正解!");
            } else {
                request.setAttribute("result", "不正解...");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("QuestionView.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

