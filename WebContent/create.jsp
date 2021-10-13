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

	<c:if test="${message != null }">
	<script>
		alert("${message}");
	</script>
	</c:if>
	

	<%@include file="includes/header.jsp"%>

	<main style="display: flex">

		<%@include file="includes/menu.jsp"%>

		<div id="content">
			<div class="innertube" align="center">
				<div align="center">
					<c:if test="${user != null}">
						<form
							action="${pageContext.request.contextPath}/user?action=update"
							method="post">
					</c:if>
					<c:if test="${user == null}">
						<form
							action="${pageContext.request.contextPath}/user?action=insert"
							method="post">
					</c:if>

					<h2 align="center">
						<c:if test="${user != null}">
               Edit User
              </c:if>
						<c:if test="${user == null}">
               Add New User
              </c:if>
					</h2>
					<br />
					<table class="table table-striped table-bordered"
						style="table-layout: auto; width: auto;">
						<c:if test="${user != null}">
							<input type="hidden" name="id"
								value="<c:out value='${user.id}' />" />
						</c:if>
						<tr>
							<th>Name:</th>
							<td><input type="text" name="name" size="45"
								value="<c:out value='${user.name}' />" required/></td>
						</tr>
						<tr>
							<th>Email:</th>
							<td><input type="text" name="email" size="45"
								value="<c:out value='${user.email}' />" required/></td>
						</tr>
						<tr>
							<th>Password:</th>
							<td><input type="password" name="password" size="45"
								value="<c:out value='${user.password}'/>" required/></td>
						</tr>
					</table>
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/user?action=list"
						role="button">Back</a> <input class="btn btn-success"
						type="submit" value="Save">
					</form>
				</div>
			</div>
		</div>
	</main>



	<%@include file="includes/footer.jsp"%>

</body>
</html>