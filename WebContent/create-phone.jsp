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
			<div class="innertube" align="center">
				<div align="center">
					<c:if test="${phone != null}">
						<form
							action="${pageContext.request.contextPath}/phone?action=update&user_id=<c:out value='${user.id}'/>&id=<c:out value='${phone.id}'/>"
							method="post">
					</c:if>
					<c:if test="${phone == null}">
						<form
							action="${pageContext.request.contextPath}/phone?action=insert&user_id=<c:out value='${user.id}'/>"
							method="post">
					</c:if>
					<table class="table table-striped table-bordered"
						style="table-layout: auto; width: auto;">
						<h2>
							<c:if test="${phone != null}">
               Edit Phone
              </c:if>
							<c:if test="${phone == null}">
               Add Phone
              </c:if>
						</h2>
						<c:if test="${phone != null}">
							<input type="hidden" name="id"
								value="<c:out value='${phone.id}' />" />
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
					</table>
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/phone?action=list&user_id=<c:out value='${user.id}'/>"
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