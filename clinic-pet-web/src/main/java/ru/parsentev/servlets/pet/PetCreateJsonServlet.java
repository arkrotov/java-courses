package ru.parsentev.servlets.pet;

import org.codehaus.jackson.map.ObjectMapper;
import ru.lessons.lesson_6.Animal;
import ru.parsentev.models.Dog;
import ru.parsentev.models.PetForm;
import ru.parsentev.store.PetCache;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PetCreateJsonServlet extends HttpServlet {

    private final PetCache PET_CACHE = PetCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        final ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.print(new ObjectMapper().writeValueAsString(PET_CACHE.values()));
        outputStream.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        final PetForm petForm = new ObjectMapper().readValue(req.getInputStream(), PetForm.class);
        PET_CACHE.add(new Dog(new Animal(PET_CACHE.generateId(), petForm.getName(), petForm.getOwner(), petForm.isSex())));
        resp.getOutputStream().write("{'result' : 'true'}".getBytes());
    }
}
