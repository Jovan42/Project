package project.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.dao.FollowingDAO;
import project.dao.UsersDAO;
import project.model.User;

/**
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		Map<String, Object> data = new HashMap<>();
		HttpSession session = request.getSession();
		User loggedInUser = null;
		boolean prati = false;
		try {
			loggedInUser = (User) session.getAttribute("loggedInUser");
			data.put("user", loggedInUser.getUserName());
			data.put("role", loggedInUser.isAdmin());
			ArrayList<String> list = FollowingDAO.getFollowedBy(loggedInUser.getUserName());
			
			for(String user : list) {
				if(user.equals(userName)) prati = true;
			}
			
			
		} catch (Exception e) {
			data.put("user", "Guest");
		}
		
		data.put("prati", prati);
		User profile = (User)UsersDAO.instance.get(userName);
		if(profile == null) {response.sendRedirect("./main.html"); return;}
		if((profile.isDeleted() || profile.isBanned()) && !loggedInUser.isAdmin()) {data.put("msg", "NotFound"); return;}
		
		
		profile.setPassword("");
		data.put("profile", profile);
		//4442939720618123
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
