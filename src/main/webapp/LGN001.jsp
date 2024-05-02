<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="test.css">
<title> Student Registration LGN001 </title>
</head>
<body class="login-page-body"> 
  
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            <p>${error}</p>
            <p>${success }</p>
          </div>
        </div>
        <form class="login-form" action="LoginServlet" method="post">
          <input type="text" placeholder="Email" name="email"/>
          <input type="password" placeholder="Password" name="password"/>
          <select style="width:100%;" class="form-select" id="userRole" name="role">
                  <option >Admin</option>
                  <option >User</option>
    	  </select>
          <br><br>
          <button>login</button>
          <p class="message">Not registered? <a href="/Student_Servlet/UserRegisterServlet">Create an account</a></p>
        </form>
      </div>
    </div>
</body>

</html>