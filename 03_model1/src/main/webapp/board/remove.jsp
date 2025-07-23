<%@ page import="dao.BoardDAO" %><%--
  Created by IntelliJ IDEA.
  User: GDJ018
  Date: 2025-07-23
  Time: 오전 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

//--- bid값을 가진 게시글 삭제하기
  int count = BoardDAO.getInstance().deleteBoard(bid);

  //----- 삭제 후 이동할 경로 및 메시지 결정
  String msg = "게시글 삭제 실패";
  String url = "list.jsp";
  if (count == 1) {
    msg = "게시글 삭제 성공";
  }
%>

<script type="text/javascript">
  alert("<%=msg%>");
  location.href="<%=url%>";
</script>
</body>
</html>
