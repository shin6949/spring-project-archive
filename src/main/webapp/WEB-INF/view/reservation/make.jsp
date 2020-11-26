<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>예약페이지</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link href="/resources/reservation_css.css" rel="stylesheet" type="text/css">

    <%-- 진료 과가 변경되었을 때 생기는 이벤트 --%>
    <script>
        function changeDoctorSelectValue() {
            // option 전체 삭제
            document.forms["reservationForm"].doctor.options.length = 0;
            document.forms["reservationForm"].time.options.length = 0;

            const departmentSelect = document.getElementById("select-department");
            const departmentSelectValue = departmentSelect.options[departmentSelect.selectedIndex].value;
            const departmentSelectText = departmentSelect.options[departmentSelect.selectedIndex].text;

            const form = {
                departmentNo: departmentSelectValue,
                departmentText: departmentSelectText,
            }

            $.ajax({
                url: "getdoctorbydepartment",
                type: "POST",
                dataType: 'json',
                data: form,

                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        // select 태그의 option을 정의한다.
                        var op = new Option();
                        op.value = data[i].doctorNo;
                        op.text = data[i].name;

                        // select 태그에 생성 된 option을 넣는다.
                        document.forms["reservationForm"].doctor.add(op);

                        changeTimeSelectValue();
                    }
                },
                error: function (error) {
                    alert('data error');
                }
            })
        }

        function changeTimeSelectValue(){
            // option 전체 삭제
            document.forms["reservationForm"].time.options.length = 0;

            const doctorSelect = document.getElementById("select-doctor");
            const doctorSelectValue = doctorSelect.options[doctorSelect.selectedIndex].value;
            const doctorSelectText = doctorSelect.options[doctorSelect.selectedIndex].text;

            const dateSelect = document.getElementById("select-date");
            const dateSelectValue = dateSelect.options[dateSelect.selectedIndex].value;

            const form = {
                doctorNo: doctorSelectValue,
                doctorName: doctorSelectText,
                selectDate: dateSelectValue
            }

            $.ajax({
                url : "gettimebydoctorno",
                type : "POST",
                dataType: 'json',
                data : form,

                success: function(data){
                    for(var i = 0; i < data.length; i++) {
                        // select 태그의 option을 정의한다.
                        var op = new Option();
                        op.value = data[i];
                        op.text = data[i];

                        // select 태그에 생성 된 option을 넣는다.
                        document.forms["reservationForm"].time.add(op);
                    }
                },
                error: function (error) {
                    alert('data error');
                }
            })

            if(data.length === 0) {
                alert('예약 가능한 시간이 없습니다.');
            }
        }

        $(document).ready(function(){
            changeDoctorSelectValue();
        });
    </script>
</head>

<body>
<div class="head_text">
<h1>예약페이지</h1>
</div>
<div class="text3">
<form name="reservationForm" method="post" action="/reservation/makereservation">
    <label for="select-date"> 1.진료 날짜를 선택하세요.
        <select id="select-date" name="date" onchange="changeTimeSelectValue()">
            <c:forEach var="row" items="${dates}">
                <option value="${row.dateName.toString()}">${row.toString()}</option>
            </c:forEach>
        </select>
    </label>
    <br>

    <label for="select-department"> 2.진료 과를 선택하세요.
        <select id="select-department" name="department" onchange="changeDoctorSelectValue()">
            <c:forEach var="row" items="${departments}">
                <option value="${row.dno}">${row.name}</option>
            </c:forEach>
        </select>
    </label>
    <br>

    <label for="select-doctor"> 3.의사 선생님을 선택하세요.
        <select id="select-doctor" name="doctor" onchange="changeTimeSelectValue()">
        </select>
    </label>
    <br>

    <label for="select-time"> 4.진료 시간을 선택하세요.
        <select id="select-time" name="time">
        </select>
    </label>
    <br>

    <label> 5.진료를 원하는 증상을 적어주세요.
        <textarea id="symptom" name="symptom"></textarea>
    </label>

    <script src="https://cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
    <script>
        CKEDITOR.replace('symptom');
    </script>

    <br>


    <input class="btn hover3" type="submit" value="예약하기">
</form>
</div>
</body>
</html>
