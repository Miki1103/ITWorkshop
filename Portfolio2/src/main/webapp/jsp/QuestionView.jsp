<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Question" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ゲームスタート画面</title>
    <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
    <link rel="stylesheet" href="http://localhost:8080/Portfolio2/css/style.css">
</head>
<body>
    <h1 class="align-center">
        <a href="/Portfolio2/index.jsp"><img class="logo" src="http://localhost:8080/Portfolio2/images/logo1.png" alt="簿記試験攻略ゲーム"></a>
    </h1>
    <h2 class="start align-center">
        選択肢から答えを選んでください<br>
    </h2>
    <div class="gameinfo-page">
        <form action="GameController" method="post">
            <% 
                List<Question> questions = (List<Question>) request.getAttribute("questions");
                if (questions != null && !questions.isEmpty()) {
                    out.println("Questions size: " + questions.size());
                    for (Question question : questions) {
            %>
                <div>
                    <p><%= question.getText() %></p>
                    <% 
                        List<String> choices = question.getChoices();
                        for (String choice : choices) {
                    %>
                        <input type="radio" name="answer_<%= question.getId() %>" value="<%= choice %>" /> <%= choice %><br/>
                    <% 
                        }
                    %>
                </div>
            <% 
                    }
                } else {
                    out.println("Questions list is null or empty in JSP");
            %>
                <p>質問が読み込まれませんでした。</p>
            <% 
                }
            %>
            <button type="submit" name="action" value="submitAll">結果を見る</button>
        </form>
    </div>
    <div class="grid-container">
        <img class="girl" src="http://localhost:8080/Portfolio2/images/girl1.gif">
        <img class="girl" src="http://localhost:8080/Portfolio2/images/girl2.gif">
    </div>
</body>
</html>




