package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import project.model.Comment;
import project.model.Video;

public class CommentsDAO implements IDAO {

	public static CommentsDAO instance = new CommentsDAO();
	
	@Override
	public Object get(Object o) {
		int	id = (Integer) o;
	    Connection conn = ConnectionManager.getConnection();
	    
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	
	    try {
            String query = "SELECT * FROM comments WHERE id = ?";

            pstmt = conn.prepareStatement(query);
            int index = 1;
            pstmt.setInt(index++, id);
            System.out.println(pstmt);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                index = 2;
                String content= rset.getString(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int videoID = rset.getInt(index++);
                boolean deleted = rset.getBoolean(index++);

                return new Comment(id, content, created, ownerUserName, videoID, deleted, CommentRatesDAO.instance.getLikes(id), CommentRatesDAO.instance.getDislikes(id));
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
	    	String query = "SELECT * FROM comments";
	           
            pstmt = conn.prepareStatement(query);
            System.out.println(pstmt);

            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	int id = rset.getInt(index++);
            	String content= rset.getString(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int videoID = rset.getInt(index++);
                boolean deleted = rset.getBoolean(index++);
                
               Comment comm = new Comment(id, content, created, ownerUserName, videoID, deleted, CommentRatesDAO.instance.getLikes(id), CommentRatesDAO.instance.getDislikes(id));
                 
                comments.add(c.cast(comm));     
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
		Comment comm = (Comment) o;
		Video v = (Video)(VideosDAO.instance.get(comm.getVideoID()));
		if(!v.isComments()) {
			return false;
		}
		  
		Connection conn = ConnectionManager.getConnection();
        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO comments (content, date, owner, videoID, deleted) VALUES (?, ?, ?, ?, 0)";

            pstmt = conn.prepareStatement(query);
            int index = 1;
            pstmt.setString(index++, comm.getContent());
            java.sql.Date date = new java.sql.Date(comm.getCreated().getTime());
            pstmt.setDate(index++, date);
            pstmt.setString(index++, comm.getOwnerUserName());
            pstmt.setInt(index++, comm.getVideoID());
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
		Comment comm = (Comment) o;
		
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE comments SET content = ?, date= ?, owner = ?, videoID = ?, deleted = ? WHERE id = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			 
            pstmt.setString(index++, comm.getContent());
            java.sql.Date date = new java.sql.Date(comm.getCreated().getTime());
            pstmt.setDate(index++, date);
            pstmt.setString(index++, comm.getOwnerUserName());
            pstmt.setInt(index++, comm.getVideoID());
            pstmt.setBoolean(index++, comm.isDeleted());
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
			String query = "UPDATE comments SET  deleted = 1 WHERE id = ?";
			
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
	
	public ArrayList<Comment> getFrom(String userName) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Comment> comments = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM comments WHERE owner = ?";
	           
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userName);
            System.out.println(pstmt);

            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	int id = rset.getInt(index++);
            	String content= rset.getString(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int videoID = rset.getInt(index++);
                boolean deleted = rset.getBoolean(index++);
                
               Comment comm = new Comment(id, content, created, ownerUserName, videoID, deleted, CommentRatesDAO.instance.getLikes(id), CommentRatesDAO.instance.getDislikes(id));
                 
                comments.add(comm);     
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
	
	public ArrayList<Comment> getFor(int videoID) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Comment> comments = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM comments WHERE videoID = ?";
	           
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, videoID);
            System.out.println(pstmt);

            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	int id = rset.getInt(index++);
            	String content= rset.getString(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int video = rset.getInt(index++);
                boolean deleted = rset.getBoolean(index++);
                
               Comment comm = new Comment(id, content, created, ownerUserName, video, deleted, CommentRatesDAO.instance.getLikes(id), CommentRatesDAO.instance.getDislikes(id));
                 
                comments.add(comm);     
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

	
}
