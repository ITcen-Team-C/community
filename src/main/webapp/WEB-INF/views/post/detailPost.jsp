<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/detailpost.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/modal.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/header_footer.css"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>detail</title>
<%--    <link rel="stylesheet" href="${path}/css/test.css">--%>


    <script src="${path}/js/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {

            $("#deleteBTN").click(function (e){
                $(".modal").fadeIn();
            });

            $("#modal, .close").on('click',function (){
                $(".modal").fadeOut();
            })

            $("#noBTN").on('click',function (){
                $(".modal").fadeOut();
            })

            $("#goListBTN").on('click',function (){
                console.log("elifejlw")
                window.location.href="/post/1"
            })


            const $form=document.getElementById("mod-del");

            $("#okBTN").on("click",function (e){
                console.log("${userId}")
                console.log("${responseDTO.userId}")

                if ("${userId}"=="${responseDTO.userId}"){
                    $form.setAttribute("action", "/post/delete/${responseDTO.postId}");
                    $form.submit();
                }
                else if(${userId eq ""}){
                    console.log("로그인 후 이용 바랍니다.")
                    window.alert("로그인 후 이용 바랍니다.")
                    window.location.href="/login"
                }
                else{
                    console.log("작성자만 삭제 가능합니다.")
                    window.alert("작성자만 삭제 가능합니다.")
                    $(".modal").fadeOut();

                }
            })



            $("#updateBTN").on("click",function (){

                <%--window.location.href="/post/update/${responseDTO.postId}"--%>
                console.log("${userId}")
                console.log("${responseDTO.userId}")

                if ("${userId}"=="${responseDTO.userId}"){
                    $form.setAttribute("action", "/post/update/${responseDTO.postId}");
                    $form.submit();
                }
                else if(${userId eq ""}){
                    console.log("로그인 후 이용 바랍니다.")
                    window.alert("로그인 후 이용 바랍니다.")
                    window.location.href="/login"
                }
                else{
                    console.log("작성자만 수정 가능합니다.")
                    window.alert("작성자만 수정 가능합니다.")
                }

            })








        });
    </script>


</head>

<%--http://localhost:8080/post/detail/402880d0862a376b01862a37c3820000--%>

<body>


<%@include file="/WEB-INF/views/post/header.jsp"%>


<div class="detail">
    <div style="margin-left: 40px; padding-top: 30px; font-size: 15px; font-weight: 700;"> >>>  ${responseDTO.category}</div>

    <div class="title-contents">

        <h1>
            ${responseDTO.title}
        </h1>

        <div class="contents">
            ${responseDTO.contents}
        </div>

        <div class="price-box">
            <div class="price">
                ${responseDTO.price} 원
            </div>
            <input class="solve-btn" id="solveBTN" type="button" value="질문 해결하기">
        </div>

        <div class="ul-modify-delete">
            <form id="mod-del">
                <input class="to-list"  id="goListBTN" type="button" value="목록">
                <input class="post-modify"  id="updateBTN" type="button" value="수정">
                <input class="post-delete"  id="deleteBTN" type="button" value="삭제">

            </form>


        </div>


        <!-- 채팅버튼 -->
        <div class="product-detail-chatbutton">
            <form id="chatSubmit_form" action="/chatMessage" method="GET">
                <a id="chatLink" href="javascript:{}" onclick="chatSubmit()">
                    <input type="hidden" name="buyerId" value="${userId}" />
                    <input type="hidden" name="sellerId" value="${responseDTO.userId}" />
                    <input type="hidden" name="post_id" value="${responseDTO.postId}" />
                    <input type="hidden" name="post_title" value="${responseDTO.title}" />

                    <button class="chat-on-button" id="btn_chat">채팅하기</button>
                </a>
            </form>
        </div>

    </div>






</div>


</div>

<div class="modal">
    <div class="modal_content">
        <div id="modalCloseBTN" class="close">x</div>
        <br>
        <div class="desc">삭제하시겠습니까?</div><br>

        <input class="delete-no" id="noBTN" type="button" value="취소">
        <input class="delete-ok"  id="okBTN" type="button" value="삭제">

    </div>
</div>

<%@include file="/WEB-INF/views/post/footer.jsp"%>

<script>
    // 채팅 data submit
    function chatSubmit(e) {
        document.getElementById('chatSubmit_form').submit();
    }
</script>

</body>

</html>