<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>SpirngSecurity websocket example</title>
</head>
<body>
<h1>
	WebSocket Chat Example!
</h1>
<ul>
	<sec:authorize access="!hasRole('ROLE_USER')">
		<li><a href="login">로그인</a></li>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ROLE_USER')">
		<sec:authentication property="name" var="loginUser" />
		<li><h2>${loginUser}님 로그인중</h2></li>
	</sec:authorize>
		<li><a href="chat.htm">채팅방 접속하기</a></li>
</ul>

</body>
</html>
