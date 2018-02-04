package project.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.dao.UsersDAO;
import project.model.User;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./main.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password1").trim();
		
		if(!UsersDAO.instance.checkOnLogIn(userName, password)) {
			response.sendRedirect("./home.html?msg=wrongLogin");
		} else {
			User user = (User)UsersDAO.instance.get(userName);
			System.out.println(user.getUserName() + user.getPassword());
			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", user);
			response.sendRedirect("./main.html");
		}
	}

}
