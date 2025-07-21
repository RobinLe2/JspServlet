<%@ page import="java.util.*" %>
<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: GDJ018
  Date: 2025-07-21
  Time: 오후 2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%-- 1. 화면을 새로 고침 할 때 마다 변수 count 값을 1 증가시켜서 화면에 출력하세요--%>
<%!
 int count = 0;
 %>

<%
  count++;
%>

<%= "count 값: " + count %>
<hr>

<%-- 2. "일", ... "토" 요일 정보를 배열에 저장하고 현재 요일 정보를 화면에 출력하세요--%>
<%!
  String[] weekdays = {"일","월","화","수","목","금","토"};
  %>
<% LocalDate today = LocalDate.now();
    int dayOfWeekNo = today.getDayOfWeek().getValue() % 7;
    %>
<div> 오늘은 <%= weekdays[dayOfWeekNo]%>요일입니다.</div>

<hr>
<%-- 3. List<String> fruits에 과일명을 3개 저장하고 화면에 <ul> 태그로 출력하세요. --%>
<%!List<String> fruits = new ArrayList<>();
%>
<%
        fruits.add("귤");
        fruits.add("블루베리");
        fruits.add("수박");

%>
<ul>
  <% for (String fruit : fruits) { %>
  <li><%= fruit %></li>
  <% } %>
</ul>

<hr>

<%-- 4. Map<String, String> members nickname과 name을 하나의 Entry로 저장하세요. 멤버는 3명을 만들고 각 회원 정보를 <table> 태그로 출력하세요--%>
<%
    Map<String, String> members = new HashMap<>();

    members.put("robin", "이창민");
    members.put("chloe", "나유경");
    members.put("billy", "홍세중");
%>
<table border = "1">
    <tr>
        <th>닉네임</th>
        <th>이름</th>
    </tr>
    <%
        for (Map.Entry<String, String> entry : members.entrySet()) {%>
        <tr>
            <td><%= entry.getKey()%></td>
            <td><%= entry.getValue()%></td>
        </tr>
    <% } %>

</table>

<hr>
<%-- 5. int age에 임의의 나이를 저장하고, 20세 기분으로 "성인입니다." 또는 "미성년자입니다"를 화면에 출력하세요--%>
<%
int age = 17;
String result;

if (age >= 20) {
result = "성인입니다.";
} else {
result = "미성년자입니다.";
}
%>

<%= "나이:  " + age%>
<br>
<%= "결과: " + result%>

</body>
</html>

