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

    <script src="../js/script.js"></script>

    <title>
        Jo's
    </title>
</head>

<body>
<header>
    <div class="header_container">
        <div class="logo_container">
            <a href="../index.jsp">BLOG</a>
        </div>
        <div class="nav_container" id="nav_menu">
            <div class="menu_container">
                <ul class="menu">
                    <li class="menu_1">
                        <a class="menu_title">메뉴 1</a>
                        <ul class="menu_1_content">
                            <li><a class="menu_index" href="#">메뉴 1 - 1</a></li>
                            <li><a class="menu_index" href="#">메뉴 1 - 2</a></li>
                            <li><a class="menu_index" href="#">메뉴 1 - 3</a></li>
                        </ul>
                    </li>
                    <li class="menu_2">
                        <a class="menu_title">메뉴 2</a>
                        <ul class="menu_2_content">
                            <li><a class="menu_index" href="#">메뉴 2 - 1</a></li>
                            <li><a class="menu_index" href="#">메뉴 2 - 2</a></li>
                            <li><a class="menu_index" href="#">메뉴 2 - 3</a></li>
                        </ul>
                    </li>
                    <li class="menu_3">
                        <a class="menu_title">메뉴 3</a>
                        <ul class="menu_3_content">
                            <li><a class="menu_index" href="#">메뉴 3 - 1</a></li>
                            <li><a class="menu_index" href="#">메뉴 3 - 2</a></li>
                            <li><a class="menu_index" href="#">메뉴 3 - 3</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="login_container">
                <ul class="login">
                    <li class="menu_login"><a class="menu_title" href="./login.jsp">로그인</a></li>
                    <li class="menu_join"><a class="menu_title" href="./join.jsp">회원가입</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>

<div class="join_container">
    <h2>
        회원 가입
    </h2>
    <form method="post" action="./join_Action.jsp">
        <h3>아이디</h3>
        <div class="joinID">
            <input type="text" class="input" style="ime-mode:disabled;" placeholder="아이디" name="userID" title="아이디" maxlength="20">
        </div>
        <h3>비밀번호</h3>
        <div class="joinPassword">
            <input type="password" class="input" placeholder="비밀번호" name="userPassword" title="비밀번호" maxlength="20">
        </div>
        <h3>이름</h3>
        <div class="joinName">
            <input type="text" class="input" placeholder="이름" name="userName" title="이름" maxlength="20">
        </div>
        <h3>생년월일</h3>
        <div class="joinDate">
            <input type="date" class="input" placeholder="생년월일" name="userDate">
        </div>
        <h3>성별</h3>
        <div class="joinGender">
            <input type="radio" name="userGender" value="M" title="성별"> 남자
            <input type="radio" name="userGender" value="F" title="성별"> 여자
        </div>
        <input type="submit" class="bt_join" value="회원가입">
    </form>
</div>

<footer>
    <div class="footer_container">
        <div class="footA">
            Jo's
        </div>
        <div class="footB">
            Copyright © community All Rights Reserved.
        </div>
    </div>
</footer>
</body>

</html>