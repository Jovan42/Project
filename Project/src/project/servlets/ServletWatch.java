package project.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.dao.CommentsDAO;
import project.dao.VideosDAO;
import project.model.Comment;
import project.model.User;
import project.model.Video;

/**
 * Servlet implementation class ServletWatch
 */
public class ServletWatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletWatch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int videoID = Integer.parseInt(request.getParameter("videoID"));
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");		
		Map<String, Object> data = new HashMap<>();
		
		Video video =(Video) VideosDAO.instance.get(videoID);
		ArrayList<Comment> comments = CommentsDAO.instance.getFor(video.getId());
		video.setViews(video.getViews() + 1);
		VideosDAO.instance.update(video, video.getId());
		
		String sort = request.getParameter("sort");
		String sortType = request.getParameter("sortType");
		if(sort == null) sort = "";
		if(sortType == null) sortType = "asc";
		
		switch (sort) {
		case "rating":
			if(sortType.equals("desc")) Collections.sort(comments, Comment.ratingComparatorDesc);
			else Collections.sort(comments, Comment.ratingComparatorAsc);
			break;
		case "date":
			if(sortType.equals("desc")) Collections.sort(comments, Comment.dateComparatorDesc);
			else Collections.sort(comments, Comment.dateComparatorAsc);
			break;
		default:
			break;
		}
		
		String userName = loggedInUser == null ? "Guest" : loggedInUser.getUserName();
		data.put("user", userName);
		data.put("Video", video);
		data.put("Comments", comments);
		
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
