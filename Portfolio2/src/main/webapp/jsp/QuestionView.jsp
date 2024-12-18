<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Game" %>
<%@ page import="model.Question" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ゲーム画面</title>
    <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
    <link rel="icon" href="http://localhost:8080/Portfolio2/images/logo2.png" type="image/png">
    <link rel="stylesheet" href="http://localhost:8080/Portfolio2/css/style.css">
</head>
<body>
<div class="game-main">
        <%
            Game game = (Game) session.getAttribute("game");
            if (game == null) {
        %>
            <div class="start-container">
                <h3 class="start align-center">ゲームを開始してください</h3>
                <p>
                    タイムアタック形式を今後追加する予定です。<br>
                    そのため、ゲーム開始のみの画面を作成しました。<br>
                    現段階ではタイムアタック形式の機能は実装していません。
                </p>
                <form action="<%= request.getContextPath() %>/GameController" method="post">
                    <input type="hidden" name="action" value="startGame">
                    <button type="submit">ゲーム開始</button>
                </form>
            </div>
        <%
            } else {
                Question currentQuestion = game.getCurrentQuestion();
                List<Question> questions = (List<Question>) game.getQuestions();
                int currentIndex = game.getCurrentIndex() + 1; // 現在の質問番号 (1-based)
        %>
            <div class="question-container">
                <h3><%= currentQuestion.getText() %></h3>
                <form action="<%= request.getContextPath() %>/GameController" method="post">
                    <%
                        List<String> choices = currentQuestion.getChoices();
                        for (String choice : choices) {
                    %>
                        <label>
                            <input type="radio" name="answer" value="<%= choice %>"> <%= choice %>
                        </label><br>
                    <%
                        }
                    %>
                    <% if (request.getAttribute("errorMessage") != null) { %>
                    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
                     <% } %>
                    
                    <input type="hidden" name="action" value="submitAnswer">
                    <button type="submit">回答を送信</button>
                </form>
        <p>問題の回答詳細</p>       
		<p>正解: <%= request.getAttribute("correctAnswer") != null ? request.getAttribute("correctAnswer") : "不明" %></p>
		<p>あなたの回答: <%= request.getAttribute("userAnswer") != null ? request.getAttribute("userAnswer") : "未回答" %></p>
               
            </div>
		<%
   	      String playerImagePath = (String) request.getAttribute("playerImagePath");
          String enemyImagePath = (String) request.getAttribute("enemyImagePath");

    	if (playerImagePath == null) {
        playerImagePath = "http://localhost:8080/Portfolio2/images/player_healthy.png"; 
   	 }
    	if (enemyImagePath == null) {
        enemyImagePath = "http://localhost:8080/Portfolio2/images/enemy_healthy.png"; 
    	}
		%>
           <!-- キャラクター画像コンテナ -->
           <div class="grid-container">
    	<div class="player-image">
        <h3>プレイヤーキャラクター</h3>
        <img src="<%= playerImagePath %>" alt="Player Character">
    	</div>
    	<div class="enemy-image">
        	<h3>敵キャラクター</h3>
        	<img src="<%= enemyImagePath %>" alt="Enemy Character">
    	</div>
		</div>

            <!-- ステータスコンテナ -->
            <div class="grid-container">
                <!-- プレイヤーのHP -->
                <div>
                    <p>プレイヤーのHP: <%= game.getPlayerCharacter().getHp() %></p>
                    <div class="hp-bar">
                        <div class="hp-bar-inner player-hp" style="width: <%= game.getPlayerCharacter().getHp() %>%;"></div>
                    </div>
                </div>

                <!-- 敵のHP -->
                <div>
                    <p>敵のHP: <%= game.getEnemyCharacter().getHp() %></p>
                    <div class="hp-bar">
                        <div class="hp-bar-inner enemy-hp" style="width: <%= game.getEnemyCharacter().getHp() %>%;"></div>
                    </div>
                </div>
            </div>
        <%
            }
        %>
    </div> 
</body>
</html>








