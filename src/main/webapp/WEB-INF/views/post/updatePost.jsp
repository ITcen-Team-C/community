<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ page import ="store.itcen.community.postapi.entity.Category" %>

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/updatepost.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/header_footer.css"/>

<!DOCTYPE html>
<html>
<head>
    <title>update</title>
    <script src="${path}/js/jquery-3.6.0.min.js"></script>

    <script>
        $(document).ready(function(){
            $("#updateBTN").on("click", function (e) {

                const title = $("#title").val();
                const contents = $("#contents").val();
                const price = $("#price").val();
                const category = $("#category").val();
                const nickName = $("#nickName").val();
                // alert(nickName);
                const userId = $("#userId").val();

                console.log(title + " " + contents + " " + category + " " + price);


                fetch('/community/post/update/${responseDTO.postId}',{
                    method: "PATCH",
                    headers: {"content-type" : "application/json"},
                    body: JSON.stringify({
                        'title': title,
                        'contents': contents,
                        'price': price,
                        'category': category,
                        'nickName': nickName,
                        'userId': userId
                    })
                })
                    .then(response => response.json())
                    .then(result => {
                        console.log(result);
                        window.location.href="/post/detail/${responseDTO.postId}"
                    })

            })
        });

    </script>

</head>
<body>

<%@include file="/WEB-INF/views/post/header.jsp"%>

<div class="update">
    <form id="updateForm">

        <select id="category" style="width: 150px;
                    height: 60px;
                    margin-top: 30px;
                    margin-left: 37px;
                    padding-left: 10px;
                    background-color: white;
                    border: none;
                    border-radius: 4px;
                    cursor: pointer;">
            <c:forEach items="${Category.values()}" var="cg">
                <option value="${cg}"
                    <c:if test="${responseDTO.category eq cg.cat}"> selected="selected"</c:if>>
                        ${cg.cat}</option>
            </c:forEach>
        </select>

        <input class="input-price" id="price" name="price" type="number" value="${responseDTO.price}">
        <div class="title-contents">
            <input class="title" id="title" name="title" type="text" value="${responseDTO.title}" > <br>

            <textarea class="contents" id="contents" name="contents" type="text" >${responseDTO.contents}</textarea> <br>
        </div>

        <input id="nickName" name="nickName" type="hidden" value="닉넴">
        <input id="userId" name="userId" type=hidden value="402880d08624b21b018624b2cc790000">
        <input class="send-btn" id="updateBTN" type="button" value="수정">
    </form>
</div>



<%@include file="/WEB-INF/views/post/footer.jsp"%>
</body>
</html>
