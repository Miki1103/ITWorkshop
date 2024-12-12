<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="ゲーム感覚で簿記の勉強ができるアプリ">
    <title>ゲームスタート画面</title>
    <meta name="viewport" content="width, initial-scale=1">

    <!-- リセットCSS -->
     <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">

    <!-- オリジナルCSS -->
     <link rel="stylesheet" href="http://localhost:8080/Portfolio2/css/style.css">

</head>
<body>
    <h1 class="align-center">
        <a href="/Portfolio2/index.jsp"><img class="logo" src="http://localhost:8080/Portfolio2/images/logo1.png" alt="簿記試験攻略ゲーム">
            </a>
    </h1>

    <h2 class="start align-center">
        選択肢から答えを選んでください<br>
    </h2>
	 <h1>問題</h1>
    <p>${question.text}</p>
    <form action="GameController" method="post">
        <input type="hidden" name="questionId" value="${question.id}" />
        <c:forEach var="choice" items="${question.choices.split(',')}">
            <input type="radio" name="answer" value="${choice}" /> ${choice}<br/>
        </c:forEach>
        <button type="submit" name="action" value="answer">回答</button>
    </form>
    <div class="grid-container">
    <img class="girl" src="http://localhost:8080/Portfolio2/images/girl1.gif">
    <img class="girl" src="http://localhost:8080/Portfolio2/images/girl2.gif">
    </div>
</body>
</html>