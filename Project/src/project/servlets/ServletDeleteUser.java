package project.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.dao.UsersDAO;
import project.model.User;

/**
 * Servlet implementation class ServletDeleteUser
 */
public class ServletDeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName").trim();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		System.out.println((loggedInUser == null || (!loggedInUser.isAdmin() && !loggedInUser.getUserName().equals(userName)) ));
		if(loggedInUser == null || (!loggedInUser.isAdmin() && !loggedInUser.getUserName().equals(userName)) ) {
			response.sendRedirect("./main.html");
			return;
		}
		
		User u = (User)UsersDAO.instance.get(userName);
		if(u.isDeleted())u.setDeleted(false);
		else u.setDeleted(true);
		UsersDAO.instance.update(u, userName);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./profile.html?userName=" + userName);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
