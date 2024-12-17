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
            System.out.println("DAOs initialized successfully.");
        } catch (Exception e) {
            System.out.println("Failed to initialize DAOs: " + e.getMessage());
            throw new ServletException("Initialization failed", e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GETリクエストをPOST処理に委譲
        doPost(request, response);
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

            // キャラクター画像のパスを生成
            CharacterImageManager playerImageManager = new CharacterImageManager(playerCharacter);
            CharacterImageManager enemyImageManager = new CharacterImageManager(enemyCharacter);
            
            request.getSession().setAttribute("game", game);
            request.setAttribute("playerImagePath", playerImageManager.getImagePath());
            request.setAttribute("enemyImagePath", enemyImageManager.getImagePath());
            request.setAttribute("questions", questions);

            System.out.println("Game object and image paths set in request scope.");

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
            Game game = (Game) request.getSession().getAttribute("game");
            if (game == null) {
                System.out.println("No game object in session. Redirecting to index page.");
                response.sendRedirect("/Portfolio2/index.jsp");
                return;
            }

            // ユーザーの回答を取得し、nullチェック
            String userAnswer = request.getParameter("answer");

            if (userAnswer == null || userAnswer.trim().isEmpty()) {
                System.out.println("User did not select an answer. Redirecting to NullError.jsp.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/NullError.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // ユーザーの回答をチェック
            game.checkAnswer(userAnswer.trim());

            System.out.println("DEBUG: Player HP: " + game.getPlayerCharacter().getHp());
            System.out.println("DEBUG: Enemy HP: " + game.getEnemyCharacter().getHp());

            // キャラクター画像のパスを再設定
            CharacterImageManager playerImageManager = new CharacterImageManager(game.getPlayerCharacter());
            CharacterImageManager enemyImageManager = new CharacterImageManager(game.getEnemyCharacter());

            String playerImagePath = request.getContextPath() + "/" + playerImageManager.getImagePath();
            String enemyImagePath = request.getContextPath() + "/" + enemyImageManager.getImagePath();

            // リクエストに画像パスとゲームオブジェクトをセット
            request.setAttribute("playerImagePath", playerImagePath);
            request.setAttribute("enemyImagePath", enemyImagePath);
            request.setAttribute("game", game);

            // 次の質問があるかチェック
            if (game.hasNextQuestion()) {
                game.moveToNextQuestion(); // 次の質問へ移動
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/QuestionView.jsp");
                dispatcher.forward(request, response);
            } else {
                // 全ての質問が終わった場合、HPを比較して結果を表示
                String resultPage;
                if (game.getPlayerCharacter().getHp() > game.getEnemyCharacter().getHp()) {
                    System.out.println("Player wins. Redirecting to GameClearView.jsp.");
                    resultPage = "/jsp/GameClearView.jsp";
                } else if (game.getPlayerCharacter().getHp() < game.getEnemyCharacter().getHp()) {
                    System.out.println("Enemy wins. Redirecting to GameOverView.jsp.");
                    resultPage = "/jsp/GameOverView.jsp";
                } else {
                    System.out.println("It's a draw. Redirecting to GameClearView.jsp.");
                    resultPage = "/jsp/GameClearView.jsp";
                }

                request.getSession().removeAttribute("game"); // ゲームオブジェクトをセッションから削除
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