package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.model.User;

public class FollowingDAO {
	public static ArrayList<String> getFollowedBy(String userName) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<String> users = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT followedUser FROM following WHERE followingUser = ?";
	           
            pstmt = conn.prepareStatement(query);
            System.out.println(pstmt);
            pstmt.setString(1, userName);
            rset = pstmt.executeQuery();

            while(rset.next()) {
            	String user = rset.getString(1);
                 
                users.add(user);     
               
            }
	            
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	        }
	        return users;
	}
	
	public static boolean get(String followingUser, String followedUser) {
		Connection conn = ConnectionManager.getConnection();

	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT followedUser FROM following WHERE followingUser = ? AND followedUser = ?";
	           
            pstmt = conn.prepareStatement(query);
            System.out.println(pstmt);
            pstmt.setString(1, followingUser);
            pstmt.setString(2, followedUser);
            rset = pstmt.executeQuery();

            if(rset.next()) {
            	return true;   
            } return false;
	            
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	        }
	        return false;
	}
	
	public static boolean delete(String followingUser, String followedUser) {
		Connection conn = ConnectionManager.getConnection();

	    PreparedStatement pstmt = null;

	    try {
	    	String query = "DELETE FROM following WHERE followingUser = ? AND followedUser = ?";
	           
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, followingUser);
            pstmt.setString(2, followedUser);
            System.out.println(pstmt);


           
            return pstmt.executeUpdate() == 1;
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();
	            return false;

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            
	        }
	}
	
	public static ArrayList<String> getFollwoing(String userName) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<String> users = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT followingUser FROM following WHERE followedUser = ?";
	           
            pstmt = conn.prepareStatement(query);
           
            pstmt.setString(1, userName);
            System.out.println(pstmt);
            rset = pstmt.executeQuery();

            while(rset.next()) {
            	String user = rset.getString(1);
                 
                users.add(user);     
               
            }
	            
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	        }
	        return users;
	}
	
	public static boolean add(String following, String followed) {
		Connection conn = ConnectionManager.getConnection();

        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO following (followingUser, followedUser) VALUES (?, ?)";

            pstmt = conn.prepareStatement(query);
            int index = 1;
            pstmt.setString(index++, following);
            pstmt.setString(index++, followed);
            System.out.println(pstmt);

            return pstmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("Greska u SQL upitu!");
            ex.printStackTrace();
        } finally {

            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
        }

        return false;
	}
}
