package ru.parsentev.servlets.pet;

import ru.lessons.lesson_6.Animal;
import ru.parsentev.models.Dog;
import ru.parsentev.store.PetCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class PetCreateServlet extends HttpServlet{

    private final AtomicInteger ids = new AtomicInteger();
    private final PetCache PET_CACHE = PetCache.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.PET_CACHE.add(new Dog(new Animal(this.ids.incrementAndGet(), req.getParameter("name"), req.getParameter("owner"))));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/pet/view"));
    }

}
