<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <link href="/resources/main_css.css" rel="stylesheet" type="text/css">
    <title>메인 페이지</title>
</head>
<body>
<c:if test="${loginedName ne null}">
    <h4 style="float: right;">${loginedName}님 환영합니다. | <a href="${pageContext.request.contextPath}/logout">로그아웃</a></h4>
    <br>
    <br>
    <a href="${pageContext.request.contextPath}/reservation/">
        <h2>예약하기</h2>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/mypage">
        <h2>마이 페이지</h2>
    </a>
</c:if>

<c:if test="${loginedName eq null}">
    <div class="text1">
    <a class="link1" href="${pageContext.request.contextPath}/login">
        <h2>로그인</h2>
    </a>
    <br>
    <a class="link1" href="${pageContext.request.contextPath}/members/register">
        <h2>회원가입</h2>
    </a>
    <br>
    </div>
</c:if>
</body>
</html>
