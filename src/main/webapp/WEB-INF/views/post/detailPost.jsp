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


            $("#okBTN").on("click",function (e){
                fetch('/community/post/delete/${responseDTO.postId}',{
                    method:"DELETE",
                    headers: {"content-type" : "application/json"}
                })
                    .then(response=>
                        response.json)
                    .then(result => {
                        console.log(result);

                        window.location.href="/post/1"
                    })
            })

            $("#updateBTN").on("click",function (){
                window.location.href="/post/update/${responseDTO.postId}"
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
            <input class="post-modify"  id="updateBTN" type="button" value="수정">
            <input class="post-delete"  id="deleteBTN" type="button" value="삭제">

        </div>

    </div>


</div>

<div class="modal">
    <div class="modal_content">
        <p href="" class="close">x</p>
        <br>
        <div class="desc">삭제하시겠습니까?</div><br>

        <input class="delete-no" id="noBTN" type="button" value="취소">
        <input class="delete-ok"  id="okBTN" type="button" value="삭제">

    </div>
</div>

<%@include file="/WEB-INF/views/post/footer.jsp"%>



</body>

</html>