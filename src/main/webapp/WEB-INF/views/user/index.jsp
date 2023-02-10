<%--
  Created by IntelliJ IDEA.
  User: PC - 08
  Date: 2023-02-07
  Time: 오후 4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>무엇이든 물어보세요</title>
</head>
<body>
<header>
    <div class="header_container">
        <div class="logo_container">
            <a href="http://www.naver.com">BLOG</a>
        </div>

        <div class="login_container">
            <ul class="login">
                <li class="menu_login"><a class="menu_title" href="http://localhost:8080/signin">로그아웃</a></li>
            </ul>
        </div>
    </div>
    </div>
</header>



무엇이든 물어보세요
<br>
환영메세지

<br>
소개
<br>
<form method="get" action="/post/1">
    <input type="submit" class="bt_start" value="시작하기">
</form>



<script>
    const ACCESS_TOKEN = '${token}';
    // console.log(ACCESS_TOKEN);

    window.localStorage.setItem("ACCESS_TOKEN", ACCESS_TOKEN);


    const item = window.localStorage.getItem("ACCESS_TOKEN");
    console.log(item);
</script>
</body>
</html>