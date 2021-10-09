<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Create User</title>
</head>
<body>
	<h1 align="center">Phone</h1>
	<h2 align="center">
		<a href="create.jsp">Add New Phone</a> &nbsp;&nbsp;&nbsp; <a
			href="${pageContext.request.contextPath}/user?action=list">List
			All Users</a>

	</h2>
	<div align="center">
		<c:if test="${user != null}">
			<form action="${pageContext.request.contextPath}/user?action=update"
				method="post">
		</c:if>
		<c:if test="${user == null}">
			<form action="${pageContext.request.contextPath}/user?action=insert"
				method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${user != null}">
               Edit Phone
              </c:if>
					<c:if test="${user == null}">
               Add Phone
              </c:if>
				</h2>
			</caption>
			<c:if test="${user != null}">
				<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
			</c:if>
			<tr>
				<th>Phone:</th>
				<td><input type="radio" id="residencial" name="type"
					value="Residencial"> <label for="residencial">Residencial</label><br>

					<input type="radio" id="celular" name="type" value="celular">
					<label for="celular">Celular</label><br>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Save" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>