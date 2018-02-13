package project.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.dao.UsersDAO;
import project.dao.VideosDAO;
import project.enums.Visibility;
import project.model.User;
import project.model.Video;

/**
 * Servlet implementation class ServletVideoEdit
 */
public class ServletVideoEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVideoEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		Video video = (Video) VideosDAO.instance.get(videoId);
		
		if(loggedInUser == null || (!loggedInUser.isAdmin() && !loggedInUser.getUserName().equals(video.getOwnersUserName())) ) {
			response.sendRedirect("./main.html");
			return;
		}
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
		
		
		
		
		
		
		Video newVideo = new Video(videoId, url, url, thumbnailUrl, description, visible, comment, rate, video.isBlocked(), video.getViews(), video.getCreated(), video.getOwnersUserName(), video.isDeleted());
		
		VideosDAO.instance.update(newVideo, videoId);
		
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
