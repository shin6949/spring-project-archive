<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
	<div class="grid-sample" style="height: 600px;">
		<div id="grid_data" style="height: 600px;"></div>
	</div>
	<h1>YOU ARE NOW LOGINED!</h1>
</sec:authorize>
</body>
</html>
