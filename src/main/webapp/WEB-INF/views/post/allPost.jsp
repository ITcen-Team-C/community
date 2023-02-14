<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>무몰보</title>
    <link rel="shortcut icon" type="image/x-icon" href="${path}/pictures/monster.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/css/allboard.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/header_footer.css"/>
    <script src="${path}/js/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function () {
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
                location.replace("/post/" + ${responseDTO.pageInfo.startPage - 1})
            });
            $("#pagingNext").on("click", function () {
                location.replace("/post/" + ${responseDTO.pageInfo.endPage + 1})
            });

            console.log("currentPage: " + currentPage);

            // 현재 페이지 색 표시
            for (let i = 0; i < 10; i++) {
                if ($("#pageNumLink" + i).html() == currentPage) {
                    $("#pageNumList" + i).attr("class", "page-item active");
                }
            }



            // hover 시 버튼
            // $(".price-box").mouseover(function () {
            //     console.log("mouseover");
            //     $(".btn-primary").attr("style", "display:inline");
            // });



        });
    </script>
</head>
<body>

<%@include file="/WEB-INF/views/post/header.jsp"%>



<div class="allPostContainer">
    <%-- 검색 --%>
    <div class="smart-search-container">
        <form class="smart-search-box mb-4" action="/post/smartSearch/1" method="get">
            <table class="smartSearchBox-Table">
                <tr>
                    <td>카테고리</td>
                </tr>
                <tr>
                    <th>
                        <select id="searchCategory" name="searchCategory">
                            <option>ALL</option>
                            <option>BACKEND</option>
                            <option>FRONTEND</option>
                            <option>DATABASE</option>
                            <option>AWS</option>
                        </select>
                    </th>
                </tr>

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

    <%-- 전체 게시물 + 페이징 --%>
    <div class="main-content">
        <div class="pricing-box-container">
            <c:forEach items="${responseDTO.posts}" var="each">

                <!-- 날짜 몇일 전으로 변환 -->
                <fmt:parseDate value="${each.createDate}" var="uploadDate" pattern="yyyy-MM-dd"/>
                <c:set var="current" value="<%=new java.util.Date()%>"/>
                <fmt:formatDate value="${current}" pattern="yyyy-MM-dd" var="currentForm"/>
                <fmt:parseDate value="${currentForm}" var="now" pattern="yyyy-MM-dd"/>

                <fmt:parseNumber value="${ (now.time - uploadDate.time)/(1000*60*60*24)}" integerOnly="true"
                                 var="dateDiff">
                </fmt:parseNumber>

                <c:set var="dateDiffShow" value="${dateDiff}일전"/>

                <c:if test="${dateDiffShow == '0일전'}">
                    <c:set var="dateDiffShow" value="오늘"/>
                </c:if>


                <div class="pricing-box text-center">
                    <a class="allboard-a" href="/post/detail/${each.postId}">
                    <h5><strong>${each.category}</strong></h5>
                    <p id="allboard-title" class="price">${each.title}</p>
                    <ul class="features-list">
                        <li><strong>가격</strong> ${each.price}</li>
                        <li><strong>작성자</strong> ${each.nickName}</li>
                        <li><strong>${dateDiffShow}</strong></li>
                    </ul>
                    <button class="btn-primary"><a class="allboard-a" href="/post/detail/${each.postId}">답변하러 가기</a>
                    </button>
                    </a>
                </div>
            </c:forEach>
            <%--PricingBox Container--%>
        </div>


        <%-- 페이징 --%>

        <nav aria-label="...">
            <ul class="pagination pagination-lg">
                <li id="pagingPrevious" class="page-item">
                    <a class="page-link" href="#" tabindex="-1">Previous</a>
                </li>

                <c:set var="counter" value="0"/>
                <c:forEach begin="${responseDTO.pageInfo.startPage}" end="${responseDTO.pageInfo.endPage}"
                           varStatus="vs">
                    <li id="pageNumList${counter}" class="page-item"><a id="pageNumLink${counter}" class="page-link"
                                                                        href="/post/${responseDTO.pageInfo.startPage + counter}">${responseDTO.pageInfo.startPage + counter}</a>
                    </li>
                    <c:set var="counter" value="${counter + 1}"/>
                </c:forEach>

                <%--        <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
                <%--        --%>
                <%--        <li class="page-item active">--%>
                <%--            <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>--%>
                <%--        </li>--%>
                <%--        --%>
                <%--        <li class="page-item"><a class="page-link" href="#">3</a></li>--%>


                <li id="pagingNext" class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </nav>
    </div>


    <%-- allPostContainer  --%>
</div>


<%--<div id="pagingDiv">--%>

<%--    <span id="pagingPrevious2" style="cursor:pointer;" >◁</span>--%>
<%--    <c:set var="counter" value="0" />--%>
<%--    <c:forEach begin="${responseDTO.pageInfo.startPage}" end="${responseDTO.pageInfo.endPage}" varStatus="vs">--%>
<%--        <a href="/post/${responseDTO.pageInfo.startPage + counter}"> ${responseDTO.pageInfo.startPage + counter} </a>--%>
<%--    <c:set var="counter" value="${counter + 1}" />--%>
<%--    </c:forEach>--%>
<%--    <span id="pagingNext2" style="cursor:pointer">▷</span>--%>

<%--</div>--%>


<%@include file="/WEB-INF/views/post/footer.jsp"%>

</body>
</html>