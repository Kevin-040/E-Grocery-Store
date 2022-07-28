<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<%
		String status = (String)session.getAttribute("Admin");	
		if(status == null || !status.equals("1")){
			response.sendRedirect("adminLogin.jsp?status=2");
		}
	%>
	<%@ page import="java.sql.*" %>

 <%
    String URL = "jdbc:mysql://localhost:3306/grocery_DB";
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String USER = "root";
    String PASS = "";
    Connection conn=null;
    
    try {
    	Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(URL,USER,PASS);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    ResultSet rs = conn.createStatement().executeQuery("Select * from category");
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/sign_up.css">
<title>Add New Product</title>
</head>
<body>
	
	<div id="Sign-Up"> 
		<fieldset style="width:30%; color:white;">
			<legend>Add a new Product</legend> 
				<table class="center" border="0"> <tr> 
					<form method="GET" action="AddProduct"> 
						<td>Product Name</td>
						<td> <input type="text" name="pname"></td>
 						</tr> 
 						<tr> <td>Category</td>
 						<td> <select name="cat">
					<%while(rs.next()){
						String cat = rs.getString("category_name");
						int id = rs.getInt("category_id");
						%><option value="<%=id %>"> <%=cat %></option>
					<%}%>
				</select></td> </tr>
 						<tr> <td>Price</td>
 						<td> <input type="number" name="price" step="0.01"></td> </tr>
 						<tr> <td>Product Image file name</td><td>
 						<input type="text" name="img"></td> </tr>
 						<tr> <td>Product Description </td>
 						<td><textarea name="desc"></textarea></td> </tr> 
						 <tr> <td><input type="Submit" value="Add product"/>
						<input type="reset" value="reset"/></td> </tr> 
					 </form> 
 				</table> 
 		</fieldset> 
	
	
 	</div>
	<a href="adminPage.jsp"><button>Back</button></a>
</body>
</html>
