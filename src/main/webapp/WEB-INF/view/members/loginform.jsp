<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<div>
    <c:if test="${param.loginfail eq 1}">
        <h3>로그인에 실패하였습니다.</h3><br>
    </c:if>
    <div>
        <form method="post" action="/authenticate">
            <div>
                <label>ID</label>
                <input type="text" name="userId">
            </div>
            <div>
                <label>
                    암호
                    <input type="password" name="password">
                </label>
            </div>
            <div>
                <label></label>
                <input type="submit" value="로그인">
            </div>
        </form>
    </div>
</div>
</body>
</html>
