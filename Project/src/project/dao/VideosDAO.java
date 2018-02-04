package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import project.enums.UserRole;
import project.enums.Visibility;
import project.model.User;
import project.model.Video;

public class VideosDAO implements IDAO, IRoleRestricted {

	public static VideosDAO instance = new VideosDAO();
	
	@Override
	public Object get(Object o) {
		int id = (Integer) o;
	    Connection conn = ConnectionManager.getConnection();
	    
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	
	    try {
            String query = "SELECT * FROM videos WHERE id = ?";

            pstmt = conn.prepareStatement(query);
            int index = 1;
            pstmt.setInt(index++, id);
            System.out.println(pstmt);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                index = 2;
                String url = rset.getString(index++);
                String name = rset.getString(index++);
                String thumbnailURL = rset.getString(index++);
                String description = rset.getString(index++);
                int visibility = rset.getInt(index++);
                boolean comments = rset.getBoolean(index++);
                boolean rate = rset.getBoolean(index++);
                boolean blocked = rset.getBoolean(index++);
                int views = rset.getInt(index++);
                Date created = rset.getDate(index++);
                String ownersUserName = rset.getString(index++);
                boolean deleted = rset.getBoolean(index++);
                
                return new Video(id, name, url, thumbnailURL, description, getValfromInt(visibility) , comments, rate, blocked, views,
                		created, ownersUserName, deleted);
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
	    	String query = "SELECT * FROM videos";
	           
	            pstmt = conn.prepareStatement(query);
	            System.out.println(pstmt);

	            rset = pstmt.executeQuery();

	            while(rset.next()) {
	            	int index = 1;
	            	int id = rset.getInt(index++);
	                String url = rset.getString(index++);
	                String name = rset.getString(index++);
	                String thumbnailURL = rset.getString(index++);
	                String description = rset.getString(index++);
	                int visibility = rset.getInt(index++);
	                boolean comments = rset.getBoolean(index++);
	                boolean rate = rset.getBoolean(index++);
	                boolean blocked = rset.getBoolean(index++);
	                int views = rset.getInt(index++);
	                Date created = rset.getDate(index++);
	                String ownersUserName = rset.getString(index++);
	                boolean deleted = rset.getBoolean(index++);
	                
	                Video v = new Video(id, url, name, thumbnailURL, description, getValfromInt(visibility) , comments, rate, blocked, views,
	                		created, ownersUserName, deleted);
	                 
	                videos.add(c.cast(v));     
	               
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
        Video video = (Video) o;
        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO videos (url, name, thumbnail, description, visibility, comments, rate, blocked," +
                    " views, date, owner, deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(query);
            int index = 1;
            pstmt.setString(index++, video.getUrl());
            pstmt.setString(index++, video.getName());
            pstmt.setString(index++, video.getTumbnailURL());
            pstmt.setString(index++, video.getDescription());
            pstmt.setInt(index++, video.getVisibility().getInt());
            pstmt.setBoolean(index++, video.isComments());
            pstmt.setBoolean(index++, video.isRate());
            pstmt.setBoolean(index++, video.isBlocked());
            pstmt.setInt(index++, video.getViews());
            java.sql.Date date = new java.sql.Date(video.getCreated().getTime());
            pstmt.setDate(index++, date);
            pstmt.setString(index++, video.getOwnerUserName());
            pstmt.setBoolean(index++, false);
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
		Video video = (Video) o;
		
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE videos SET url = ?, name = ?, thumbnail = ?, description = ?, visibility = ?, comments = ?, "
					+ "rate = ?, blocked = ?, views = ?, date = ?, owner = ?, deleted = ? WHERE id = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			 
            pstmt.setString(index++, video.getUrl());
            pstmt.setString(index++, video.getName());
            pstmt.setString(index++, video.getTumbnailURL());
            pstmt.setString(index++, video.getDescription());
            pstmt.setInt(index++, video.getVisibility().getInt());
            pstmt.setBoolean(index++, video.isComments());
            pstmt.setBoolean(index++, video.isRate());
            pstmt.setBoolean(index++, video.isBlocked());
            pstmt.setInt(index++, video.getViews());
            java.sql.Date date = new java.sql.Date(video.getCreated().getTime());
            pstmt.setDate(index++, date);
            pstmt.setString(index++, video.getOwnerUserName());
            pstmt.setBoolean(index++, video.isDeleted());
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
			String query = "UPDATE videos SET  deleted = 1 WHERE id = ?";
			
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

	public ArrayList<Video> getFrom(String ownerUserName) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Video> videos = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM videos WHERE owner = ? AND deleted = 0";
	           
	            pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, ownerUserName);
	            rset = pstmt.executeQuery();

	            while(rset.next()) {
	            	int index = 1;
	            	int id = rset.getInt(index++);
	            	String name = rset.getString(index++);
	                String url = rset.getString(index++);
	                String thumbnailURL = rset.getString(index++);
	                String description = rset.getString(index++);
	                int visibility = rset.getInt(index++);
	                boolean comments = rset.getBoolean(index++);
	                boolean rate = rset.getBoolean(index++);
	                boolean blocked = rset.getBoolean(index++);
	                int views = rset.getInt(index++);
	                Date created = rset.getDate(index++);
	                String userName = rset.getString(index++);
	                boolean deleted = rset.getBoolean(index++);
	                
	                Video v = new Video(id, url, name, thumbnailURL, description, getValfromInt(visibility) , comments, rate, blocked, views,
	                		created, userName, deleted);
	                 
	                videos.add(v);     
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
	
	private Visibility getValfromInt(int i) {
		switch (i) {
		case 0:
			return Visibility.PUBLIC;
		case 1:
			return Visibility.UNLISTED;
		case 2:
			return Visibility.UNLISTED;
		default:
			return Visibility.PUBLIC;
		}
	}

	@Override
	public <T> ArrayList<T> get(UserRole role, Object from, Class<T> c) {
		if(role == UserRole.ADMIN) {
			Connection conn = ConnectionManager.getConnection();
			ArrayList<T> videos = new ArrayList<>(); 
			String userName = (String) from;
		    PreparedStatement pstmt = null;
		    ResultSet rset = null;

		    try {
		    	String query = "SELECT * FROM videos WHERE owner = ?";
		           
		            pstmt = conn.prepareStatement(query);
		            pstmt.setString(1, userName);
		            System.out.println(pstmt);

		            rset = pstmt.executeQuery();

		            while(rset.next()) {
		            	int index = 1;
		            	int id = rset.getInt(index++);
		                String url = rset.getString(index++);
		                String name = rset.getString(index++);
		                String thumbnailURL = rset.getString(index++);
		                String description = rset.getString(index++);
		                int visibility = rset.getInt(index++);
		                boolean comments = rset.getBoolean(index++);
		                boolean rate = rset.getBoolean(index++);
		                boolean blocked = rset.getBoolean(index++);
		                int views = rset.getInt(index++);
		                Date created = rset.getDate(index++);
		                String ownersUserName = rset.getString(index++);
		                boolean deleted = rset.getBoolean(index++);
		                 
		                Video v = new Video(id, url, name, thumbnailURL, description, getValfromInt(visibility) , comments, rate, blocked, views,
		                		created, ownersUserName, deleted);
		                 
		                videos.add(c.cast(v));     
		               
		            }
		            
		        } catch (SQLException ex) {
		            System.out.println("Greska u SQL upitu!");
		            ex.printStackTrace();

		        } finally {
		            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		        }
		    return videos;
		} else {
			Connection conn = ConnectionManager.getConnection();
			ArrayList<T> videos = new ArrayList<>(); 
			String userName = (String) from;
		    PreparedStatement pstmt = null;
		    ResultSet rset = null;

		    try {
		    	String query = "SELECT * FROM videos WHERE owner = ? AND visibility != 2";
		           
		            pstmt = conn.prepareStatement(query);
		            pstmt.setString(1, userName);
		            System.out.println(pstmt);

		            rset = pstmt.executeQuery();

		            while(rset.next()) {
		            	int index = 1;
		            	int id = rset.getInt(index++);
		                String url = rset.getString(index++);
		                String name = rset.getString(index++);
		                String thumbnailURL = rset.getString(index++);
		                String description = rset.getString(index++);
		                int visibility = rset.getInt(index++);
		                boolean comments = rset.getBoolean(index++);
		                boolean rate = rset.getBoolean(index++);
		                boolean blocked = rset.getBoolean(index++);
		                int views = rset.getInt(index++);
		                Date created = rset.getDate(index++);
		                String ownersUserName = rset.getString(index++);
		                boolean deleted = rset.getBoolean(index++);
		                
		                Video v = new Video(id, url, name, thumbnailURL, description, getValfromInt(visibility) , comments, rate, blocked, views,
		                		created, ownersUserName, deleted);
		                 
		                videos.add(c.cast(v));     
		               
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
	}

	public ArrayList<Video> getAll(UserRole role) {
		if(role == UserRole.ADMIN) return getAll(Video.class);
		else {
			Connection conn = ConnectionManager.getConnection();
			ArrayList<Video> videos = new ArrayList<>();
		    PreparedStatement pstmt = null;
		    ResultSet rset = null;

		    try {
		    	String query = "SELECT * FROM videos WHERE visibility != 2 AND deleted != 1";
		           
		            pstmt = conn.prepareStatement(query);
		            rset = pstmt.executeQuery();

		            while(rset.next()) {
		            	int index = 1;
		            	int id = rset.getInt(index++);
		                String url = rset.getString(index++);
		                String name = rset.getString(index++);
		                String thumbnailURL = rset.getString(index++);
		                String description = rset.getString(index++);
		                int visibility = rset.getInt(index++);
		                boolean comments = rset.getBoolean(index++);
		                boolean rate = rset.getBoolean(index++);
		                boolean blocked = rset.getBoolean(index++);
		                int views = rset.getInt(index++);
		                Date created = rset.getDate(index++);
		                String userName = rset.getString(index++);
		                boolean deleted = rset.getBoolean(index++);
		                
		                Video v = new Video(id, url, name, thumbnailURL, description, getValfromInt(visibility) , comments, rate, blocked, views,
		                		created, userName, deleted);
		                 
		                videos.add(v);     
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
	}

	public ArrayList<Video> getFor(User user) {
		ArrayList<Video> videos;
		if(user == null) {
			 videos = getAll(UserRole.NON_REGISTERED);
			 return videos;
		} else if (user.isAdmin()) {
			 videos = getAll(UserRole.ADMIN);
			 return videos;
		} else {
			 videos = getAll(UserRole.USER);
			 ArrayList<Video> myVideos = getFrom(user.getUserName());
			 for (Video video : myVideos) {
				 if(video.getVisibility() == Visibility.PRIVATE) videos.add(video);
			 }
			 return videos;
		}
		
		
	}
}
