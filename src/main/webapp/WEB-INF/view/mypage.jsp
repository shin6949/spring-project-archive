<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>마이 페이지</title>
</head>
<body>
<c:if test="${loginedName ne null}">
    <h4 style="float: right;">${loginedName}님 환영합니다. | <a href="${pageContext.request.contextPath}/logout">로그아웃</a></h4>
</c:if>

<table class="table table-striped">
    <tr>
        <td>번호</td>
        <td>의사</td>
        <td>작성자</td>
        <td>작성일</td>
        <td>조회수</td>

    </tr>
    <c:forEach var="row" items="${getall}">
        <tr>
            <td>${row.rno}</td>
            <td>${row.doctor_no}</td>
            <td>${row.cno}</td>
            <td>${row.confirmed}</td>
            <td>${row.symptom}</td>
            <td>${row.reservation_time}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
