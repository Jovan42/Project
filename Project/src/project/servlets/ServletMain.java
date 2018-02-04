package project.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.dao.VideosDAO;
import project.model.User;
import project.model.Video;

/**
 * Servlet implementation class ServletMain
 */
public class ServletMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser"); 
		ArrayList<Video> videos = VideosDAO.instance.getFor(loggedInUser);
		Map<String, Object> data = new HashMap<>();
		String sort = "name";
		if (request.getParameter("sort") != null) sort = request.getParameter("sort").toLowerCase();
		String sortType  = "asc";
		if( request.getParameter("sortType") != null) sortType = request.getParameter("sortType").toLowerCase();
	
		String sName = request.getParameter("sName");
		String sOwner = request.getParameter("sOwner");
		String sViews = request.getParameter("sViews");
		String sDate = request.getParameter("sDate");
		System.out.println("name: " + sDate);
		
		if(sName != null && !sName.trim().equals("")) videos = searchName(videos, sName.toLowerCase());
		if(sOwner != null && !sOwner.trim().equals("")) videos = searchOwner(videos, sOwner.toLowerCase());
		if(sViews != null && !sViews.trim().equals("")) videos = searchViews(videos, Integer.parseInt(sViews));
		if(sDate != null && !sDate.trim().equals("")) videos = searchDate(videos, sDate);
		
		if(sort == null) sort = "";
		if(sortType == null) sortType = "asc";
		
		switch (sort) {
		case "name":
			if(sortType.equals("desc")) Collections.sort(videos, Video.nameComparatorDesc);
			else Collections.sort(videos, Video.nameComparatorAsc);
			break;
		case "owner":
			if(sortType.equals("desc")) Collections.sort(videos, Video.ownerComparatorDesc);
			else Collections.sort(videos, Video.ownerComparatorAsc);
			break;
		case "views":
			if(sortType.equals("desc")) Collections.sort(videos, Video.viewsComparatorDesc);
			else Collections.sort(videos, Video.viewsComparatorAsc);
			break;
		case "date":
			if(sortType.equals("desc")) Collections.sort(videos, Video.dateComparatorDesc);
			else Collections.sort(videos, Video.dateComparatorAsc);
			break;
		default:
			break;
		}

		
		data.put("user", loggedInUser);
		data.put("videos", videos);
		
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
	
	private ArrayList<Video> searchName(ArrayList<Video> videos, String name) {
		ArrayList<Video> tmp = new ArrayList<>();
		for(Video v : videos) {
			if(v.getName().contains(name)) tmp.add(v);
		}
		return tmp;
	}
	
	private ArrayList<Video> searchOwner(ArrayList<Video> videos, String owner) {
		ArrayList<Video> tmp = new ArrayList<>();
		for(Video v : videos) {
			if(v.getOwnersUserName().contains(owner)) tmp.add(v);
		}
		return tmp;
	}
	
	private ArrayList<Video> searchViews(ArrayList<Video> videos, int views) {
		ArrayList<Video> tmp = new ArrayList<>();
		for(Video v : videos) {
			if(v.getViews() == views) tmp.add(v);
		}
		return tmp;
	}
	
	private ArrayList<Video> searchDate(ArrayList<Video> videos, String date) {
		ArrayList<Video> tmp = new ArrayList<>();
		for(Video v : videos) {
			if(v.getCreated().toString().equals(date)) tmp.add(v);
		}
		return tmp;
	}

}
