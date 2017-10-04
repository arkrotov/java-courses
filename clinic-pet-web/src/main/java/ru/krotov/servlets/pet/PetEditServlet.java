package ru.krotov.servlets.pet;

import ru.krotov.service.PetService;
import ru.krotov.service.PetServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PetEditServlet extends HttpServlet {

    private final PetService PET_CACHE = PetServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pet", this.PET_CACHE.get(Integer.parseInt(req.getParameter("id"))));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/pet/EditPet.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         //this.PET_CACHE.edit(
                /*new Pet(
                        new Animal(
                                Integer.parseInt(req.getParameter("id")),
                                req.getParameter("name"),
                                req.getParameter("owner"),
                                Boolean.getBoolean(req.getParameter("sex")
                                )
                        )
                )
        );
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/pet/view"));*/
    }
}
