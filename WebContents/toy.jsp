<%@page import="java.util.*"%>

<%@page import="model.Toy"%>
<%@page import="model.User"%>
<%@page import="model.Comment"%>
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


<script type="text/javascript">
	
</script>

</head>
<body>


	<%
	System.out.println("In toy.jsp");
	con = getConnection();

	String uname = "undefined";
	String prod = "";
	boolean commentExists = false;
	boolean userBuyed = false;
	ArrayList<Comment> comments = new ArrayList<>();

	if (session.getAttribute("username") == null) {
		request.setAttribute("err", "Please login first.");
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	} else {
		uname = session.getAttribute("username").toString();
	}

	if (request.getAttribute("prod") != null) {
		prod = request.getAttribute("prod").toString();
	}
	System.out.println("In toy: " + prod);

	Toy toy = Dao.getToy(prod, con);
	String toypath = "";
	if (toy != null) {
		System.out.println("toy not null");
		toypath = "toysimages/" + toy.getTypeof() + "/" + toy.getImage();
		comments = Dao.getToyCommentsList(toy.getName(), con);
		for (Comment c : comments) {

			if (c.getUser().equals(uname)) {
		userBuyed = true;
			}
			if (c.getUser().equals(uname) && c.getComment().isEmpty()) {
		commentExists = true;
		break;
			}
		}

	} else {
		System.out.println("toy is null");
		RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
		rd.forward(request, response);
	}
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
	<%
	if (toy != null) {
	%>
	<H2 >
		Toy Name:
		<%=toy.getName()%>
	</H2>




	<%
	if (userBuyed) {
	%>


	<form action="ToyServlet" method="get" target="_blank">



		<div>
		<table>
		<tr>
			<td><img src=<%=toypath%> height="220" width="320"></td>
			<td>
			 	<input type="hidden" name="print" value="print">
				 <input	type="hidden" name="prod" value=<%=toy.getName().replace(" ", "_")%>>
				<input type="hidden" name="user" value=<%=uname%>>
				<h2 style="margin-left:200px;">
					<label>Price: ₹<%=toy.getPrice()%>.00</label><br><br> 
					<label>Seller: <%=toy.getSeller()%></label><br><br> 
					<label><%if (toy.getTypeof().equals("edu")) {%>
					Toy Category: Educational 
					<%} else {%>
					Toy Category: <%=toy.getTypeof()%>
					<%}%>
					</label>
			</h2>
		
			</td>
		</tr>
		
		</table>
		<br>
		<Button class="mainbtn" type="submit">Download Order Details Print pdf</Button>
		</div>
		<br>





	</form>



	<%
	} else {
	%>

	<form action="ToyServlet" method="post">

		<div>
		<table>
		<tr>
		<td><img src=<%=toypath%> height="220" width="320"></td>
		<td >
			 <input type="hidden" name="buy" value="buy">
			 <input	type="hidden" name="prod" value=<%=toy.getName().replace(" ", "_")%>>
			<input type="hidden" name="user" value=<%=uname%>>
			<h2 style="margin-left:200px;">
				<label>Price: ₹<%=toy.getPrice()%>.00</label><br><br> 
				<label>Seller: <%=toy.getSeller()%></label><br><br> 
				<label><%if (toy.getTypeof().equals("edu")) {%>
					Toy Category: Educational 
					<%} else {%>
					Toy Category: <%=toy.getTypeof()%>
					<%}%>
					</label>
			</h2>
		
		</td>
		</tr>
			
		</table>
		<br>
		<Button class="mainbtn" type="submit">Buy</Button>
			

		</div>
		<br>


	</form>
	<%
	}
	%>
	<div>
		<%
		if (userBuyed) {
		%>
		<h4>You have brought the product.</h4>
		<%
		}
		%>
		<h3>Review Section</h3>
		<div>
			<%
			if (commentExists) {
			%>
			<form action="ToyServlet" method="post">
				<input type="hidden" name="prod"
					value=<%=toy.getName().replace(" ", "_")%>> <input
					type="hidden" name="user" value=<%=uname%>>
				<textarea ncol="10" nrow="5" name="review"></textarea>
				<button class="mainbtn"  type="submit">Post</button>
			</form>
			<%
			}
			%>
		</div>
		<%
		for (int i = 0; i < comments.size(); i++) {
			if (comments.get(i).getComment().length() > 0) {
		%>
		<div class="divheader">
			<label>Buyer Name: <%=Dao.getUser(comments.get(i).getUser(), con).getFullname()%></label><br>
			<label>Review: <%=comments.get(i).getComment()%></label>

		</div>
		<br>
		<%
		}
		}
		%>

	</div>

	<%
	}
	%>

</body>
</html>