<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>join</title>
    <link rel="stylesheet" href="${path}/css/test.css">
    <script src="${path}/js/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {

            $("#writeBTN").on("click", function (e) {
                const title = $("#title").val();
                const contents = $("#contents").val();
                const price = $("#price").val();
                const category = $("#category").val();
                const userId = $("#writer").val();

                console.log(title + " " + contents + " " + category + " " + price);

                // $.ajax({
                //     type: "POST",
                //     url: "/community/writeProcess",
                //     dataType: "json",
                //     contentType: "application/json; charset=UTF-8",
                //     data: JSON.stringify({
                //         'title': title,
                //         'contents': contents,
                //         'price': price,
                //         'category': category,
                //         'userId': userId
                //     }),
                //
                //     success: function (res) {
                //         // let boardContents = res.responseEntity.body.contents;
                //         // let boardJob = res.responseEntity.body.job;
                //         console.log("그냥 res : ", res);
                //
                //         console.log("JSON res : " + JSON.stringify(res));
                //
                //         // alert("JSON res 에서 꺼내기 : " + JSON.stringify(res.statusCode)); //undefined
                //         console.log("JSON res 에서 꺼내기 : " + JSON.stringify(res.category));
                //         console.log("JSON res 에서 꺼내기 : " + JSON.stringify(res.contents));
                //         console.log("JSON res 에서 꺼내기 : " + JSON.stringify(res.price));
                //
                //     } //success
                //
                // }); //ajax

                fetch("/community/writeProcess",{
                    method: "POST",
                    headers: {"content-type" : "application/json"},
                    body: JSON.stringify({
                        'title': title,
                        'contents': contents,
                        'price': price,
                        'category': category,
                        'userId': userId
                    })
                })
                    .then(response => response.json())
                    .then(result => {
                        console.log(result);
                        console.log(result.title);
                    })

            }); //onClick
        }); // onload
    </script>
</head>

<body>

<form id="writeForm">
    제목 : <input id="title" name="title" type="text"> <br>
    내용 : <textarea id="contents" name="contents"></textarea> <br>
    카테고리 : <input id="category" name="category" type="text"> <br>
    가격 : <input id="price" name="price" type="number"> <br>
    <input id="writer" name="userId" type=hidden value="402880ab861642ee01861645eb220000">
    <input id="writeBTN" type="button" value="작성">
</form>

</body>

</html>