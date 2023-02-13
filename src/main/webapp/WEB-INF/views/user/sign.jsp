<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ko">
<html class="html">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <meta http-equiv="X-UA-Compatible" content="ie-edge"/>
    <meta name="keywords" content="Yulian Brito, Yulian, Brito, Frontend Developer, Sign Up Sign In 1"/>
    <meta name="author" content="Yulian Brito"/>
    <title>Sign Up / Sign In 1</title>
    <link rel="stylesheet" href="/css/test.css"/>
<%--    <link rel="stylesheet" href="/css/signin.css"/>--%>
    <script type="text/javascript" src="/js/test.js"></script>
</head>

<body class="body">

<!-- Choose Form -->

<section class="form-container">
    <span id="arrowClick" class="form-container__arrow">
        <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
    </span>
    <div class="overlay"></div>
    <div class="choose-form">
        <div class="title">
            <h1 class="title__h1">Welcome!<br>Start to free</h1>
        </div>
        <div class="buttons">
            <a id="signUp" class="buttons__signup" href="#">Sign up</a>
            <a id="login" class="buttons__signup buttons__signup--login" href="#">Login</a>
        </div>
    </div>

    <!-- Login Form -->

    <div class="login-form">
        <div class="form-wrapper">
            <form method="post" action="/api/auth/signin">
<%--               // 이름--%>
                <label class="form-wrapper__label" for="login-email">Email</label>
                <input id="login-email" class="form-wrapper__input" type="text" placeholder="email" name="email" required>
<%--                // 비밀번호--%>
                <label class="form-wrapper__label" for="login-password">Password</label>
                <input id="login-password" class="form-wrapper__input" type="password" placeholder="password" name="password" pattern=".{3,}" title="Password must contain at least 3 characters" required>

                <a class="login-form__forgot-password" href="#">Forgot password?</a>
                <button class="buttons__signup buttons__signup--login-form" type="submit" href="#">Login</button>
            </form>

        </div>
    </div>

    <!-- Register Form -->

    <div class="login-form login-form--register">
        <div class="form-wrapper">
            <form method="post" action="/api/auth/signup">
<%--               // 이메일--%>
                <label class="form-wrapper__label" for="signup-email">Email</label>
                <input id="signup-email" class="form-wrapper__input" type="text" style="ime-mode:disalbed;"
                       placeholder="example@hotmail.com" name="email" required>
<%--               // 이름--%>
                <label class="form-wrapper__label" for="signup-username">Username</label>
                <input id="signup-username" class="form-wrapper__input" type="text" placeholder="Username"
                       name="name" required>
<%--               // 비밀번호--%>
                <label class="form-wrapper__label" for="signup-password">Password</label>
                <input id="signup-password" class="form-wrapper__input" type="password" placeholder="Password" name="password" pattern=".{3,}" title="Password must contain at least 3 characters" required>
<%--               // 닉네임--%>
                <label class="form-wrapper__label" for="signup-nickname">Nickname</label>
                <input id="signup-nickname" class="form-wrapper__input" type="text" placeholder="nickname" name="nickname" required>
<%--                // 나이--%>
                <label class="form-wrapper__label" for="signup-age">Age</label>
                <input id="signup-age" class="form-wrapper__input" type="text" placeholder="age" name="age" required>

                <button id="form-sigin-up-fin" class="buttons__signup buttons__signup--sign-up-form" type="submit">Sign up</button>

            </form>
        </div>
    </div>
</section>

</body>
</html>