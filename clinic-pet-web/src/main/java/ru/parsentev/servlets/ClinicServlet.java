package ru.parsentev.servlets;


import ru.lessons.lesson_6.Animal;
import ru.lessons.lesson_6.Pet;
import ru.parsentev.models.Dog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 16.04.2015
 */
public class ClinicServlet extends HttpServlet {

    private final List<Pet> pets = new CopyOnWriteArrayList<Pet>();
    private String searchName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "     <title>Clinic Pets</title>" +
                        "</head>" +
                        "<body>" +
                        "     <form action='" + req.getContextPath() + "/clinic' method='post'>" +
                        "         Name : <input type='text' name='name'>" +
                        "         Owner : <input type='text' name='owner'>" +
                        "         <input type='submit' name='add' value='Submit'>" +
                        "     <form>" +
                        this.viewPets() +
                        "<br>" +
                        "<br>" +
                        "<br>" +
                        "     <form action='" + req.getContextPath() + "/clinic' method='post'>" +
                        "         Owner : <input type='text' name='searchOwner'>" +
                        "         <input type='submit' name='search' value='Submit'>" +
                        "     <form>" +
                        this.searchPet(searchName) +
                        "</body>" +
                        "</html>"
        );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int maxId = 0;
        for (Pet pet : this.pets) {
            if (maxId < pet.getId()) {
                maxId = pet.getId();
            }
        }

        if (req.getParameter("add") != null && req.getParameter("add").equals("Submit")) {
            this.pets.add(new Dog(new Animal(++maxId, req.getParameter("name"), req.getParameter("owner"))));
        } else {
            this.searchName = req.getParameter("searchOwner");
        }
        doGet(req, resp);
    }

    private String viewPets() {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Pets</p>");
        sb.append("<table style='border : 1px solid black'>");
        sb.append("<tr><td style='border : 1px solid black'>ID</td><td style='border : 1px solid black'>Name</td><td style='border : 1px solid black'>Owner</td></tr>");
        for (Pet pet : this.pets) {
            sb.append("<tr>")
                    .append("<td style='border : 1px solid black'>").append(pet.getId()).append("</td>")
                    .append("<td style='border : 1px solid black'>").append(pet.getName()).append("</td>")
                    .append("<td style='border : 1px solid black'>").append(pet.getOwner()).append("</td>")
                    .append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    private String searchPet(String owner) {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Search results</p>");
        sb.append("<table style='border : 1px solid black'>");
        sb.append("<tr><td style='border : 1px solid black'>ID</td><td style='border : 1px solid black'>Name</td><td style='border : 1px solid black'>Owner</td></tr>");
        for (Pet pet : pets) {
            if (pet.getOwner().equals(owner)) {
                sb.append("<tr>")
                        .append("<td style='border : 1px solid black'>").append(pet.getId()).append("</td>")
                        .append("<td style='border : 1px solid black'>").append(pet.getName()).append("</td>")
                        .append("<td style='border : 1px solid black'>").append(pet.getOwner()).append("</td>")
                        .append("</tr>");
            }
        }
        sb.append("</table>");
        return sb.toString();
    }
}
