<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Phones</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>

	<%@include file="includes/header.jsp"%>

	<main style="display: flex">

		<%@include file="includes/menu.jsp"%>

		<div id="content">
			<div class="innertube" align="center">
				<h2>
					User:
					<c:out value='${user.name}' />
				</h2>
				<div align="center">

					<table class="table table-striped table-bordered"
						style="table-layout: auto; width: auto;">
						<tr>
							<th>DDD</th>
							<th>Number</th>
							<th>Type</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
						<c:forEach var="phone" items="${listPhone}">
							<tr>
								<td><c:out value="${phone.ddd}" /></td>
								<td><c:out value="${phone.number}" /></td>
								<td><c:out value="${phone.type}" /></td>
								<td><a
									href="${pageContext.request.contextPath}/phone?action=edit&user_id=<c:out value='${user.id}'/>&id=<c:out value='${phone.id}'/>">Edit</a>
								</td>
								<td><a
									href="${pageContext.request.contextPath}/phone?action=delete&user_id=<c:out value='${user.id}'/>&id=<c:out value='${phone.id}'/>">Delete</a>
								</td>
							</tr>
						</c:forEach>
					</table>
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/user?action=list"
						role="button">Back</a> <a class="btn btn-success"
						href="${pageContext.request.contextPath}/phone?action=new&user_id=<c:out value='${user.id}'/>">Add
						Phone</a>
				</div>
			</div>
		</div>
	</main>

	<%@include file="includes/footer.jsp"%>

</body>
</html>