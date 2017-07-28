package ru.parsentev.servlets.pet;

import ru.lessons.lesson_6.Animal;
import ru.parsentev.models.Dog;
import ru.parsentev.store.PetCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PetEditServlet extends HttpServlet {

    private final PetCache PET_CACHE = PetCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pet", this.PET_CACHE.get(Integer.parseInt(req.getParameter("id"))));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/pet/EditPet.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.PET_CACHE.edit(new Dog(new Animal(Integer.parseInt(req.getParameter("id")), req.getParameter("name"), req.getParameter("owner"))));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/pet/view"));
    }
}
