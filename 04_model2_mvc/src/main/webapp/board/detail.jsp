<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var = "contextPath" value = "${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<h1>${board.title}</h1>
<div>작성자 번호: ${board.user.uid}</div>
<div>작성자 닉네임: ${board.user.nickname}</div>
<div>최초 작성 일시: ${board.createdAt}</div>
<div>최종 수정 일시 : ${board.modifiedAt}</div>

<hr>

<pre>${board.content}</pre>

<hr>

<button type="button" onclick="list()">목록보기</button>
<c:if test="${board ne null}">
    <button type="button" onclick="deleteBoard()">삭제</button>
</c:if>

<script type="text/javascript">
    function list() {
        location.href = "${contextPath}/board/list.do";
    }

    function deleteBoard() {
        if (confirm("현재 게시글을 삭제할까요?")) {
            location.href="${contextPath}/board/remove.do?bid=${board.bid}";
        }
    }
</script>
</body>
</html>