<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<c:if test="${message != null }">
		<script>
			alert("${message}");
		</script>
	</c:if>
	
	<%@include file="includes/header.jsp"%>

	<main>

		<div>
			<div class="innertube" style="margin-left: 30%; margin-right: 30%;">
				<div class="container col-md-8 col-md-offset-3"
					style="overflow: auto">
					<br /> <br />
					<h1 align="center">LOGIN</h1>
					<form action="<%=request.getContextPath()%>/login" method="post">
						<div class="form-group">
							<label for="uname">Email:</label> <input type="text"
								class="form-control" placeholder="Email" name="email" required>
						</div>
						<div class="form-group">
							<label for="uname">Password:</label> <input type="password"
								class="form-control" placeholder="Password" name="password"
								required>
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</main>

	<%@include file="includes/footer.jsp"%>

</body>
</html>