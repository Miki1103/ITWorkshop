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
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"> <!-- 修正されたCSSパス -->
</head>
<body>
    <h1 class="align-center">
        <a href="<%= request.getContextPath() %>/index.jsp"><img class="logo" src="<%= request.getContextPath() %>/images/logo1.png" alt="簿記試験攻略ゲーム"></a>
    </h1>
    <div class="game-main">
        <%
            // セッションスコープから`game`オブジェクトを取得
            Game game = (Game) session.getAttribute("game");
            if (game == null) {
        %>
    <div class="start-container">
    	<h3 class="start align-center">ゲームを開始してください</h3>
    	<p>タイムアタック形式を追加する際にいきなりゲームが始まらないようにゲーム開始画面を作成しています。<br>
    	現段階ではタイムアタック形式の機能は実装していません。</p>
    <form action="<%= request.getContextPath() %>/GameController" method="post">
        	<input type="hidden" name="action" value="startGame">
    		<button type="submit">ゲーム開始</button>
    	 </form>
	</div>
               
        <%
            } else {
                // ゲーム進行中
                Question currentQuestion = game.getCurrentQuestion();
                List<Question> questions = (List<Question>) game.getQuestions();
                int currentIndex = game.getCurrentIndex() + 1; // 現在の質問番号 (1-based)
        %>
                <!-- 質問コンテナ -->
                <div class="question-container">
                    <h2 class="align-center">問題 <%= currentIndex %> / <%= questions.size() %></h2>
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
                        <input type="hidden" name="action" value="submitAnswer">
                        <button type="submit">回答を送信</button>
                    </form>
                </div>

                <!-- ステータスコンテナ -->
                <div class="status-container">
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

                    <!-- プレイヤーのステータス -->
                    <h3>進行状況</h3>
                    <p>
                        <%
                            if (game.getPlayerCharacter().getHp() > game.getEnemyCharacter().getHp()) {
                                out.println("プレイヤーが優勢です！");
                            } else if (game.getPlayerCharacter().getHp() < game.getEnemyCharacter().getHp()) {
                                out.println("敵が優勢です…頑張ってください！");
                            } else {
                                out.println("互角の勝負です！");
                            }
                        %>
                    </p>
                </div>
        <%
            }
        %>
    </div>
</body>
</html>






