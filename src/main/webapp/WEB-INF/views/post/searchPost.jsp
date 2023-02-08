<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/css/allboard.css">
    <script src="${path}/js/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function() {
            const currentPage = ${responseDTO.pageInfo.currentPage};
            const prev = ${responseDTO.pageInfo.prev};
            const next = ${responseDTO.pageInfo.next};

            // prev next 활성-비활성 및 링크설정
            if (!prev) {
                $("#pagingPrevious").attr("class", "page-item disabled");
            }
            if (!next) {
                $("#pagingNext").attr("class", "page-item disabled");
            }

            $("#pagingPrevious").on("click", function () {
                location.replace("/post/smartSearch/" + ${responseDTO.pageInfo.startPage - 1} + "?searchTitle=${param.searchTitle}&searchWriter=${param.searchWriter}&searchPriceMin=${param.searchPriceMin}&searchPriceMax=${param.searchPriceMax}")
            });
            $("#pagingNext").on("click", function () {
                location.replace("/post/smartSearch/" + ${responseDTO.pageInfo.endPage + 1} + "?searchTitle=${param.searchTitle}&searchWriter=${param.searchWriter}&searchPriceMin=${param.searchPriceMin}&searchPriceMax=${param.searchPriceMax}")
            });

            // 현재 페이지 색 표시
            for (let i = 0; i < 10; i ++) {
                if ($("#pageNumLink" + i).html() == currentPage) {
                    $("#pageNumList" + i).attr("class", "page-item active");
                }
            }
        });
    </script>
</head>
<body>
<%-- 검색 --%>
<div class="smart-search-container">
<form class="smart-search-box mb-4" action="/post/smartSearch/1" method="get">
    <table class="smartSearchBox-Table">
        <tr>
            <td>제목</td>
        </tr>
        <tr>
            <th><input id="searchTitle" type="text" name="searchTitle" placeholder="검색"></th>
        </tr>

        <tr>
            <td>작성자</td>
        </tr>
        <tr>
            <th><input id="searchWriter" type="text" name="searchWriter" placeholder="검색"></th>
        </tr>


        <tr>
            <td>가격</td>
        </tr>
        <tr>
            <th><input id="searchPriceMin" type="number"
                       name="searchPriceMin" placeholder="최소가격(₩)" step="500"></th>
        </tr>
        <tr>
            <th><input type="number" name="searchPriceMax"
                       id="searchPriceMax" placeholder="최대가격(₩)" step="500"></th>
        </tr>

        <tr>
            <th><input class="smart-search-button" type="submit" value="검색"></th>
        </tr>
    </table>
</form>
</div>



<%-- 페이징 --%>
<nav aria-label="...">
    <ul class="pagination pagination-lg">
        <li id="pagingPrevious" class="page-item">
            <a class="page-link" href="#" tabindex="-1">Previous</a>
        </li>

        <c:set var="counter" value="0" />
        <c:forEach begin="${responseDTO.pageInfo.startPage}" end="${responseDTO.pageInfo.endPage}" varStatus="vs">
            <li id="pageNumList${counter}" class="page-item"><a id="pageNumLink${counter}" class="page-link" href="/post/smartSearch/${responseDTO.pageInfo.startPage + counter}?searchTitle=${param.searchTitle}&searchWriter=${param.searchWriter}&searchPriceMin=${param.searchPriceMin}&searchPriceMax=${param.searchPriceMax}">
                    ${responseDTO.pageInfo.startPage + counter}</a></li>
            <c:set var="counter" value="${counter + 1}" />
        </c:forEach>

        <li id="pagingNext" class="page-item">
            <a class="page-link" href="#">Next</a>
        </li>
    </ul>
</nav>


<%-- 전체 게시물 --%>
<div class="pricing-box-container">
<c:forEach items="${responseDTO.posts}" var="each">
    <div class="pricing-box text-center">
        <h5>${each.category}</h5>
        <p class="price">${each.title}</p>
        <ul class="features-list">
            <li><strong>가격</strong> ${each.price}</li>
            <li><strong>${each.createDate}</strong></li>
        </ul>
        <button id="chatbtn" class="btn-primary"><a href="/post/detail/${each.postId}">Get Started</a></button>
    </div>

</c:forEach>
</div>

</body>
</html>