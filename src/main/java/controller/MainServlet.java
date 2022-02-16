package controller;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

public class MainServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -215663465816718548L;
	public Connection con;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}
	
	public Connection getConnection() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		con=getConnection();
		System.out.println("in doPost");
		
		if(request.getParameter("emp")!=null) {
			String cmp = request.getParameter("emp").toString();
		}
		
		if(request.getParameter("logout")!=null) {
			session.setAttribute("username", "");
			session.setAttribute("category", "girl");
			redirectTo("/login.jsp",request,response);
		}
		
		if(request.getParameter("category")!=null) {
			String category = request.getParameter("category").toString();
			session.setAttribute("category", category);
			redirectTo("/main.jsp",request,response);
		}
		
		log("checking for prod");
		if(request.getParameter("prod")!=null) {
			String prod = request.getParameter("prod").toString().replace("_", " ");
			request.setAttribute("prod",prod);
			log("prod: "+prod);
			if(prod.length()>0) {
				redirectTo("/toy.jsp",request,response);
			}
			else {
				redirectTo("/main.jsp",request,response);
			}
		}
		
		

		
		
		
		
		
	}
	
	public void redirectTo(String url ,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		System.out.println("redirecting to:"+url);
		rd.forward(request, response);	
	}
	
	public void log(String s) {
		System.out.println(s);	
	}
	
	public boolean userCheck(String u, String p){
		return false;
	}

}
