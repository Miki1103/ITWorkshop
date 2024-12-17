<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>エラー発生画面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

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

    <div class="login-page">
        <h3 class="start align-center">
            予期せぬエラーが発生しました。<br>
            もう一度回答を始めてください。
        </h3>
   <a href="<%= request.getContextPath() %>/GameController?action=startGame" class="btn">ゲームに戻る</a>
    </div>

    <div class="grid-container2">
    <img class="girl" src="http://localhost:8080/Portfolio2/images/girl3.gif">
    </div>

</body>
</html>