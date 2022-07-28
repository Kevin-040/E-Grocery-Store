

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class groceryServlet
 */
@WebServlet("/groceryServlet")
public class groceryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final String URL = "jdbc:mysql://localhost:3306/Grocery_DB";
	    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	    private static final String USER = "root";
	    private static final String PASS = "";
	    java.sql.Connection conn;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public groceryServlet() {
        super();
        try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(URL,USER,PASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String name = request.getParameter("name");
			String cid = request.getParameter("cid");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			int id = Integer.parseInt(request.getParameter("id"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			
			String query = "INSERT INTO customer_details (customer_id, Name, Phone_No, Email_ID, Address) VALUES (?, ?, ?, ?, ?)";
			
			String query1 = "INSERT INTO order_details (quantity, prod_ID, cust_ID) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, cid);
			ps.setString(2, name);
			ps.setString(3, phone);
			ps.setString(4, email);
			ps.setString(5, address);
			
			ps.executeUpdate();

			PreparedStatement ps2 = conn.prepareStatement(query1);
			ps2.setInt(1, quantity);
			ps2.setInt(2, id);
			ps2.setString(3, cid);
			ps2.executeUpdate();
                        
			response.sendRedirect("Thankyou.html");
			//System.out.println("Hi");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
