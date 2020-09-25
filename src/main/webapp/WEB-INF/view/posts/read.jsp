<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>${post.title}</title>
    <script type="text/javascript">
        function isValidate(obj) {
            if(obj.content.value === "") {
                alert("내용이 입력되지 않았습니다.")
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
<h2><c:out value="${post.title}" /></h2><br>
작성자: <c:out value="${post.writerName}" /><br>
작성 시간: <c:out value="${post.writeTimeString}" /><br>
조회수: <c:out value="${post.viewNumber}" /><br>
${post.content}<br>

댓글: ${comment_count}개 <br>

<c:forEach var="row" items="${comments}">
    <div>
        작성자: ${row.writerName}<br>
        작성 시간: ${row.writeTimeString}<br>
        ${row.content}<br>
    </div>
    <br>
</c:forEach>

댓글 남기기<br>
<form action="insertcomment/${postId}" method="post" onsubmit="return isValidate(this)">
    <input type="text" name="content"><br>
    <input type="submit" value="댓글 작성">
</form>
</body>
</html>
