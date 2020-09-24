<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>게시판리스트</title>
</head>
<body>
<table>
    <tr>
        <td>번호</td>
        <td>제목</td>
        <td>작성자</td>
        <td>날짜</td>
        <td>조회수</td>
    </tr>
    <c:forEach items="${list }" var="post">
        <tr>
            <td>${post.number }</td>
            <td><a href="read?number=${post.number}">${post.title }</a></td>
            <td>${post.writer }</td>
            <td>${post.write_time }</td>
            <td>${post.view_number }</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>