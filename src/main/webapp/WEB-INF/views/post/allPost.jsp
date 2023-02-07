<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>title</title>
    <link rel="stylesheet" href="${path}/css/allboard.css">

    <script src="${path}/js/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            const prev = ${responseDTO.pageInfo.prev};
            const next = ${responseDTO.pageInfo.next};

            if (!prev) {
                $("#pagingPrevious").attr("style", "display:none");
            }
            if (!next) {
                $("#pagingNext").attr("style", "display:none");
            }

            $("#pagingPrevious").on("click", function () {
                location.replace("/post/" + ${responseDTO.pageInfo.startPage - 1})
            });
            $("#pagingNext").on("click", function () {
                location.replace("/post/" + ${responseDTO.pageInfo.endPage + 1})
            });


        });
    </script>
</head>

<body>
<div id="pagingDiv">

    <span id="pagingPrevious" style="cursor:pointer;" >◁</span>
    <c:set var="counter" value="0" />
    <c:forEach begin="${responseDTO.pageInfo.startPage}" end="${responseDTO.pageInfo.endPage}" varStatus="vs">
        <a href="/post/${responseDTO.pageInfo.startPage + counter}"> ${responseDTO.pageInfo.startPage + counter} </a>
    <c:set var="counter" value="${counter + 1}" />
    </c:forEach>
    <span id="pagingNext" style="cursor:pointer">▷</span>

</div>


<%--게시물 정보 : ${responseDTO.posts}--%>


<div class="pricing-box-container">
<c:forEach items="${responseDTO.posts}" var="each">
    <div class="pricing-box text-center">
        <h5>${each.category}</h5>
        <p class="price">${each.title}</p>
        <ul class="features-list">
            <li><strong>가격</strong> ${each.price}</li>
            <li><strong>내용 </strong> ${each.contents}</li>
<%--            <li>${each.createdtime}</li>--%>
        </ul>
        <button id="chatbtn" class="btn-primary"><a href="/post/detail/${each.postId}">Get Started</a></button>
    </div>

</c:forEach>
</div>




</body>

</html>