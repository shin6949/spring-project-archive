<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>게시글 리스트</title>
</head>
<body>

<table>
    <c:out value="${test}" />
    <tr>
        <td>게시판</td>
        <td>제목</td>
        <td>작성자</td>
        <td>작성일</td>
        <td>조회수</td>
    </tr>
    <c:forEach var="row" items="${posts}">
        <tr>
            <td>${row.boardName}</td>
            <td><a href="read/${row.id}">${row.title}</a></td>
            <td>${row.writerName}</td>
            <td>${row.writeTime}</td>
            <td>${row.viewNumber}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>