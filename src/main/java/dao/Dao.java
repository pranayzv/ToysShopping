package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;
import model.Toy;
import model.Comment;
import utils.Constants.*;

public class Dao {
	
	

	
	public static ArrayList<User> getUserList(String u,Connection con) {
		ArrayList<User> user = new ArrayList<User>();
		 String query = " SELECT * FROM "+utils.Constants.USERTABLE;
         Statement st;
     try {
         st = con.createStatement();
         ResultSet rs = st.executeQuery(query);
         
         while(rs.next()){
         	
             if(userCheck(u,rs.getString("username"), con)){
                 user.add(new User(rs.getString("fname"),rs.getString("username"),"" ,rs.getString("address"), rs.getString("gender") ,rs.getLong("mob")));
             }
         }
     } catch (SQLException ex) {
         
     }
     return user;
	}
	
	
	public static User getUser(String usr,Connection con){
        String query = " SELECT * FROM "+utils.Constants.USERTABLE;
            Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
            	
                if(rs.getString("username").equals(usr)){
                    return new User(rs.getString("fname"),rs.getString("username"),"" ,rs.getString("address"), rs.getString("gender") ,rs.getLong("mob"));
                }
            }
        } catch (SQLException ex) {
            
        }
        return null;
    }

	public static boolean userCheck(String usr, String pass,Connection con){
	        String query = " SELECT * FROM "+utils.Constants.USERTABLE;
	            Statement st;
	        try {
	            st = con.createStatement();
	            ResultSet rs = st.executeQuery(query);
	            
	            while(rs.next()){
	            	
	                if(rs.getString("username").equals(usr) && rs.getString("pass").equals(pass)){
	                    return true;
	                }
	            }
	        } catch (SQLException ex) {
	            
	        }
	        return false;
	    }
	
	
	
	
	
	public static void addUser(User e,Connection con){
		
		try {
			PreparedStatement ps = con.prepareStatement(utils.Constants.USERINSERT);
			ps.setString(1,e.getFullname());
			ps.setString(2,e.getUsername());
			ps.setString(3,e.getPassword());
			ps.setString(4,e.getAddress());
			ps.setString(5,e.getGender());
			ps.setLong(6,e.getMob());			
			ps.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
	}
	
	
	public static ArrayList<Toy> getToyList(String u,Connection con) {
		ArrayList<Toy> toy = new ArrayList<Toy>();
		 String query = " SELECT * FROM "+utils.Constants.TOYTABLE;
         Statement st;
     try {
         st = con.createStatement();
         ResultSet rs = st.executeQuery(query);
         
         while(rs.next()){
                toy.add(new Toy(rs.getString("name"),Long.parseLong(rs.getString("price").toString()),rs.getString("seller"), rs.getString("typeof") ,rs.getString("image")));
         }
     } catch (SQLException ex) {
         
     }
     return toy;
	}
	
	
	public static Toy getToy(String usr,Connection con){
        String query = " SELECT * FROM "+utils.Constants.TOYTABLE;
            Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                if(rs.getString("name").equals(usr)){
                    return new Toy(rs.getString("name"),Long.parseLong(rs.getString("price").toString()),rs.getString("seller"), rs.getString("typeof") ,rs.getString("image"));
                }
            }
        } catch (SQLException ex) {
            
        }
        return null;
    }
	
	
	public static void addComment(Comment com,Connection con){
		
		try {
			PreparedStatement ps = con.prepareStatement(utils.Constants.COMMENTINSERT);
			ps.setString(1,com.getUser());
			ps.setString(2,com.getProd());
			ps.setString(3,com.getComment());
			ps.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
	}
	
public static void updateComment(Comment com,Connection con){
	String query = "update "+utils.Constants.COMMENTTABLE+" set review='"+com.getComment()+"' where username='"+com.getUser()+"' and prod='"+com.getProd()+"' ;";
	Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate(query);
			  
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
	}
	
	public static ArrayList<Comment> getToyCommentsList(String p,Connection con) {
		ArrayList<Comment> comm = new ArrayList<Comment>();
		 String query = " SELECT * FROM "+utils.Constants.COMMENTTABLE;
         Statement st;
     try {
         st = con.createStatement();
         ResultSet rs = st.executeQuery(query);
         
         while(rs.next()){
        	 	if(rs.getString("prod").equals(p)) {
        	 		comm.add(new Comment(rs.getString("username"),rs.getString("prod"), rs.getString("review")));
        	 	}
         }
     } catch (SQLException ex) {
         
     }
     return comm;
	}
	
	

}
