<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
</head>
<body>

	<h1 align="center">SEFAZ</h1>
	<h2 align="center">
		<a
			href="${pageContext.request.contextPath}/phone?action=new&user_id=<c:out value='${user.id}'/>">Add
			New Phone</a> &nbsp;&nbsp;&nbsp; <a
			href="${pageContext.request.contextPath}/user?action=list">List
			All Users</a> &nbsp;&nbsp;&nbsp; <a
			href="${pageContext.request.contextPath}">Logout</a>
	</h2>

	<div align="center">
		<h2>
			User:
			<c:out value='${user.name}' />
		</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>DDD</th>
				<th>Number</th>
				<th>Type</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="phone" items="${listPhone}">
				<tr>
					<td><c:out value="${phone.ddd}" /></td>
					<td><c:out value="${phone.number}" /></td>
					<td><c:out value="${phone.type}" /></td>
					<td><a
						href="${pageContext.request.contextPath}/phone?action=edit&user_id=<c:out value='${user.id}'/>&id=<c:out value='${phone.id}'/>">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="${pageContext.request.contextPath}/phone?action=delete&user_id=<c:out value='${user.id}'/>&id=<c:out value='${phone.id}'/>">Delete</a>
						&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>