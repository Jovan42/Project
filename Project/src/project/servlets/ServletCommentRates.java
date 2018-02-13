package project.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.dao.CommentRatesDAO;
import project.dao.CommentsDAO;
import project.dao.VideoRatesDAO;
import project.model.CommentRate;
import project.model.User;

/**
 * Servlet implementation class ServletCommentRates
 */
public class ServletCommentRates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCommentRates() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentID = Integer.parseInt(request.getParameter("commentID"));
		System.out.println(request.getParameter("job"));
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		String userName = loggedInUser == null ? "Guest" : loggedInUser.getUserName();
		Map<String, Object> data = new HashMap<>();
		if(request.getParameter("job") == null) {
			CommentRate cr = CommentRatesDAO.instance.getUsersRate(commentID, userName);
			if(userName.equals("Guest")) {
				data.put("UserRate", "none");
			} else {
				if (cr == null)
					data.put("UserRate", "none");
				else if (cr.isLike()) data.put("UserRate", "like");
				else data.put("UserRate", "dislike");
			}
			
		} else {
			
			String like = request.getParameter("like");
			if(userName.equals("Guest")) {
				data.put("message", "Not looged in");
			} else {
				CommentRate cr =CommentRatesDAO.instance.getUsersRate(commentID, userName);
				if ( cr == null) {
					boolean l = like.equals("true" ) ? true : false;
					CommentRatesDAO.instance.add(new CommentRate(0, l, new Date(), userName, commentID));
				} else {
					boolean l = like.equals("true" ) ? true : false;
					cr.setLike(l);
					CommentRatesDAO.instance.update(cr, cr.getId());
				}
			}
		}
		
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
