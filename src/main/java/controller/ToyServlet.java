package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Comment;
import model.Toy;
import model.User;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import dao.Dao;


/**
 * Servlet implementation class ToyServlet
 */
public class ToyServlet extends HttpServlet {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con;
    /**
     * Default constructor. 
     */
    public ToyServlet() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse responseponse)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	System.out.println("In Do get toy servletttttttttttt");
        if (request.getParameter("print") != null) {
            con = getConnection();
            User user = Dao.getUser(request.getParameter("user").toString(), con);
            Toy toy = Dao.getToy(request.getParameter("prod").toString().replace("_", " "), con);
            String FILE_NAME = getServletContext().getRealPath("/orderpdf" + File.separator + "OrderDetails.pdf");
            File file = new File(FILE_NAME);
            if (file.exists()) {
            
                System.out.println("file exists:" + FILE_NAME);
                Document document = new Document();

                try {

                    PdfWriter.getInstance(document, new FileOutputStream(file)).setPageEmpty(true);

                    //open
                    document.open();

                    String top = "Toys Shopping\r\nOrder Details";

                    Font fheader = new Font();
                    fheader.setStyle(Font.BOLD);
                    fheader.setSize(20);
                    Paragraph p = new Paragraph();
                    p.add(top);
                    p.setFont(fheader);
                    p.setAlignment(Element.ALIGN_CENTER);
                    document.add(p);

                    String details = "\n\nUser Details: \nFull Name: " + user.getFullname() +
                        "\nPhone Number: " + user.getMob() +
                        "\nShipping Address: " + user.getAddress() +
                        "\n\n\nProduct Details: \nProduct name: " + toy.getName() +
                        "\nSeller: " + toy.getSeller() +
                        "\nToy Category: " + toy.getTypeof() +
                        "\nPrice: ₹" + toy.getPrice() +".00"+
                        "\nDelivery: Free";

                    Paragraph p2 = new Paragraph();
                    p2.add(details); //no alignment
                    document.add(p2);

                    Font f = new Font();
                    f.setStyle(Font.BOLD);
                    f.setSize(15);

                    Paragraph p3 = new Paragraph();
                    p3.add("Grand Total: ₹" + toy.getPrice()+".00");
                    p3.setAlignment(Element.ALIGN_RIGHT);
                    p3.setFont(f);
                    document.add(p3);

                    //close
                    document.close();

                    
                    response.setContentType("application/pdf");
                    response.setHeader("Expiresponse", "0");
                    response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
                    response.setHeader("Content-Disposition","inline;filename=OrderDetails-" + toy.getName()+".pdf");
                    response.setHeader("Accept-Ranges", "bytes");
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    ServletOutputStream sos = response.getOutputStream();
                    byte[] buffer = new byte[2048];
                    while (true) {
                      int bytesRead = bis.read(buffer, 0, buffer.length);
                      if (bytesRead < 0) {
                        break;
                      }
                    sos.write(buffer, 0, bytesRead);
                    sos.flush();
                    }
                    sos.flush();
                    bis.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else {
            	System.out.println("Weong path the file doesnt exits:"+file.getPath());
            }
        }

    }




    public void redirectTo(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }


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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	if(request.getParameter("review")!=null) {
    		Comment com = new Comment(request.getParameter("user").toString(),request.getParameter("prod").toString().replace("_", " "),request.getParameter("review").toString());
    		 con=getConnection();
    		Dao.updateComment(com, con);
    		request.setAttribute("prod", com.getProd());
    		redirectTo("/toy.jsp",request,response);
    	}
    	
    	if(request.getParameter("logout")!=null) {
			session.setAttribute("username", "");
			session.setAttribute("category", "girl");
			redirectTo("/login.jsp",request,response);
		}
    	  if (request.getParameter("buy") != null) {
              con = getConnection();
              User user = Dao.getUser(request.getParameter("user").toString(), con);
              Toy toy = Dao.getToy(request.getParameter("prod").toString().replace("_", " "), con);
              boolean comres = false;
              	ArrayList<Comment> com = Dao.getToyCommentsList(toy.getName(),con); 
              	for(Comment c :com) {
              		if(c.getUser().equals(user.getUsername())) {
              			comres=false;
              			break;
              		}
              		else {comres = true;}
              	
              	}
              	if(comres || com.size()==0) {
              		Dao.addComment(new Comment(user.getUsername(),toy.getName(),""),con);
              		request.setAttribute("prod", toy.getName());
              		redirectTo("toy.jsp",request,response);
              	}
              	
              
    	  }
              	
    	
    	
    
    	
    }
   


}