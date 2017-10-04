package ru.krotov.servlets.user;

import ru.krotov.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO: comment
 * @author krotov
 * @since 17.04.2015
 */
public class UserEditServlet extends HttpServlet {

	final AtomicInteger ids = new AtomicInteger();

	private final UserServiceImpl USER_CACHE = UserServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("user", this.USER_CACHE.get(Integer.valueOf(req.getParameter("id"))));
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/EditUser.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*this.USER_CACHE.edit(
				new User(
						this.ids.incrementAndGet(),
						req.getParameter("login"),
						req.getParameter("firstName"),
						req.getParameter("lastName"),
						req.getParameter("sex"),
						req.getParameter("phone"),
						req.getParameter("email")
				)
		);
		resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
	*/
	}
}
