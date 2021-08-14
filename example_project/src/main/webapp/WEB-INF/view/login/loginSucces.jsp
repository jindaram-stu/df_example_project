<%@page import="login.LoginRequest"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<%
	LoginRequest loginRequest = (LoginRequest)session.getAttribute("MemberInfo");
%>
<html>
<body>
<p>
	환영합니다. <%= loginRequest.getId() %>님
<p>
<p>
	<a href="<c:url value='/logout'/>">로그아웃</a>
</p>
<p>
	<a href="<c:url value='/myPage'/>">마이페이지</a>
</p>
<p>
	<a href="<c:url value='/productReg'/>">메뉴 등록</a>
</p>

</body>
</html>
