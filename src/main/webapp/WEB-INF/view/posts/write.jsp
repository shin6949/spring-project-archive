<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 쓰기</title>
</head>
<body>
    <form action="/board/insertpost" method="post">
        <label>제목<input type="text" name="title"></label><br>
        <label>내용<br>
        <textarea id="content" name="content"></textarea>

        <script src="https://cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
        <script>
            CKEDITOR.replace('content');
        </script>
        </label>

        <input type="submit" value="업로드">
    </form>
</body>
</html>
