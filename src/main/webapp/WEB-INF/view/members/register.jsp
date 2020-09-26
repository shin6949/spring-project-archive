<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <SCRIPT type="text/javascript">
        function fn_emailCheck(obj) {
            if(obj.email.value === "") {
                alert("이메일을 입력하세요.");
                return false
            }

            const form = {
                email: obj.email.value
            }

            $.ajax({
                url : "emailCheck",
                type : "POST",
                dataType: 'json',
                data : form,

                success : function(data){
                    if(data) {
                        obj.emailChk.setAttribute("value", "Y")
                        obj.email.readOnly = true;
                        obj.emailChk.disabled = true;

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
        function isValidate(obj) {
            if(obj.emailChk.getAttribute("value") === "N") {
                alert("이메일 중복 확인을 수행하세요.")
                return false;
            }

            if(obj.password.value !== obj.password_re.value) {
                obj.password.focus()
                alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.")
                return false;
            }

            return true;
        }
    </script>

</head>
<body>
<div>
    <h1>회원 가입 폼</h1>
    <div>
        <form method="post" action="insert" onsubmit="return isValidate(this);">
            <div>
                <label>이름</label>
                <input type="text" name="name" required>
            </div>
            <div>
                <label>이메일</label>
                <input type="text" name="email" required/>
                <button name="emailChk" type="button" onclick="fn_emailCheck(this.form);" value="N" >중복확인</button>
                <label>아이디로 사용됩니다.</label>
            </div>
            <div>
                <label>비밀번호</label>
                <input type="password" name="password" required>
            </div>
            <div>
                <label>비밀번호 확인</label>
                <input type="password" name="password_re" required>
            </div>
            <div>
                <input type="submit" value="회원가입" >
            </div>
        </form>
    </div>
</div>

</body>



</html>