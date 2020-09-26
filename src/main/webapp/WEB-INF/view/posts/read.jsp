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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <table class="table">
            <thead>
            <tr class="table-active">
                <th scope="col" style="width: 60%">제목 : <c:out value="${post.title}" /><br>
                    작성자 : <c:out value="${post.writerName}" /></th>
                <th scope="col" style="width: 40%" class="text-right">조회 : <c:out value="${post.viewNumber}" />
                    <br>작성시간 : <c:out value="${post.writeTimeString}" /></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><pre>${post.content}</pre></td>
            </tr>
            </tbody>
        </table>
        <br>
        <div class="btn-toolbar" role="toolbar" style="width: 100%;">
            <div class="btn-group mr-2" role="group">
                <button type="button" class="btn btn-secondary">수정</button>
                <button type="button" class="btn btn-secondary">삭제</button>
            </div>
        </div>
        <br>
        <table class="table">
            <thead>
            댓글 수: ${comment_count}<br>
            <br>
            <c:forEach var="row" items="${comments}">
                <tr class="table-active">
                    작성자: ${row.writerName}<br>
                    작성 시간: ${row.writeTimeString}<br>
                        ${row.content}<br>
                    <br>
                </tr>
                <div style="width: 100%;">
                <button type="button" class="btn btn-secondary" >댓글삭제</button>
                </div>
            </c:forEach>
            </thead>
        </table>
        <table>
            <thead>
            <tr class="table-active">
                <form action="/comment/insert/${postId}" method="post" onsubmit="return isValidate(this)">
                    <input type="text" name="content"><br>
                    <input type="submit" value="댓글 작성">
                </form>

            </tr>
            </thead>
        </table>


        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>
