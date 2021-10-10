<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Create Phone</title>
</head>
<body>
	<h1 align="center">Phone</h1>
	<h2 align="center">
		<a href="${pageContext.request.contextPath}/phone?action=new">Add New Phone</a> &nbsp;&nbsp;&nbsp; <a
			href="${pageContext.request.contextPath}/user?action=list">List
			All Phones</a>

	</h2>
	<div align="center">
		<c:if test="${phone != null}">
			<form action="${pageContext.request.contextPath}/phone?action=update&user_id=<c:out value='${user.id}'/>&id=<c:out value='${phone.id}'/>"
				method="post">
		</c:if>
		<c:if test="${phone == null}">
			<form action="${pageContext.request.contextPath}/phone?action=insert&user_id=<c:out value='${user.id}'/>"
				method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${phone != null}">
               Edit Phone
              </c:if>
					<c:if test="${phone == null}">
               Add Phone
              </c:if>
				</h2>
			</caption>
			<c:if test="${phone != null}">
				<input type="hidden" name="id" value="<c:out value='${phone.id}' />" />
			</c:if>
			<tr>
				<th>DDD</th>
				<th>Number</th>
			</tr>
			<td><input type="text" name="ddd" size="5" maxlength="3"
				value="<c:out value='${phone.ddd}' />" /></td>
			<td><input type="text" name="number" size="30" maxlength="9"
				value="<c:out value='${phone.number}' />" /></td>
			<tr>
			</tr>

			<tr>
				<th>Type:</th>
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