<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ko">

<head>
    <!--          meta 선언          -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--          link 선언          -->
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/style_login.css">

    <!--          script 선언          -->
    <script src="https://kit.fontawesome.com/e1bd1cb2a5.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>

    <script src="../js/jquery-3.6.0.min.js"></script>

    <title>
        community
    </title>
</head>

<body>
<header>
    <div class="header_container">
        <div class="logo_container">
            <a href="http://localhost:8080">큐앤에이</a>
        </div>

            <div class="login_container">
                <ul class="login">
                    <li class="menu_join"><a class="menu_title" href="http://localhost:8080/join">회원가입</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>

<div class="login_containers">
    <h2>
        로그인
    </h2>
    <form method="post" action="/api/auth/signin">
        <h3>이메일</h3>
        <div class="loginID">
            <input type="text" class="input" placeholder="이메일" name="email" maxlength="20">
        </div>
        <h3>비밀번호</h3>
        <div class="loginPassword">
            <input type="password" class="input" placeholder="비밀번호" name="password" maxlength="20">
        </div>
        <input type="submit" class="bt_login" value="로그인">
    </form>
</div>

<footer>
    <div class="footer_container">
        <div class="footA">
            kim
        </div>
        <div class="footB">
            Copyright © kim All Rights Reserved.
        </div>
    </div>
</footer>

<script>

    const message = '${message}';
    if (message) {
        alert(message);
    }
</script>

</body>

</html>
