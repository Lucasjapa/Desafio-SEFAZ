<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Users</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>

	<%@include file="includes/header.jsp"%>

	<main style="display: flex">

		<%@include file="includes/menu.jsp"%>

		<div id="content">

			<h1 align="center">List of Users</h1>
			<div class="innertube" align="center">

				<table class="table table-striped table-bordered"
					style="table-layout: auto; width: auto;">
					<tr>
						<th>Name</th>
						<th>Email</th>
						<th>Edit</th>
						<th>Delete</th>
						<th>Phones</th>
					</tr>
					<c:forEach var="user" items="${listUser}">
						<tr>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><a
								href="${pageContext.request.contextPath}/user?action=edit&id=<c:out value='${user.id}' />">Edit</a></td>
							<td><a
								href="${pageContext.request.contextPath}/user?action=delete&id=<c:out value='${user.id}' />">Delete</a></td>
							<td><a
								href="${pageContext.request.contextPath}/phone?action=list&user_id=<c:out value='${user.id}'/>">List
									phones</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</main>

	<%@include file="includes/footer.jsp"%>

</body>
</html>


