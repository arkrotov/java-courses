package ru.krotov.servlets.pet;

import ru.krotov.service.PetService;
import ru.krotov.service.PetServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PetViewServlet extends HttpServlet {

    private final PetService PET_CACHE = PetServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pets", this.PET_CACHE.values());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/pet/PetView.jsp");
        dispatcher.forward(req, resp);
    }
}
