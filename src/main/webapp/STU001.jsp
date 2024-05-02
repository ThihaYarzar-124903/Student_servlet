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

<style>
.logout {
	margin-left: 600px;
	text-color: white;
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
					<a class="logout btn btn-danger" onclick="showLogoutConfirmation()">Logout</a>
				</div>
			</div>
		</div>

	</div>
	<!-- <div id="testsidebar">Hello World </div> -->
	<div class="container">
		<div class="sidenav">

			<a href="/Student_Servlet/StudentAddServlet">Student Register</a>

		</div>
		<div class="main_contents">
			<div id="sub_content">
				<form action="StudentAddServlet" method="post"
					enctype="multipart/form-data" onsubmit="return validatePhoneNumber();">

					<h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student
						Registration</h2>
					${error}
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="studentID" class="col-md-2 col-form-label">Student
							ID</label>
						<div class="col-md-4">
							<input type="text" class="form-control" value="${student.id}" name="id" readonly>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="name" class="col-md-2 col-form-label">Name</label>
						<div class="col-md-4">
							<input type="text" class="form-control" name="name"/>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="dob" class="col-md-2 col-form-label">DOB</label>
						<div class="col-md-4">
							<input type="date" class="form-control" name="dob"/>
						</div>
					</div>

					<fieldset class="row mb-4">
						<div class="col-md-2"></div>
						<legend class="col-form-label col-md-2 pt-0">Gender</legend>
						<div class="col-md-4">
							<div class="form-check-inline">
								<input class="form-check-input" type="radio" name="gender"
									value="Male"> <label class="form-check-label"
									for="gridRadios1"> Male </label>
							</div>
							<div class="form-check-inline">
								<input class="form-check-input" type="radio" name="gender"
									value="Female"> <label class="form-check-label"
									for="gridRadios2"> Female </label>
							</div>

						</div>
					</fieldset>


					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="phone" class="col-md-2 col-form-label">Phone</label>
						<div class="col-md-4">
							 <input type="text" class="form-control" id="phone" name="phone" placeholder="09XXXXXXXXX"/>
               				 <small id="phoneError" class="text-danger"></small> <!-- Error message placeholder -->
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="education" class="col-md-2 col-form-label">Education</label>
						<div class="col-md-4">
							<select class="form-select" aria-label="Education" name="edu">
								<option>Bachelor of Information Technology</option>
								<option>Diploma in IT</option>
								<option>Bachelor of Computer Science</option>

							</select>
						</div>
					</div>
					<fieldset class="row mb-4">
						<div class="col-md-2"></div>
						<legend class="col-form-label col-md-2 pt-0">Attend</legend>

						<div class="col-md-4">
							<c:forEach items="${list1}" var="list1">
								<div class="form-check-inline col-md-2">
									<input type="checkbox" name="attend"
										value="${list1.coursename }"> <label>
										${list1.coursename} </label>

								</div>
							</c:forEach>
						</div>

					</fieldset>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="name" class="col-md-2 col-form-label">Photo</label>
						<div class="col-md-4">
							<input type="file" class="form-control" name="photo"/>
						</div>
					</div>

					<div class="row mb-4">
						<div class="col-md-4"></div>

						<div class="col-md-4">
							<input type="submit" class="btn btn-primary" value="Add" />
							<input type="reset" class="btn btn-secondary" value="Cancel" />
						</div>
					</div>

				</form>
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
            
            function validatePhoneNumber() {
                var phoneInput = document.getElementById('phone').value;
                var phoneRegex = /^[0-9]{11}$/; // Regex for 11-digit numeric phone number

                if (!phoneRegex.test(phoneInput)) {
                    document.getElementById('phoneError').textContent = "Phone number must be 11 digits numeric.";
                    return false; // Prevent form submission
                } else {
                    document.getElementById('phoneError').textContent = ""; // Clear error message
                    return true; // Allow form submission
                }
            }

            
     </script>
</body>

</html>




