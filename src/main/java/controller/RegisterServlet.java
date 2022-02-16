package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import dao.Dao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	
Connection con;
	
	
	
    private Connection getConnection() {
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
    }


	

	

		

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String fname = request.getParameter("fname").toString();
			String uname = request.getParameter("uname").toString();
			String address = request.getParameter("address").toString();
			long mob = Long.parseLong(request.getParameter("mob").toString());
			String gender = request.getParameter("gender").toString();
			String pass = request.getParameter("password").toString();
			con=getConnection();
			if(Dao.userCheck(uname,pass,con)) {
				//UserExists
				request.setAttribute("err", "User already exists please check your username.");
				redirectTo("/register.jsp",request,response);
			}
			else {
				//userdoes'nt exits
				
				Dao.addUser(new User(fname,uname,pass,address,gender,mob), con);
				redirectTo("/login.jsp",request,response);
			}
			
		}
		
		public void redirectTo(String url ,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);	
		}
		
		
		
		
	

}
