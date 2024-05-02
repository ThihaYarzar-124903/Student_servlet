<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="test.css">
<title>Student Registration LGN001</title>
</head>
<body class="login-page-body">


	<div class="login-page">
		<div class="form">
			<div class="login">
				<div class="login-header">
					<h1>Register Form</h1>

				</div>
			</div>
			<form class="login-form" action="UserRegisterServlet" method="post">
				<p>${blank}${passcheck }${error }</p>

				<input type="text" placeholder="ID" name="userid" value="${user.userid}" readonly /> 
				<input type="text" placeholder="Email" name="useremail"  value="${user.useremail}" /> 
				<input type="password" placeholder="Password" name="userpassword" value="${user.userpassword}"/> 
				<input type="password" placeholder="Confirm Password" name="userconpassword" />

				<button>Submit</button>
				<p class="message">
					registered? <a href="LGN001.jsp">Go Login Form</a>
				</p>
			</form>
		</div>
	</div>
</body>

</html>