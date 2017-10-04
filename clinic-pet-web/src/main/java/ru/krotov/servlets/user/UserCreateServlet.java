package ru.krotov.servlets.user;

import ru.krotov.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO: comment
 *
 * @author krotov
 * @since 17.04.2015
 */
public class UserCreateServlet extends HttpServlet {

    private final AtomicInteger ids = new AtomicInteger();
    private final UserServiceImpl USER_CACHE = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*  this.USER_CACHE.add(
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
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/user/view"));*/
    }
}
