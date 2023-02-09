<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ko">

<head>
    <!--          meta 선언          -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--          link 선언          -->
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/style_join.css">

    <!--          script 선언          -->
    <script src="https://kit.fontawesome.com/e1bd1cb2a5.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="../js/script.js"></script>

    <title>
        kim
    </title>


</head>

<body>


<header>

    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Features</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Pricing</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Dropdown link
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>



    <div class="header_container">
        <div class="logo_container">
            <a href="http://localhost:8080">큐앤에이</a>
        </div>

            <div class="login_container">
                <ul class="login">
                    <li class="menu_login"><a class="menu_title" href="http://localhost:8080/login">로그인</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>

<div class="join_container">
    <h2>
        회원 가입
    </h2>
    <form method="post" action="/api/auth/signup">
        <h3>이메일</h3>
        <div class="joinID">
            <input type="text" class="input" style="ime-mode:disabled;" placeholder="이메일" name="email" title="이메일" maxlength="20">
        </div>
        <h3>비밀번호</h3>
        <div class="joinPassword">
            <input type="password" class="input" placeholder="비밀번호" name="password" title="비밀번호" maxlength="20">
        </div>
        <h3>이름</h3>
        <div class="joinName">
            <input type="text" class="input" placeholder="이름" name="name" title="이름" maxlength="20">
        </div>

        <h3>별명</h3>
        <div class="joinName">
            <input type="text" class="input" placeholder="별명" name="nickname" title="별명" maxlength="20">
        </div>

        <h3>나이</h3>
        <div class="joinName">
            <input type="text" class="input" placeholder="나이" name="age" title="나이" maxlength="20">
        </div>



        <input type="submit" class="bt_join" value="회원가입">
    </form>
</div>

<footer>
    <div class="footer_container">
        <div class="footA">
            kim
        </div>
        <div class="footB">
            Copyright © community All Rights Reserved.
        </div>
    </div>
</footer>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>


</body>

</html>