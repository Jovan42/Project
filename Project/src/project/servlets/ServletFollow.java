package project.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.dao.FollowingDAO;
import project.model.User;

/**
 * Servlet implementation class ServletFollow
 */
public class ServletFollow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFollow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String profile = request.getParameter("userName");
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");		
		Map<String, Object> data = new HashMap<>();
		
		if(loggedInUser == null) {data.put("msg", "fail"); return; }
		if(FollowingDAO.get(loggedInUser.getUserName(), profile)) { FollowingDAO.delete(loggedInUser.getUserName(), profile);}
		else { FollowingDAO.add(loggedInUser.getUserName(),	profile);}
		
		
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
