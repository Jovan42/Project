package project.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.dao.UsersDAO;
import project.dao.VideosDAO;
import project.model.User;
import project.model.Video;

/**
 * Servlet implementation class ServletDeleteVideo
 */
public class ServletDeleteVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeleteVideo() {
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
		Video video = (Video)VideosDAO.instance.get(videoId);
		if(loggedInUser == null || (!loggedInUser.isAdmin() && !loggedInUser.getUserName().equals(video.getOwnersUserName())) ) {
			response.sendRedirect("./main.html");
			return;
		}
		
		video.setDeleted(true);
		VideosDAO.instance.update(video, videoId);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
