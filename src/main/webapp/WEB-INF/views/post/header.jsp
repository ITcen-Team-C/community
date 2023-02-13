<%@ page import="org.springframework.web.util.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script src="${path}/js/jquery-3.6.0.min.js"></script>
<script>

    $(document).ready(function(){
        <%
        if (WebUtils.getCookie(request,"token")!=null){
        %>
        console.log("토큰이 있음")
        $("#toLoginBTN").val("로그아웃")
        <%
        }
        else{
        %>
        console.log("토큰이 없어!")
        <%
        }
        %>


        $("#toListBTN").on('click',function (){
            window.location.href="/post/1"
        })


        $("#toLoginBTN").on('click',function (){
            // window.location.href="/login"

            console.log("로그인/로그아웃 버튼이 눌렸어요~!")
            <%--    if("<%=token%>"==null){--%>
            <%--        console.log("토큰이없다")--%>
            <%--    }--%>

            <%--$("#toLoginBTN").val("로그아웃")--%>
            // window.location.href="/api/auth/signin"
        })
        $("#toSignUpBTN").on('click',function (){
            window.location.href="/join"
        })
        $("#toWriteBTN").on('click',function (){
            window.location.href="/post/write"
        })
        $("#toChatBTN").on('click',function (){
            window.location.href="/chatList"
        })

    });



</script>

<div class="header" >
    <div id="toListBTN" class="header-title">AnyQ</div>


    <%--        <span class="menu-icon"><a href="/chatList"><img src="${path}/pictures/chatting.png" alt=""></a></span>--%>
    <%--        <span class="menu-title close"><a href="/chatList">채팅리스트</a></span>--%>
    <input type="button" id="toChatBTN" class="to-chat" value="채팅리스트">
    <input type="button" id="toLoginBTN" class="to-login" value="로그인">
    <input type="button" id="toSignUpBTN"class="to-signup" value="회원가입">
    <input type="button" id="toWriteBTN"class="to-write" value="글쓰기">



</div>
