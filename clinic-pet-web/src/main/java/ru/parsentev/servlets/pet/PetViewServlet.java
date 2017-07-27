package ru.parsentev.servlets.pet;

import ru.parsentev.store.PetCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PetViewServlet extends HttpServlet {

    private final PetCache PET_CACHE = PetCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pets", this.PET_CACHE.values());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/pet/PetView.jsp");
        dispatcher.forward(req, resp);
    }
}
