<%@page import="java.util.*"%>
<%@page import="model.Toy"%>
<%@page import="model.User"%>
<%@page import="dao.Dao"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%!Connection con;%>
<%!public Connection getConnection() {
		try {
			Class.forName(utils.Constants.JDBC);
			if (con != null) {
				con.close();
			}
			return DriverManager.getConnection(utils.Constants.URL, utils.Constants.USER, utils.Constants.PASS);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="css/head.css" />
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript"></script>
</head>
<body>

	<%
	con = getConnection();
	String uname = "undefined";

	if (session.getAttribute("username") == null) {
		request.setAttribute("err", "Please login first.");
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	} else {
		uname = session.getAttribute("username").toString();
	}

	ArrayList<Toy> toy = Dao.getToyList(uname, con);

	String category = "";

	if (session.getAttribute("category") != null) {
		category = session.getAttribute("category").toString();
	} else {
		category = "girl";
	}
	//cloning toy list
	for (Toy t : new ArrayList<Toy>(toy)) {
		if (!t.getTypeof().equals(category)) {
			toy.remove(t);
		}
	}

	//dao.CmpDao.getCompanyList(uname, con);
	%>


	<div class="header">
		<h1>
			<a href="main.jsp">Toys Shopping</a>
		</h1>
		<form method="post" action="MainServlet" >
		<h5>
			let's go shopping.. <label style="float: right">User: <%=uname%></label><br>
			<button class="logoutbtn" type="submit" name="logout" value="logout">Logout</button>
		</h5>
		
			</form>

	</div>
<br>





	<div>
		<div >
			<form action="MainServlet" method="post">
				<select name="category" class="mainbtn">
					<%
					if (category.equals("girl")) {
					%>
					<option value="girl">girl</option>
					<option value="boy">boy</option>
					<option value="edu">educational</option>
					<%
					}
					%>
					<%
					if (category.equals("boy")) {
					%>
					<option value="boy">boy</option>
					<option value="girl">girl</option>
					<option value="edu">educational</option>
					<%
					}
					%>
					<%
					if (category.equals("edu")) {
					%>
					<option value="edu">educational</option>
					<option value="girl">girl</option>
					<option value="boy">boy</option>
					<%
					}
					%>
				</select>
				<button class="mainbtn" type="submit">filter</button>
				<br>
			</form>
		</div>
	</div>
	
	<br>
<table>
	<%
	for (int i = 0; i < toy.size(); i++) {
	%>
	<tr>
	
	
	
	
	
		
		<td>
		
		<form action="MainServlet" method="post">
			<div style="float: left; margin-right: 250px;">
				<%
				String toypath = "toysimages/" + toy.get(i).getTypeof() + "/" + toy.get(i).getImage();
				String toyname = toy.get(i).getName();
				%>
				<input type="hidden" name="prod" value=<%=toyname.replace(" ", "_")%>>
				<input type="image" alt="submit" src=<%=toypath%> height="220px" width="320px">

			</div>
			</form>
			</td>
			<td>
			<div style=" margin-left: 50px;">
				<h2>
					<label> Name: <%=toyname%>
					</label><br> 
					 <label> 
					 <%if (toy.get(i).getTypeof().equals("edu")) {%>
					Category: Educational 
					<%} else {%>
					 Category: <%=toy.get(i).getTypeof()%>
					<%}%>
					</label>
				</h2>
				<h3>
					<label> Price:₹ <%=toy.get(i).getPrice()%>
					</label>
				</h3>
				<br>
				<br> <label style="color:#000000;"> Free Delivery </label>
			</div>
			</td>
	
	

	

	<%
	}
	%>
	
</table>

</body>
</html>