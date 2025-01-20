<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <!-- <%=((Member)request.getAttribute("member")).getId()%>
      -> property 접근법 ${member.id} 조회할 땐 자동으로 getter, 값을 넣으면 setter 호출 -->
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
