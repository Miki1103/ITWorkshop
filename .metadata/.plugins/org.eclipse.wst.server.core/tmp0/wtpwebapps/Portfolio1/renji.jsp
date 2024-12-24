<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="掃除機や電子レンジなど、あなたにぴったりな家電を診断できるアプリ">
    
    <title>レンジの診断</title>

     <!-- viewportの指定 -->
     <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- リセットCSS -->
    <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">

    <!-- Googleフォント -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Zen+Maru+Gothic:wght@300;400;500;700;900&display=swap" rel="stylesheet">

    <!-- CSS-->
    <link rel="stylesheet" href="http://localhost:8080/Portfolio1/css/style.css">

    <!-- ファビコン -->
     <link rel="icon" type="image.png" href="http://localhost:8080/Portfolio1/images/logo2.png">

</head>

<body>   
    <header class="page-header">
            <h1 class="align-center">
            <a href="/Portfolio1/index.html"><img class="logo" src="http://localhost:8080/Portfolio1/images/logo1.png" alt="家電おすすめアプリ"></a>
            </h1>
        <nav>
        <ul class="main-nav font-japanese">
            <li><a href="/Portfolio1/html/type.html">家電の種類</a></li>
            <li><a href="/Portfolio1/html/menu.html">家電診断</a></li>
            <li><a href="/Portfolio1/html/contact.html">お問い合わせ</a></li>
        </ul>    
        </nav>
    </header>
    
     <h2 class="page-title font-japanese">診断スタート</h2>

     <div class="align-center">
     <p>※必ずどちらかを選択してください</p>
     </div>
     
     <main class="align-center category">
     
     <form action="Renji" method="post">
     
     <p>Q1.温める機能があれば充分？</p>

    <input type="radio" name="q1" value="yes">充分<br>
    <input type="radio" name="q1" value="no">足りない

    <p>Q2.蒸し料理を作ることはある？</p>

    <input type="radio" name="q2" value="yes">ある<br>
    <input type="radio" name="q2" value="no">ない

    <p>Q3.焼き菓子など頻繁に作る？</p>

    <input type="radio" name="q3" value="yes">作る<br>
    <input type="radio" name="q3" value="no">作らない

    <p>Q4.操作性をかなり重視する？</p>

    <input type="radio" name="q4" value="yes">一番重要<br>
    <input type="radio" name="q4" value="no">特に気にしない

    <p>Q5.コンパクトなサイズがいい？</p>

    <input type="radio" name="q5" value="yes">はい<br>
    <input type="radio" name="q5" value="no">特に気にしない<br>

    
    <input type="submit" value="診断結果を見る" class="align-center send-btn">
    
    </form>
    
    </main>

    <footer class="page-footer">
        <div class="copyright">
            <small>&copy;2024 Kaden Shindan</small>
        </div>
    </footer>

</body>
</html>