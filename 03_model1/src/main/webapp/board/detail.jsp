<%@ page import="model.BoardDTO" %>
<%@ page import="dao.BoardDAO" %><%--
  Created by IntelliJ IDEA.
  User: GDJ018
  Date: 2025-07-23
  Time: 오전 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
 <%
   //---- 파라미터로 전달된 bid 받기
   request.setCharacterEncoding("UTF-8");
   int bid = 0;
   try {
     bid = Integer.parseInt(request.getParameter("bid"));
   } catch (Exception e) {
     bid = 0;
   }

   //---- bid값을 가진 Board 가져오기
   BoardDTO board = BoardDAO.getInstance().getBoardById(bid);
   request.setAttribute("board", board);
 %>

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
     location.href = "${contextPath}/board/list.jsp";
   }

   function deleteBoard() {
     if (confirm("현재 게시글을 삭제할까요?")) {
       location.href="${contextPath}/board/remove.jsp?bid=${board.bid}";
     }
   }
 </script>
</body>
</html>
