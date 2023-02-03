<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>로그인</title>

    <style>
        #app {
            margin: auto;
            margin-top: 60px;
            width: 60%;
            border: 3px solid #8ac007;
            padding: 10px;
        }

        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<h1>Login</h1>
<div id="app">
    <table>
        <tr>
            <td>아이디</td>
            <td>
                <input type="text" id="id" placeholder="아이디" size="20" />
            </td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td>
                <input type="text" id="pwd" placeholder="비밀번호" size="20" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="button" id="login">로그인</button>
                <a href="account.html">회원가입</a>
            </td>
        </tr>
    </table>
</div>

<script>


    // 로그인시 입력한 아이디와 비밀번호를 넘겨준다.
    // 서버단의 로그인 함수가 MemberDto를 반환하기 때문에 프론트엔드단에서 JSON 형태로 넘어온다.
    // 아무것도 입력하지 않으면 JSON의 각 Key에 대한 Value가 비어있을 것이기 때문에 조건식으로 검사해준다.
    // 로그인이 처리되어 정상적으로 넘어왔다면 sessionStorage에 로그인 정보를 저장해서 다른 페이지로 넘겨줄 것이다.
    $(document).ready(function() {
        $("#login").click(function() {
            $.ajax({
                url: "http://localhost:3000/login",
                type: "POST",
                data: {
                    id: $("#id").val(),
                    pwd: $("#pwd").val()
                },
                success: function(json) {
                    // alert('success');
                    // alert(JSON.stringify(json));
                    if(json === "") {
                        alert("아이디 또는 비밀번호를 확인하세요.");
                        $("#id").val("");
                        $("#pwd").val("");
                    } else {
                        // 로그인 정보를 세션에 저장
                        sessionStorage.setItem("login", JSON.stringify(json));
                        alert(`${json.name}님 환영합니다!`);
                        location.href = "../boards/boards.html";
                    }
                },
                error: function() {
                    alert('error');
                }
            });
        });
    });

</script>
</body>
</html>