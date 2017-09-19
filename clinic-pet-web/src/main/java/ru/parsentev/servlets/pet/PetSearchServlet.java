package ru.parsentev.servlets.pet;

import ru.parsentev.service.PetService;
import ru.parsentev.service.PetServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PetSearchServlet extends HttpServlet {

    private final PetService PET_CACHE = PetServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // this.PET_CACHE.setOwner(req.getParameter("owner"));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/pet/view"));
    }
}
