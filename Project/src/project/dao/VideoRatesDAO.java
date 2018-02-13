package project.dao;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import project.model.VideoRate;

/**
 * Created by jovan on 30-Jan-18.
 */
public class VideoRatesDAO  implements IDAO {
	public static VideoRatesDAO instance = new VideoRatesDAO();
	
	@Override
	public Object get(Object o) {
		int	id = (Integer) o;
	    Connection conn = ConnectionManager.getConnection();
	    
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	
	    try {
            String query = "SELECT * FROM videorates WHERE id = ?";

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
                int videoID = rset.getInt(index++);
                

                return new VideoRate(id, like, created, ownerUserName, videoID);
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
		ArrayList<T> videos = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM videorates";
	           
            pstmt = conn.prepareStatement(query);
            System.out.println(pstmt);

            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	int id = rset.getInt(index++);
                boolean like = rset.getBoolean(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int videoID = rset.getInt(index++);
                
                VideoRate vr = new VideoRate(id, like, created, ownerUserName, videoID);
                 
                videos.add(c.cast(vr));     
               
            }
	            
        } catch (SQLException ex) {
            System.out.println("Greska u SQL upitu!");
            ex.printStackTrace();

        } finally {
            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
        }
        return videos;
	}

	@Override
	public boolean add(Object o) {
		Connection conn = ConnectionManager.getConnection();
        VideoRate	vr = (VideoRate) o;
        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO videorates (like, date, owner, videoID) VALUES (?, ?, ?, ?)";

            pstmt = conn.prepareStatement(query);
            int index = 1;
            pstmt.setBoolean(index++, vr.isLike());
            java.sql.Date date = new java.sql.Date(vr.getCreated().getTime());
            pstmt.setDate(index++, date);
            pstmt.setString(index++, vr.getOwnerUserName());
            pstmt.setInt(index++, vr.getVideoID());
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
		VideoRate vr = (VideoRate) o;
		
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE videorates SET like = ?, date = ?, owner = ?, videoID = ? WHERE id = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			 
            pstmt.setBoolean(index++, vr.isLike());
            java.sql.Date date = new java.sql.Date(vr.getCreated().getTime());
            pstmt.setDate(index++, date);
            
            pstmt.setString(index++, vr.getOwnerUserName());
            pstmt.setInt(index++, vr.getVideoID());
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
			String query = "UPDATE videorates SET  deleted = 1 WHERE id = ?";
			
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
		ArrayList<Integer> videos = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM videorates WHERE owner = ?";
	           
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
                int videoID = rset.getInt(index++);
                
                VideoRate vr = new VideoRate(id, like, created, ownerUserName, videoID);
                 
                videos.add(vr.getId());     
               
            }
	            
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	        }
	        return videos;
	}
    
    public List<Integer> getIDs(int videoID) {
    	Connection conn = ConnectionManager.getConnection();
		ArrayList<Integer> videos = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM videorates WHERE videoID = ?";
	           
            pstmt = conn.prepareStatement(query);
            System.out.println(pstmt);
            pstmt.setInt(1, videoID);
            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	int id = rset.getInt(index++);
                boolean like = rset.getBoolean(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int video = rset.getInt(index++);
                
                VideoRate vr = new VideoRate(id, like, created, ownerUserName, video);
                 
                videos.add(vr.getId());     
               
            }
	            
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	        }
	        return videos;
    }
    
    public int getLikes(int videoID) {
    	Connection conn = ConnectionManager.getConnection();
		ArrayList<Integer> videos = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM videorates WHERE (videoID = ? AND videorates.like = 1)";
	           
            pstmt = conn.prepareStatement(query);
           
            pstmt.setInt(1, videoID);
            System.out.println(pstmt);
            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	int id = rset.getInt(index++);
                boolean like = rset.getBoolean(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int video = rset.getInt(index++);
                
                VideoRate vr = new VideoRate(id, like, created, ownerUserName, video);
                 
                videos.add(vr.getId());     
               
            }
	            
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	        }
	    	System.out.println(videos.size());
	        return videos.size();
    }
    
    public int getDislikes(int videoID) {
    	Connection conn = ConnectionManager.getConnection();
		ArrayList<Integer> videos = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM videorates WHERE (videoID = ? AND videorates.like = 0)";
	           
            pstmt = conn.prepareStatement(query);
           
            pstmt.setInt(1, videoID);
            System.out.println(pstmt);
            rset = pstmt.executeQuery();

            while(rset.next()) {
            	int index = 1;
            	int id = rset.getInt(index++);
                boolean like = rset.getBoolean(index++);
                Date created = rset.getDate(index++);
                String ownerUserName = rset.getString(index++);
                int video = rset.getInt(index++);
                
                VideoRate vr = new VideoRate(id, like, created, ownerUserName, video);
                 
                videos.add(vr.getId());     
               
            }
	            
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	        }
	    	System.out.println(videos.size());
	        return videos.size();
    }

	public String getUsersRate(int videoID, String userName) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Integer> videos = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	    String res = "none";
	    try {
	    	String query = "SELECT videorates.like FROM videorates WHERE (videoID = ? AND owner = ?)";
	        
            pstmt = conn.prepareStatement(query);
           
            pstmt.setInt(1, videoID);
            pstmt.setString(2, userName);
            System.out.println(pstmt);
            rset = pstmt.executeQuery();

            if(rset.next()) {
            	boolean like = rset.getBoolean(1);
                if(like) res = "like";
                else res =  "dislike";
            } else res = "none";
	            
	        } catch (SQLException ex) {
	            System.out.println("Greska u SQL upitu!");
	            ex.printStackTrace();

	        } finally {
	            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	        }
	    	System.out.println(videos.size());
	        return res;
	}
}
