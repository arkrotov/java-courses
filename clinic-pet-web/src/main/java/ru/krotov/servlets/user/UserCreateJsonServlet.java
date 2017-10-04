package ru.krotov.servlets.user;

import org.codehaus.jackson.map.ObjectMapper;
import ru.krotov.models.User;
import ru.krotov.service.UserService;
import ru.krotov.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCreateJsonServlet extends HttpServlet{

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        final ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.print(new ObjectMapper().writeValueAsString(userService.values()));
        outputStream.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
        userService.add(user);
        resp.getOutputStream().write("{'result' : 'true'}".getBytes());
    }

}
