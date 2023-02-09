<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script src="${path}/js/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function(){
        $("#toListBTN").on('click',function (){
            window.location.href="/post/1"
        })
        $("#toLoginBTN").on('click',function (){
            window.location.href="/login"
            // window.location.href="/api/auth/signin"
        })
        $("#toSignUpBTN").on('click',function (){
            window.location.href="/join"
        })
        $("#toWriteBTN").on('click',function (){
            window.location.href="/post/write"
        })
    });



</script>

    <div class="header" >
        <div id="toListBTN" class="header-title">AnyQ</div>

        <input type="button" id="toLoginBTN" class="to-login" value="로그인">
        <input type="button" id="toSignUpBTN"class="to-signup" value="회원가입">
        <input type="button" id="toWriteBTN"class="to-write" value="글쓰기">


    </div>
