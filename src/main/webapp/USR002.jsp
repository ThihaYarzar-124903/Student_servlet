<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="test.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<title>Course Registration</title>

<style>
	.css{
		margin-left:520px;
	}

</style>

</head>

<body>
	<div id="testheader">
		<div class="container">
			<div class=row>
				<div class="col-md-5 ">
					<a href="MNU001.html"><h3>Student Registration</h3></a>
				</div>

				<div class="col-md-1">
					<a style="margin-left: 700px;" class="btn btn-danger" onclick="showLogoutConfirmation()">Logout</a>
				</div>
			</div>
		</div>

	</div>
	<!-- <div id="testsidebar">Hello World </div> -->
	<div class="container">
		<div class="sidenav">

			<button class="dropdown-btn">
				Class Management <i class="fa fa-caret-down"></i>
			</button>

			<div class="dropdown-container">
				<a href="/Student_Servlet/CourseRegisterServlet">Course Registration </a> 
				<a href="/Student_Servlet/DisplayCourseServlet">Course Management </a> 
				<a href="/Student_Servlet/DisplayStudentServlet">Student Search </a>
			</div>
			<a href="/Student_Servlet/DisplayUserServlet">Users Management</a>
		</div>
		<a href="USR003.html">Users Management</a>
	</div>
	<div class="main_contents">
		<div id="sub_content">

			<form action="UserUpdateServlet" method="post">
				${error }
				<h2 class="col-md-6 offset-md-2 mb-5 mt-4">User Update</h2>
				<input type="hidden" name="id" value="${res.id}">
				<div class="row mb-4">
					<div class="col-md-2"></div>
					<label for="email" class="col-md-2 col-form-label">ID</label>
					<div class="col-md-4">
						<input type="text" class="form-control" name="userid" id="id" value="${res.userid}" readonly="true">
					</div>
				</div>



				<div class="row mb-4">
					<div class="col-md-2"></div>
					<label for="email" class="col-md-2 col-form-label">Email</label>
					<div class="col-md-4">
						<input type="email" class="form-control" name="useremail" id="email" value="${res.useremail}">
					</div>
				</div>



				<div class="row mb-4">
					<div class="col-md-2"></div>
					<label for="Passowrd" class="col-md-2 col-form-label">Passowrd</label>
					<div class="col-md-4">
						<input type="password" class="form-control" name="userpassword" id="name" value="${res.userpassword}">
					</div>
				</div>

				<div class="row mb-4">
					<div class="col-md-2"></div>
					<label for="confirmPassword" class="col-md-2 col-form-label">Confirm Passowrd</label>
					<div class="col-md-4">
						<input type="password" class="form-control" name="userconpassword" id="confirmPassword" value="${res.userconpassword}">
					</div>
				</div>

				<div class="css">

					<button type="submit" class="btn btn-success col-md-2">Update</button>

				</div>
			</form>
		</div>
	</div>
	
	<div class="modal" tabindex="-1" role="dialog" id="logoutModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Logout Confirmation</h5>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to logout?</p>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn btn-primary" onclick="logout()">Logout</a>
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="cancelDelete()">Cancel</button>
            </div>
        </div>
    </div>
	</div>

	<div id="testfooter">
		<span>Copyright &#169; ACE Inspiration 2022</span>
	</div>
	<script>
		/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
		var dropdown = document.getElementsByClassName("dropdown-btn");
		var i;

		for (i = 0; i < dropdown.length; i++) {
			dropdown[i].addEventListener("click", function() {
				this.classList.toggle("active");
				var dropdownContent = this.nextElementSibling;
				if (dropdownContent.style.display === "block") {
					dropdownContent.style.display = "none";
				} else {
					dropdownContent.style.display = "block";
				}
			});
		}
		
		function showLogoutConfirmation() {
            $('#logoutModal').modal('show');
        }

        function logout() {
            window.location.href = "LGN001.jsp";
        }

        function cancelDelete() {
            $('#logoutModal').modal('hide');
        }
		
	</script>

</body>

</html>





