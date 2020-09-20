<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <SCRIPT type="text/javascript">
        function fn_emailCheck() {
            console.log("fn_emailCheck Called")
            const form = {
                email: $("#email").val()
            }

            $.ajax({
                url : "emailCheck",
                type : "POST",
                dataType: 'json',
                data : form,

                success : function(data){
                    if(data) {
                        $("#emailCheck").attr("value", "Y");
                        const emailInput = document.getElementById('email');
                        emailInput.readOnly = true;

                        const btn = document.getElementById('emailCheck');
                        btn.disabled = true;

                        alert("사용가능한 아이디입니다.");
                    } else {
                        alert("중복된 아이디입니다.");
                    }
                }
            })
        }
    </SCRIPT>

    <!-- 중복 확인, 비밀번호 일치 확인용 함수 -->
    <script type="text/javascript">
        $("form").submit(function() {
            if($("emailCheck").getAttribute("value") === "N") {
                alert("이메일 중복 확인을 수행하세요.")
            }
        })
    </script>

    <!-- VAL 테스트 -->
    <script type="text/javascript">
        function test() {
            console.log($("input_name").val())
            console.log($("input_pwd").val())
        }
    </script>
</head>
<body>
<div>
    <h1>회원 가입 폼</h1>
    <div>
        <form method="post" action="join">
            <div>
                <label>이름</label>
                <input type="text" name="name" required>
            </div>
            <div>
                <label class="control-label" for="email">이메일</label>
                <input class="form-control" type="text" id="email" name="email" required/>
                <button class="idChk" type="button" id="emailCheck" onclick="fn_emailCheck();" value="N">중복확인</button>
            </div>
            <div>
                <label>비밀번호</label>
                <input type="password" name="password" required>
            </div>
            <div>
                <label>비밀번호 확인</label>
                <input type="password" name="password" required>
            </div>
            <div>
                <button class="idChk" type="button" onclick="test();" value="N">TEST</button>
                <input type="submit" value="회원가입">
            </div>
        </form>
    </div>
</div>

</body>



</html>