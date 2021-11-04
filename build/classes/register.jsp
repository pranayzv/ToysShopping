<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/login.css" rel='stylesheet' type='text/css' />
<title>ToyShopping-Register</title>
</head>
<body>
	<%
		String err = "";
		if (request.getAttribute("err") != null) {
			err = (String) request.getAttribute("err");
		}
	%>
	<!--/start-login-two-->
	<div class="login-02">
		<div class="two-login  hvr-float-shadow">
			<div class="two-login-head">
				<img src="images/top-note.png" alt="" />
				<h2>Register</h2>
				<div style="color: red"><%=err%></div>
				<%
				request.setAttribute("err", "");
				%>
				
			</div>
			<form action="RegisterServlet" method="post">
				
				<li>Full Name
				<input type="text" class="text" value=""
					name="fname" required></li>
				<li>User Name
				<input type="text" class="text" value=""
					name="uname" required></li>
				Password
				<li><input type="password" value=""
					name="password" required></li>
				<li>Address
				<textarea maxlength="50" class="text"
					name="address" required></textarea></li>
					<li>Gender
					<input type="radio" name="gender" value="male" checked>Male <br/> <input type="radio" name="gender" value="female">Female</li>
					<li>Mobile Number
				<input type="tel" class="text" 
					name="mob"  required></li>
					
				<div class="submit two">
					<input type="submit" value="Register">
				</div>
				<h5>
					<a href="login.jsp">Login Here</a>
				</h5>
			</form>
		</div>
	</div>
</body>
</html>