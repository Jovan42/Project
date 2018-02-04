package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import project.model.User;

public class UsersDAO implements IDAO{
	public static UsersDAO instance = new UsersDAO();
	
	@Override
	public Object get(Object o) {
		String userName = (String) o;
	    Connection conn = ConnectionManager.getConnection();
	    
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	
	    try {
            String query = "SELECT * FROM users WHERE userName = ?";

            pstmt = conn.prepareStatement(query);
            int index = 1;
            pstmt.setString(index++, userName);
            System.out.println(pstmt);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                index = 2;
                String password = rset.getString(index++);
                String firstName = rset.getString(index++);
                String lastName = rset.getString(index++);
                String eMail = rset.getString(index++);
                String description = rset.getString(index++);
                Date created = rset.getDate(index++);
                boolean admin = rset.getBoolean(index++);
                boolean banned = rset.getBoolean(index++);
                boolean deleted = rset.getBoolean(index++);
                List<String> followersId = FollowingDAO.getFollwoing(userName);
                List<Integer> commentRatesId = CommentRatesDAO.instance.getIDs(userName);
                List<Integer> videoRatesID = VideoRatesDAO.instance.getIDs(userName);

                return new User(userName, password, firstName, lastName, eMail, description, created, admin, banned,
                        followersId, commentRatesId, videoRatesID, deleted);
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
	public <T>ArrayList<T> getAll(Class<T> c) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<T> users = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	    	String query = "SELECT * FROM users";
	           
	            pstmt = conn.prepareStatement(query);
	            System.out.println(pstmt);

	            rset = pstmt.executeQuery();

	            while(rset.next()) {
	                int index = 1;
	                String userName = rset.getString(index++);
	                String password = rset.getString(index++);
	                String firstName = rset.getString(index++);
	                String lastName = rset.getString(index++);
	                String eMail = rset.getString(index++);
	                String description = rset.getString(index++);
	                Date created = rset.getDate(index++);
	                boolean admin = rset.getBoolean(index++);
	                boolean banned = rset.getBoolean(index++);
	                boolean deleted = rset.getBoolean(index++);
	                List<String> followersId = FollowingDAO.getFollwoing(userName);
	                List<Integer> commentRatesId = CommentRatesDAO.instance.getIDs(userName);
	                List<Integer> videoRatesID = VideoRatesDAO.instance.getIDs(userName);
	                
	                User u = new User(userName, password, firstName, lastName, eMail, description, created, admin, banned,
	                        followersId, commentRatesId, videoRatesID, deleted);
	                System.out.println(u);
	                users.add(c.cast(u));
	                
	               
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
	
	@Override
	public boolean add(Object o) {
		Connection conn = ConnectionManager.getConnection();
        User user = (User) o;
        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO users (userName, password, firstName, lastName, eMail, description, date, role," +
                    " banned, deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(query);
            int index = 1;
            pstmt.setString(index++, user.getUserName());
            pstmt.setString(index++, user.getPassword());
            pstmt.setString(index++, user.getFirstName());
            pstmt.setString(index++, user.getLastName());
            pstmt.setString(index++, user.geteMail());
            pstmt.setString(index++, user.getDescription());
            java.sql.Date date = new java.sql.Date(user.getCreated().getTime());
            pstmt.setDate(index++, date);
            pstmt.setInt(index++, user.isAdmin()?1:0);
            pstmt.setInt(index++, user.isBanned()? 1:0);
            pstmt.setInt(index++, 0);
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
	public boolean update(Object o, Object userName) {
		Connection conn = ConnectionManager.getConnection();
		String userNameStr = (String) userName;
		User user = (User) o;
		
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE users SET userName = ?, password = ?, firstName = ?, "
					+ "lastName = ?, eMail = ?, description = ?, role = ?, banned = ?, deleted = ? WHERE userName = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, user.getUserName());
	        pstmt.setString(index++, user.getPassword());
	        pstmt.setString(index++, user.getFirstName());
	        pstmt.setString(index++, user.getLastName());
	        pstmt.setString(index++, user.geteMail());
	        pstmt.setString(index++, user.getDescription());
	        pstmt.setInt(index++, user.isAdmin() ? 1:0);
	        pstmt.setInt(index++, user.isBanned() ? 1:0);
	        pstmt.setInt(index++, user.isDeleted() ? 1:0);
	        pstmt.setString(index++, userNameStr);
			
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
		String userNameStr = (String) o;
		
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE users SET  deleted = 1 WHERE userName = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
	        pstmt.setString(index++, userNameStr);
			
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
	
    public boolean checkOnLogIn(String userName, String password) {
        Connection conn = ConnectionManager.getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            String query = "SELECT password FROM users WHERE userName = ?";

            pstmt = conn.prepareStatement(query);
            int index = 1;
            pstmt.setString(index++, userName);
            System.out.println(pstmt);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                String psw = rset.getString(1);
                if (psw.equals(password))
                    return  true;
            }
        } catch (SQLException ex) {
            System.out.println("Greska u SQL upitu!");
            ex.printStackTrace();

        } finally {
            try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
            try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
        }
        return false;
    }

}
