<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>예약하기</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <%-- 진료 과가 변경되었을 때 생기는 이벤트 --%>
    <script>
        function changeDepartmentSelect(){
            const departmentSelect = document.getElementById("select-department");
            // select element에서 선택된 option의 value가 저장된다.
            const selectValue = departmentSelect.options[departmentSelect.selectedIndex].value;
            // select element에서 선택된 option의 text가 저장된다.
            const selectText = departmentSelect.options[departmentSelect.selectedIndex].text;

            const form = {
                departmentNo: selectValue
            }

            $.ajax({
                url : "availabledate",
                type : "POST",
                dataType: 'json',
                data : form,

                success : function(data){
                    if(data) {
                        obj.emailChk.setAttribute("value", "Y")
                        obj.id.readOnly = true;
                        obj.emailChk.disabled = true;

                        alert("사용가능한 아이디입니다.");
                    } else {
                        alert("중복된 아이디입니다.");
                    }
                }
            })
        }
    </script>
</head>

<body>
<h1>예약하기</h1>

<form method="post" action="">
    <label for="select-department"> 진료 과를 선택하세요.
        <select id="select-department" name="department" onchange="changeDepartmentSelect()">
            <c:forEach var="row" items="${departments}">
                <option value="${row.dno}">${row.name}</option>
            </c:forEach>
        </select>
    </label>
    <br>

    <label for="select-date"> 진료 날짜를 선택하세요.
        <select id="select-date" name="date">
            <c:forEach var="row" items="${departments}">
                <option>${row.name}</option>
            </c:forEach>
        </select>
    </label>
    <br>

    <input type="submit" value="예약하기">
</form>
</body>
</html>
