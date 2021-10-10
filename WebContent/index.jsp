<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login - SEFAZ</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
		<h1>Login - SEFAZ</h1>
		<form action="<%=request.getContextPath()%>/login" method="post">
			<div class="form-group">
				<label for="uname">Email:</label> <input type="text"
					class="form-control"  placeholder="Email"
					name="email" required>
			</div>
			<div class="form-group">
				<label for="uname">Password:</label> <input type="password"
					class="form-control" placeholder="Password"
					name="password" required>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>