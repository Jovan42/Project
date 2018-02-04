package project.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.dao.CommentsDAO;
import project.model.Comment;
import project.model.User;

/**
 * Servlet implementation class ServletAddComment
 */
public class ServletAddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content").trim();
		int videoID = Integer.parseInt(request.getParameter("videoid").trim());
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser"); 
		Comment comment = new Comment(0, content, new Date(), loggedInUser.getUserName() , videoID, false, 0, 0);
		CommentsDAO.instance.add(comment);
		response.sendRedirect("watch.html?videoID=" + videoID);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
