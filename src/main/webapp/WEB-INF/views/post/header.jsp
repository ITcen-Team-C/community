<%@ page import="org.springframework.web.util.WebUtils" %>
<%@ page import="store.itcen.community.security.TokenProvider" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script src="${path}/js/jquery-3.6.0.min.js"></script>
<script>

    $(document).ready(function(){
        <%
        TokenProvider tokenProvider = new TokenProvider();

        String userId = "";
        if (WebUtils.getCookie(request,"token")!=null){
            Cookie tokenCookie = WebUtils.getCookie(request, "token");
            String token = tokenCookie.getValue();
//            userId = tokenProvider.validateAndGetUserId(token);
//            pageContext.setAttribute("userId", userId);
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

            // console.log("로그인/로그아웃 버튼이 눌렸어요~!")
            if($(this).val()=="로그아웃"){
                console.log("로그인이되어있는데?")
                if(confirm("로그아웃 하시겠습니까?")==true){
                    window.location.href="/api/auth/signout"
                    console.log("로그아웃완")

                }else {
                    return ;
                }


            }
            else{
                window.location.href="/"
            }


        })
        $("#toSignUpBTN").on('click',function (){
            window.location.href="/"
        })
        $("#toWriteBTN").on('click',function (){
            <%
            if (WebUtils.getCookie(request,"token")!=null){

            %>
                window.location.href="/post/write"
            <%
            }else {

            %>
            alert("로그인 후 이용 가능합니다.");
            <%
            }
            %>
        })
        $("#toChatBTN").on('click',function (){
            window.location.href="/chatList"
        })



        // 채팅 알림

        const sessionId = '${userId}';

        // if (sessionId != "") {
        //     getUnread();
        //     getInfiniteUnread();
        // }

        function getUnread() {
            $.ajax({
                url: "/chatUnreadAlert/ajax",
                type: "POST",
                data: JSON.stringify({
                    sessionId: sessionId}) ,
                dataType: "json",
                contentType: "application/json",
                success: function(result) {
                    if (result >= 1) {
                        showUnread(result);
                    } else {
                        showUnread('');
                    }
                } //success
            });  //ajax
        } // getUnread

        function getInfiniteUnread() {
            setInterval(() => {
                getUnread();}, 2000);
        }

        function showUnread(result) {
            $('#messageAlert').html(result);
        }




        console.log('${userId}');




    });



</script>

<div class="header" >
    <div id="toListBTN" class="header-title">AnyQ</div>


    <%--        <span class="menu-icon"><a href="/chatList"><img src="${path}/pictures/chatting.png" alt=""></a></span>--%>
    <%--        <span class="menu-title close"><a href="/chatList">채팅리스트</a></span>--%>

    <c:if test="${!empty userId}">
    <div class="to-chat">
    <a href="/chatList"><img src="/pictures/notice.png" width="23">채팅 &nbsp; <span id="messageAlert" style="color:orange; font-weight:700;"></span></a>
    </div>
    </c:if>
<%--    <input type="button" id="toChatBTN" class="to-chat" value="채팅리스트">--%>
    <input type="button" id="toLoginBTN" class="to-login" value="로그인">
    <input type="button" id="toSignUpBTN"class="to-signup" value="회원가입">
    <input type="button" id="toWriteBTN"class="to-write" value="글쓰기">



</div>
