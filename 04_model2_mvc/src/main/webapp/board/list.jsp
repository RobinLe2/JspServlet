<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var = "contextPath" value = "${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        a {
            text-decoration: none;
            color: mediumaquamarine;
            /* 인라인요소 정렬 못함 text-align: center;*/
        }
        div {
            box-sizing : border-box;
        }
        .wrap{
            width: 1024px;
            margin: 0 auto;
        }
        .title {
            text-align: center;
            color: olive;
        }
        .regist-form-link {
            display: block;
            text-align: center;
        }
        .flex-container {
            margin-top:20px;
            display: flex;
            flex-wrap : wrap;  /* 여러 줄로 flex-item 감싸기*/
            gap:15px
        }
        .flex-item {
            flex: 0 0 calc(20% - 12px); /* flex-grow(공간이 남으면 아이템을 얼마나 늘이나?)  flex-shrink(아이템의 기본크기 flex-basis */
            border:1px solid darkred;
            border-radius: 5px;
            text-align: center;
            padding-top: 10px;
            transition : transform 0.3s ease;
        }
        .flex-item:hover{
            transform : scale(1.05);
            background-color : beige;
        }

    </style>
</head>
<body>
    <div class = "wrap">
  <h1 class="title">게시글 목록 보기</h1>
  <a class="regist-form-link" href="${contextPath}/board/registForm.do">신규 게시글 작성</a> <hr>




  <div class="flex-container">
      <c:forEach var="b" items="${boards}">

          <div class="flex-item" onclick="detail(${b.bid})">
              <h3><strong>${b.title}</strong></h3>
              <div class="meta">작성자: ${b.user.nickname}</div>
              <div class="meta">작성일: ${b.createdAt}</div>
              <div class="content">${b.content}</div>
          </div>
          <%--
             `` 내부에서 EL(${})과 템플릿 리터럴 (${}) 구분하기
             1. ${contextPath} : 자바 변수
             2. \${bid} : 자바스크립트 변수

          --%>
          <script type="text/javascript">
              function detail(bid) {
                  location.href = `${contextPath}/board/detail.do?bid=\${bid}&code=detail`;
              }
          </script>
      </c:forEach>
    </div>
    </div>
</body>
</html>