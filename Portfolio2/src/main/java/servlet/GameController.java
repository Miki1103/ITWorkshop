package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.CharacterDAO;
import dao.GameDAO;
import dao.QuestionDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Character;
import model.CharacterImageManager;
import model.Game;
import model.Question;

@WebServlet("/GameController")
public class GameController extends HttpServlet {
    private GameDAO gameDAO;
    private QuestionDAO questionDAO;

    @Override
    public void init() throws ServletException {
        try {
            gameDAO = new GameDAO();
            questionDAO = new QuestionDAO();
        } catch (Exception e) {
            throw new ServletException("Initialization failed", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
            return;
        }

        try {
            switch (action) {
                case "startGame":
                    startGame(request, response);
                    break;
                case "submitAnswer":
                    submitAnswer(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error occurred while processing action: " + action, e);
        }
    }

    private void startGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CharacterDAO characterDAO = new CharacterDAO();
            Character playerCharacter = characterDAO.getCharacterById(1);
            Character enemyCharacter = characterDAO.getCharacterById(2);

            if (playerCharacter == null || enemyCharacter == null) {
                throw new ServletException("Required characters are missing in the database.");
            }

            List<Question> questions = questionDAO.getAllQuestions();
            if (questions.isEmpty()) {
                throw new ServletException("No questions available.");
            }

            // Shuffle questions randomly and select first 20
            Collections.shuffle(questions);
            List<Question> selectedQuestions = new ArrayList<>();
            for (int i = 0; i < Math.min(20, questions.size()); i++) {
                selectedQuestions.add(questions.get(i));
            }

            Question firstQuestion = selectedQuestions.get(0);
            Game game = new Game(1, playerCharacter, enemyCharacter, firstQuestion, selectedQuestions);

            CharacterImageManager playerImageManager = new CharacterImageManager(playerCharacter);
            CharacterImageManager enemyImageManager = new CharacterImageManager(enemyCharacter);

            request.getSession().setAttribute("game", game);
            request.setAttribute("playerImagePath", playerImageManager.getImagePath());
            request.setAttribute("enemyImagePath", enemyImageManager.getImagePath());
            request.setAttribute("questions", selectedQuestions);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/QuestionView.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Failed to start the game", e);
        }
    }

    private void submitAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Game game = (Game) request.getSession().getAttribute("game");
            if (game == null) {
                response.sendRedirect("/Portfolio2/index.jsp");
                return;
            }

            String userAnswer = request.getParameter("answer");

            if (userAnswer == null || userAnswer.trim().isEmpty()) {
                // 回答が入力されていない場合はセッションをリセットし、NullError.jsp へ移動
                request.getSession().removeAttribute("game"); // セッションからゲームデータを削除
                response.sendRedirect(request.getContextPath() + "/jsp/NullError.jsp");
                return;
            }

            // 現在の質問と正解を取得
            Question currentQuestion = game.getCurrentQuestion();
            int questionId = currentQuestion.getId();

            QuestionDAO questionDAO = new QuestionDAO();
            String correctAnswer = questionDAO.getCorrectAnswer(questionId);

            // ユーザーの回答をチェック
            boolean isCorrect = userAnswer.trim().equals(correctAnswer.trim());
            game.checkAnswer(userAnswer.trim());

            // キャラクター画像のパスを更新
            CharacterImageManager playerImageManager = new CharacterImageManager(game.getPlayerCharacter());
            CharacterImageManager enemyImageManager = new CharacterImageManager(game.getEnemyCharacter());

            String playerImagePath = request.getContextPath() + "/" + playerImageManager.getImagePath();
            String enemyImagePath = request.getContextPath() + "/" + enemyImageManager.getImagePath();

            request.setAttribute("playerImagePath", playerImagePath);
            request.setAttribute("enemyImagePath", enemyImagePath);

            request.setAttribute("correctAnswer", correctAnswer);
            request.setAttribute("userAnswer", userAnswer);
            request.setAttribute("isCorrect", isCorrect);

            // 次の質問があるか確認
            if (game.hasNextQuestion()) {
                game.moveToNextQuestion();
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/QuestionView.jsp");
                dispatcher.forward(request, response);
            } else {
                String resultPage;
                if (game.getPlayerCharacter().getHp() > game.getEnemyCharacter().getHp()) {
                    resultPage = "/jsp/GameClearView.jsp";
                } else {
                    resultPage = "/jsp/GameOverView.jsp";
                }
                request.getSession().removeAttribute("game");
                RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Failed to submit answer", e);
        }
    }

}


