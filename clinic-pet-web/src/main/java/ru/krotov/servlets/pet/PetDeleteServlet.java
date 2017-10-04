package ru.krotov.servlets.pet;

import ru.krotov.service.PetService;
import ru.krotov.service.PetServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PetDeleteServlet extends HttpServlet {

    private final PetService PET_CACHE = PetServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.PET_CACHE.delete(Integer.valueOf(req.getParameter("id")));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/pet/view"));
    }
}
