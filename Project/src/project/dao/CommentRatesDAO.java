package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import project.model.CommentRate;

public class CommentRatesDAO implements IDAO {
	public static CommentRatesDAO instance = new CommentRatesDAO();
	
	@Override
	public Object get(Object o) {
		int	id = (Integer) o;
	    Connection conn = ConnectionManager.getConnection();
	    
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	
	    try {
            String query = "SELECT * FROM commentrates WHERE id = ?";

            pstmt = conn.prepareStatement(query);
            int index = 1;
            pstmt.setInt(index++, id);
            System.out.println(pstmt);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                index = 2;
                boolean like = rset.getBoolean(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int commentID = rset.getInt(index++);
                

                return new CommentRate(id, like, created, ownerUserName, commentID);
            }

        } catch (SQLException ex) {
            System.out.println("Greska u SQL upitu!");
            ex.printStackTrace();

        } finally {
            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
        }
        return null;
	}

	@Override
	public <T> ArrayList<T> getAll(Class<T> c) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<T> comments = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM commentrates";
	           
            pstmt = conn.prepareStatement(query);
            System.out.println(pstmt);

            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	int id = rset.getInt(index++);
                boolean like = rset.getBoolean(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int commentID = rset.getInt(index++);
                
                CommentRate cr = new CommentRate(id, like, created, ownerUserName, commentID);
                 
                comments.add(c.cast(cr));     
               
            }
	            
        } catch (SQLException ex) {
            System.out.println("Greska u SQL upitu!");
            ex.printStackTrace();

        } finally {
            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
        }
        return comments;
	}

	@Override
	public boolean add(Object o) {
		CommentRate	cr = (CommentRate) o;
		Connection conn = ConnectionManager.getConnection();
        
        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO commentrates (like, date, owner, commentID) VALUES (?, ?, ?, ?) ";

            pstmt = conn.prepareStatement(query);
            int index = 1;
            pstmt.setBoolean(index++, cr.isLike());
            java.sql.Date date = new java.sql.Date(cr.getCreated().getTime());
            pstmt.setDate(index++, date);
            pstmt.setString(index++, cr.getOwnerUserName());
            pstmt.setInt(index++, cr.getCommentID());
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

	@Override
	public boolean update(Object o, Object identificator) {
		Connection conn = ConnectionManager.getConnection();
		int id = (Integer) identificator;
		CommentRate cr = (CommentRate) o;
		
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE commentrates SET like = ?, date = ?, owner = ?, commentID = ? WHERE id = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			 
            pstmt.setBoolean(index++, cr.isLike());
            java.sql.Date date = new java.sql.Date(cr.getCreated().getTime());
            pstmt.setDate(index++, date);
            
            pstmt.setString(index++, cr.getOwnerUserName());
            pstmt.setInt(index++, cr.getCommentID());
            pstmt.setInt(index++, id);
			
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

	@Override
	public boolean delete(Object o) {
		Connection conn = ConnectionManager.getConnection();
		int id= (Integer) o;
		
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE commentrates SET  deleted = 1 WHERE id = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
	        pstmt.setInt(index++, id);
			
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
	
    public List<Integer> getIDs(String userName) {
    	Connection conn = ConnectionManager.getConnection();
		ArrayList<Integer> comments = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM commentrates WHERE owner = ?";
	           
            pstmt = conn.prepareStatement(query);
           
            pstmt.setString(1, userName);
            System.out.println(pstmt);
            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	int id = rset.getInt(index++);
                boolean like = rset.getBoolean(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int commentID = rset.getInt(index++);
                
                CommentRate cr = new CommentRate(id, like, created, ownerUserName, commentID);
                 
                comments.add(cr.getId());     
               
            }
	            
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	        }
	        return comments;
	}
    
    public List<Integer> getIDs(int commentID) {
    	Connection conn = ConnectionManager.getConnection();
		ArrayList<Integer> comments = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM commentrates WHERE commentID = ?";
	           
            pstmt = conn.prepareStatement(query);
            System.out.println(pstmt);
            pstmt.setInt(1, commentID);
            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	int id = rset.getInt(index++);
                boolean like = rset.getBoolean(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int comment = rset.getInt(index++);
                
                CommentRate cr = new CommentRate(id, like, created, ownerUserName, comment);
                 
                comments.add(cr.getId());     
               
            }
	            
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	        }
	        return comments;
    }
    
    public int getLikes(int commentID) {
    	Connection conn = ConnectionManager.getConnection();
		ArrayList<Integer> comments = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM commentrates WHERE (commentID = ? AND commentrates.like = 1);";
	           
            pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, commentID);
            System.out.println(pstmt);
            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	int id = rset.getInt(index++);
                boolean like = rset.getBoolean(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int comment = rset.getInt(index++);
                
                CommentRate cr = new CommentRate(id, like, created, ownerUserName, comment);
                 
                comments.add(cr.getId());     
               
            }
	            
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	        }
	        return comments.size();
	}
	
	public int getDislikes(int commentID) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Integer> comments = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM commentrates WHERE commentID = ? AND commentrates.like = 0 ";
	           
            pstmt = conn.prepareStatement(query);
            System.out.println(pstmt);
            pstmt.setInt(1, commentID);
            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	int id = rset.getInt(index++);
                boolean like = rset.getBoolean(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int comment = rset.getInt(index++);
                
                CommentRate cr = new CommentRate(id, like, created, ownerUserName, comment);
                 
                comments.add(cr.getId());     
               
            }
	            
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	        }
	    if(comments == null) return 0;
        else return comments.size();
	}
}
