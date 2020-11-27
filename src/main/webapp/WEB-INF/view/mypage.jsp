<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>마이 페이지</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        function cancelReservation(){
            const url = "cancelreservation";    // Controller로 보내고자 하는 URL
            var rno =$('input[name="rnoChecked"]:checked').val();
            if (rno == null){
                alert("선택된 항목이 없습니다.");
            }
            else {
                var chk = confirm("정말 취소하시겠습니까?");
                if (chk) {
                    $.ajax({
                        url: url,                    // 전송 URL
                        type: 'POST',                // POST 방식
                        traditional: true,
                        data: {
                            rno: rno       // 보내고자 하는 data 변수 설정
                        },
                        success: function (jdata) {
                            if (jdata == 1) {
                                alert("예약 취소 되었습니다.");
                                location.replace("mypage") //list 로 페이지 새로고침
                            } else {
                                alert("예약 취소에 실패 하였습니다.");
                            }
                        }
                    });
                }
            }
        }
    </script>
</head>
<body>
<c:if test="${loginedName ne null}">
    <h4 style="float: right;">${loginedName}님 환영합니다. | <a href="${pageContext.request.contextPath}/logout">로그아웃</a></h4>
</c:if>

<table class="table table-striped">
    <tr>
        <td>선택</td>
        <td>예약 시간</td>
        <td>예약 상태</td>
        <td>의사</td>
        <td>진료과</td>
        <td>증상</td>
    </tr>
    <c:forEach var="row" items="${reservation}">
        <tr>
            <td>
                <c:if test="${row.isCancelable() eq 'true'}"><input type="radio" name="rnoChecked" value="${row.rno}"></c:if>
                <c:if test="${row.isCancelable() eq 'false'}"><input type="radio" name="rnoChecked" value="${row.rno}" disabled></c:if>
            </td>
            <td>${row.getReservationTimeString()}</td>
            <td><c:choose>
                <c:when test="${row.confirmed eq 'true'}">예약됨</c:when>
                <c:when test="${row.confirmed eq 'false'}">취소됨</c:when>
            </c:choose></td>
            <td>${row.doctor}</td>
            <td>${row.department}</td>
            <td>${row.symptom}</td>
        </tr>
    </c:forEach>
</table>
<input type="button" value="예약 취소" onclick="cancelReservation();">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>
</html>
