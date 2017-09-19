package ru.parsentev.servlets.pet;

import ru.lessons.lesson_6.Animal;
import ru.parsentev.models.Pet;
import ru.parsentev.service.PetService;
import ru.parsentev.service.PetServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class PetCreateServlet extends HttpServlet {

    private final AtomicInteger ids = new AtomicInteger();
    private final PetService PET_CACHE = PetServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /*  this.PET_CACHE.add(
                new Pet(
                        new Animal(
                                PET_CACHE.generateId(),
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
