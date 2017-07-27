package ru.parsentev.servlets.user;

import ru.parsentev.store.UserCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: comment
 * @author parsentev
 * @since 17.04.2015
 */
public class UserDeleteServlet extends HttpServlet {

	private final UserCache USER_CACHE = UserCache.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.USER_CACHE.delete(Integer.valueOf(req.getParameter("id")));
		resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));
	}
}
