<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>新規登録画面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	<link rel="icon" href="http://localhost:8080/Portfolio2/images/logo2.png" type="image/png">
    <link rel="stylesheet" href="http://localhost:8080/Portfolio2/css/style.css">
</head>
<body>
    <h1 class="align-center">
        <a href="/Portfolio2/index.jsp"><img class="logo" src="http://localhost:8080/Portfolio2/images/logo1.png" alt="ITパスポート試験攻略ゲーム">
            </a>
    </h1>

    <h2 class="start align-center">
        ユーザー名とパスワード入力後に確定ボタンを押してください<br>
    </h2>

    <div class="login-page">
    <form action="/Portfolio2/Register" method="post">
        ユーザー名:<input type="text" name="username"><br>
        パスワード:<input type="password" name="password"><br>
        <input type="submit" value="確定する" class="btn">
    </form>
    </div>

    <div class="grid-container">
    <img class="girl" src="http://localhost:8080/Portfolio2/images/girl1.gif">
    <img class="girl" src="http://localhost:8080/Portfolio2/images/girl2.gif">
    </div>

</body>
</html>
