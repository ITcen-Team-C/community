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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>무엇이든 물어보세요</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

<header>
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">무물보</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link1" href="http://localhost:8080/post/write">질문하기</a>
                    <a class="nav-link2" href="http://localhost:8080/post/1">질문찾기</a>
                    <a class="nav-link3" href="http://localhost:8080">로그아웃</a>

                </div>
            </div>
        </div>
    </nav>
</header>

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