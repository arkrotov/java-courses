package ru.parsentev.servlets.pet;

import org.codehaus.jackson.map.ObjectMapper;
import ru.parsentev.models.Pet;
import ru.parsentev.service.PetService;
import ru.parsentev.service.PetServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PetCreateJsonServlet extends HttpServlet {

    private final PetService petService = PetServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        final ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.print(new ObjectMapper().writeValueAsString(petService.values()));
        outputStream.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        final Pet pet = new ObjectMapper().readValue(req.getInputStream(), Pet.class);
        petService.add(pet);
        resp.getOutputStream().write("{'result' : 'true'}".getBytes());
    }
}
