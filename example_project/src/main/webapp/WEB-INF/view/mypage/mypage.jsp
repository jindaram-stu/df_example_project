<%@ page import="java.util.Map"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<%
	Map<String,Object> userInfo = (Map<String,Object>)session.getAttribute("userInfo");
%>


<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<title>My Page</title>
	</head>
	
	<body>
	<%= userInfo.get("client_id") %>님의 마이페이지입니다.
	
	<p>이름 : <%= userInfo.get("client_id") %></p>
	<p>이메일 :<%= userInfo.get("client_email") %></p> 
	<p>주소 : <%= userInfo.get("client_address") %></p> 
	<p>전화번호 : <%= userInfo.get("client_tel") %></p> 
	<p>사업자등록증 등록 여부 : <%= userInfo.get("client_entre") %></p>
	<p>카카오톡 연동 여부 : <%= userInfo.get("client_kakao") %></p> 
	---------------------------------------------
	</body>


</html>