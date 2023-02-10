<%--
  Created by IntelliJ IDEA.
  User: PC - 08
  Date: 2023-02-10
  Time: 오전 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>큐앤에이</title>
    <link rel="stylesheet" href="/css/signIn.css">
</head>
<body>
<a href="http://localhost:8080/signin" class="logo" target="_blank">
    <img src="https://assets.codepen.io/1462889/fcy.png" alt="">
</a>

<div class="section">
    <div class="container">
        <div class="row full-height justify-content-center">
            <div class="col-12 text-center align-self-center py-5">
                <div class="section pb-5 pt-5 pt-sm-2 text-center">
                    <h6 class="mb-0 pb-3"><span>로그인 </span><span>회원가입</span></h6>
                    <input class="checkbox" type="checkbox" id="reg-log" name="reg-log"/>
                    <label for="reg-log"></label>
                    <div class="card-3d-wrap mx-auto">
                        <div class="card-3d-wrapper">
                            <div class="card-front">
                                <div class="center-wrap">
                                    <div class="section text-center">
                                        <h4 class="mb-4 pb-3">로그인</h4>
                                        <form method="post" action="/api/auth/signin">
                                        <div class="form-group">
                                            <input type="email" name="email" class="form-style" placeholder="Your Email" id="email" autocomplete="off">
                                            <i class="input-icon uil uil-at"></i>
                                        </div>
                                        <div class="form-group mt-2">
                                            <input type="password" name="password" class="form-style" placeholder="Your Password" id="password" autocomplete="off">
                                            <i class="input-icon uil uil-lock-alt"></i>
                                        </div>
                                        <input type="submit" class="signin" value="로그인">
                                        </form>
                                        <p class="mb-0 mt-4 text-center"><a href="#0" class="link">Forgot your password?</a></p>
                                        <a href="#" class="btn mt-4">submit</a>
                                    </div>
                                </div>
                            </div>


                            <div class="card-back">
                                <div class="center-wrap">
                                    <div class="section text-center">
                                        <h4 class="mb-4 pb-3">회원가입</h4>
                                        <form method="post" action="/api/auth/signup">


                                        <div class="form-group">
                                            <input type="text" name="logname" class="form-style" placeholder="Your Full Name" id="logname" autocomplete="off">
                                            <i class="input-icon uil uil-user"></i>
                                        </div>
                                        <div class="form-group mt-2">
                                            <input type="email" name="logemail" class="form-style" placeholder="Your Email" id="logemail" autocomplete="off">
                                            <i class="input-icon uil uil-at"></i>
                                        </div>
                                        <div class="form-group mt-2">
                                            <input type="password" name="logpass" class="form-style" placeholder="Your Password" id="logpass" autocomplete="off">
                                            <i class="input-icon uil uil-lock-alt"></i>
                                        </div>

                                        <div class="form-group mt-2">
                                            <input type="nickname" name="nickname" class="form-style" placeholder="Your nickname" id="nickname" autocomplete="off">
                                            <i class="input-icon uil uil-lock-alt"></i>
                                        </div>

                                        <div class="form-group mt-2">
                                            <input type="age" name="age" class="form-style" placeholder="Your age" id="age" autocomplete="off">
                                            <i class="input-icon uil uil-lock-alt"></i>
                                        </div>
                                        <input type="submit" class="signup" value="회원가입">
                                            <a href="#" class="btn mt-4">submit</a>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
