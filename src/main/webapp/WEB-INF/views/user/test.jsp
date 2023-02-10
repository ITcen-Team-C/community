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
                <label class="form-wrapper__label" for="login-username">Username</label>
                <input id="login-username" class="form-wrapper__input" type="text" placeholder="Username" name="username" required>
                <label class="form-wrapper__label" for="login-password">Password</label>
                <input id="login-password" class="form-wrapper__input" type="password" placeholder="Password" name="password" pattern=".{3,}" title="Password must contain at least 3 characters" required>
                <a class="login-form__forgot-password" href="#">Forgot password?</a>
                <button class="buttons__signup buttons__signup--login-form" type="submit" href="#">Login</button>
            </form>

            <!-- Social-Media -->

<%--            <div class="social-media">--%>
<%--                <h6 class="title__h2">Or connect with</h6>--%>
<%--                <a class="buttons__signup buttons__signup--social" href="#"><i class="fab fa-facebook-f" aria-hidden="true"></i>&nbsp;facebook</a>--%>
<%--                <a class="buttons__signup buttons__signup--social" href="#"><i class="fab fa-google" aria-hidden="true"></i>&nbsp;google</a>--%>
<%--            </div>--%>
        </div>
    </div>

    <!-- Register Form -->

    <div class="login-form login-form--register">
        <div class="form-wrapper">
            <form method="post" action="/api/auth/signup">
                <label class="form-wrapper__label">Email</label>
                <input type="text" class="input" style="ime-mode:disabled;" placeholder="이메일" name="email" title="이메일" maxlength="20">

                <label class="form-wrapper__label">Username</label>
                <input type="text" class="input" placeholder="이름" name="name" title="이름" maxlength="20">

                <label class="form-wrapper__label">Password</label>
                <input type="password" class="input" placeholder="비밀번호" name="password" title="비밀번호" maxlength="20">

                <label class="form-wrapper__label">Nickname</label>
                <input type="text" class="input" placeholder="별명" name="nickname" title="별명" maxlength="20">

                <label class="form-wrapper__label">Age</label>
                <input type="text" class="input" placeholder="나이" name="age" title="나이" maxlength="20">

                <button class="buttons__signup buttons__signup--sign-up-form" type="submit">Sign up</button>

            </form>
        </div>
    </div>
</section>

</body>
</html>