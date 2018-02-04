package project.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.dao.CommentsDAO;
import project.dao.VideosDAO;
import project.enums.Visibility;
import project.model.Comment;
import project.model.User;
import project.model.Video;

/**
 * Servlet implementation class ServletAddVideo
 */
public class ServletAddVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddVideo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url").trim();
		String thumbnailUrl = request.getParameter("thumbnailUrl").trim();
		String description = "";
		if(request.getParameter("description") == null ) description = request.getParameter("description");
		boolean rate = true;
		if(request.getParameter("rate") == null ) rate = false;
		boolean comment = true;
		if(request.getParameter("comment") == null ) comment = false;
		Visibility visible = Visibility.UNLISTED;
		if(request.getParameter("visible") == null ) visible = Visibility.PRIVATE;
		
		
		
		
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser"); 
		Video video = new Video(0, url, url, thumbnailUrl, description, visible, comment, rate, false, 0, new Date(), loggedInUser.getUserName(), false);
		
		VideosDAO.instance.add(video);
		
		response.sendRedirect("./main.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
