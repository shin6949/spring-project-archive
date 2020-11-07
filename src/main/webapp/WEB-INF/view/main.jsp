<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>메인 페이지</title>
</head>
<body>
<c:if test="${loginedId ne null}">
    <h2>${loginedName}님 환영합니다. | <a href="${pageContext.request.contextPath}/logout">로그아웃</a></h2>
    <br>
    <a href="${pageContext.request.contextPath}/board/posts">
        <h2>문의 게시판</h2>
    </a>
</c:if>

<c:if test="${loginedId eq null}">
    <a href="${pageContext.request.contextPath}/login">
        <h2>로그인</h2>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/members/register">
        <h2>회원가입</h2>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/board/posts">
        <h2>문의 게시판</h2>
    </a>
</c:if>

</body>
</html>
