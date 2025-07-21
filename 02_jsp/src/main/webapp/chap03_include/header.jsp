<%--
  Created by IntelliJ IDEA.
  User: GDJ018
  Date: 2025-07-21
  Time: 오후 5:16
  To change this template use File | Settings | File Templates.
--%>

<%
  // 요청 인코딩
  request.setCharacterEncoding("UTF-8");

  // <jsp:param> 태그가 전달한 파라미터
  String title = request.getParameter("title");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=title%></title>
</head>
<body>

<div class="header">

  여기가 Header 입니다.
</div>