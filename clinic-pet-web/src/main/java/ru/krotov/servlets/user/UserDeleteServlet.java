package ru.krotov.servlets.user;

import ru.krotov.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: comment
 * @author krotov
 * @since 17.04.2015
 */
public class UserDeleteServlet extends HttpServlet {

	private final UserServiceImpl USER_CACHE = UserServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.USER_CACHE.delete(Integer.valueOf(req.getParameter("id")));
		resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
	}
}
