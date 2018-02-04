package project.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.dao.UsersDAO;
import project.model.User;

/**
 * Servlet implementation class ServletRegister
 */
public class ServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password1").trim();
		String password2 = request.getParameter("password2").trim();
		String firstName = "";
		if(request.getParameter("firstName") != null) firstName = request.getParameter("firstName").trim();
		String lastName = "";
		if(request.getParameter("lastName") != null) lastName = request.getParameter("lastName").trim();
		String eMail = request.getParameter("email").trim();
		String description = "";
		if( request.getParameter("description") != null) description =  request.getParameter("description").trim();
		if(userName == null || userName.equals("") || password == null || password.equals("") || !password2.equals(password) || eMail == null || eMail.equals("")) {
			response.sendRedirect("./home.html?msg=wrongRegister");
		}
		User u = new User(userName, password, firstName, lastName, eMail, description, new Date(), false, false, null, null, null, false);
		UsersDAO.instance.add(u);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("Login").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
