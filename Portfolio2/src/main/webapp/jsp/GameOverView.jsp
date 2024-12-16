<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ゲームオーバー画面</title>
 
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
<body>
<div class="game-main">
    <h1>ゲームオーバー</h1>
    <p>残念ですが、スコアが基準に達しませんでした。</p>
    <p>正解数: ${score} / ${total}</p>
    <p>再挑戦して、より高いスコアを目指しましょう！</p>
     <a href="/Portfolio2/jsp/LogoutSuccess.jsp">ログアウト</a>
</div>
</body>
</html>
