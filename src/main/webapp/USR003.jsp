<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div class="main_contents">
			<div id="sub_content">
				<form class="row g-3 mt-3 ms-2" action="UserSearchServlet"
					method="get">

					<div class="col-auto">
						<label for="inputPassword2" class="visually-hidden">User Email</label> 
						<input type="text" class="form-control" id="inputPassword2" placeholder="User Email" name="useremail">
					</div>

					<div class="col-auto">
						<button type="submit" class="btn btn-primary mb-3">Search</button>
					</div>

					<div class="col-auto">
						<a href="/Student_Servlet/DisplayServlet">
							<button type="submit" class="btn btn-danger mb-3">Reset</button>
						</a>
					</div>
				</form>
				<div>${error}</div>
				<table class="table table-striped" id="stduentTable">
					<thead>
						<tr>

							<th scope="col">User ID</th>
							<th scope="col">User Email</th>
							<th scope="col">Details</th>

						</tr>
					</thead>

					<tbody>

						<c:forEach items="${list}" var="user">
							<tr>
								<td>${user.userid}</td>
								<td>${user.useremail}</td>

								<td>
									<a href="UserUpdateServlet?id=${user.id} "class="btn btn-primary">Update</a>
								</td>
								<td>
									<a href="DeleteUserServlet?id=${user.id}" class="btn btn-danger" onclick="return confirm('Are you sure want to delete?!');">Delete</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>


				</table>


			</div>
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









