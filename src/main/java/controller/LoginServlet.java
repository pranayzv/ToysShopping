package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import dao.Dao;
import jakarta.servlet.http.*;
import jakarta.servlet.*;


public class LoginServlet extends HttpServlet {
	

    /**
	 * 
	 */
	
	
	
	private static final long serialVersionUID = 1828656851528113526L;


	public LoginServlet() {

    }
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
		
		con = getConnection();
		
		String u = request.getParameter("uname").toString();
		String p = request.getParameter("pass").toString();
		if(Dao.userCheck(u,p,con)) {
			
			HttpSession session = request.getSession();
			session.setAttribute("username", u);
			redirectTo("/main.jsp",request,response);
			
		}
		else {
			
				//userdoes'nt exits
				request.setAttribute("err", "User does'nt exists please check your credentials or create a new account.");
				redirectTo("/login.jsp",request,response);
		}
		
		
	}
	
	public void redirectTo(String url ,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);	
	}
	

}
