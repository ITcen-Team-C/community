<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/detailpost.css"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>detail</title>
    <link rel="stylesheet" href="${path}/css/test.css">
    <script src="${path}/js/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
        });
    </script>

</head>

<%--http://localhost:8080/post/detail/402880d0862a376b01862a37c3820000--%>

<body>

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
            <div class="post-modify">
                수정
            </div>
            <div class="post-delete">
                삭제
            </div>
        </div>



    </div>



</div>


</body>

</html>