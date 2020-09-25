<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>${post.title}</title>
</head>
<body>
<c:out value="${post.title}" /><br>
<c:out value="${post.content}" /><br>
<c:out value="${post.writerName}" /><br>
<c:out value="${post.writeTime}" /><br>

</body>
</html>
