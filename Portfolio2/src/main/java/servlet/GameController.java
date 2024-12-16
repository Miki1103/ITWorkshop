package servlet;

import java.io.IOException;
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
            System.out.println("DAOs initialized successfully.");
        } catch (Exception e) {
            System.out.println("Failed to initialize DAOs: " + e.getMessage());
            throw new ServletException("Initialization failed", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
            return;
        }

        System.out.println("Action: " + action);

        try {
            switch (action) {
                case "startGame":
                    startGame(request, response);
                    break;
                case "submitAnswer":
                    submitAnswer(request, response);
                    break;
                default:
                    System.out.println("Unknown action: " + action);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error in doPost: " + e.getMessage());
            e.printStackTrace();
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

            Question firstQuestion = questions.get(0);
            Game game = new Game(1, playerCharacter, enemyCharacter, firstQuestion, questions);

            System.out.println("Game created with ID: " + game.getId());

            request.getSession().setAttribute("game", game);
            System.out.println("Game object set in session scope.");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/QuestionView.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Failed to start the game: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException("Failed to start the game", e);
        }
    }

    private void submitAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // セッションスコープからゲームオブジェクトを取得
            Game game = (Game) request.getSession().getAttribute("game");

            // ゲームオブジェクトがセッションに存在しない場合の処理
            if (game == null) {
                System.out.println("No game object in session. Redirecting to index page.");
                response.sendRedirect("/Portfolio2/index.jsp");
                return;
            }

            // ユーザーの回答を取得して判定
            String userAnswer = request.getParameter("answer");
            game.checkAnswer(userAnswer);

            System.out.println("DEBUG: プレイヤーHP: " + game.getPlayerCharacter().getHp());
            System.out.println("DEBUG: 敵HP: " + game.getEnemyCharacter().getHp());

            // ゲームオーバー判定
            if (game.isGameOver()) {
                String resultPage = game.getPlayerCharacter().isAlive()
                                    ? "/jsp/GameClearView.jsp"
                                    : "/jsp/GameOverView.jsp";

                // ゲームオブジェクトをセッションから削除
                request.getSession().removeAttribute("game");
                System.out.println("Game object removed from session scope.");

                request.setAttribute("game", game);
                RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
                dispatcher.forward(request, response);
                return;
            }

            // 次の質問がある場合
            if (game.hasNextQuestion()) {
                game.moveToNextQuestion();
                request.setAttribute("game", game);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/QuestionView.jsp");
                dispatcher.forward(request, response);
            } else {
                // 最後の質問を解き終わった場合のクリア/オーバー判定
                String resultPage = game.getPlayerCharacter().isAlive()
                                    ? "/jsp/GameClearView.jsp"
                                    : "/jsp/GameOverView.jsp";

                // ゲームオブジェクトをセッションから削除
                request.getSession().removeAttribute("game");
                System.out.println("Game object removed from session scope.");

                request.setAttribute("game", game);
                RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Failed to submit answer: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException("Failed to submit answer", e);
        }
    }
}
