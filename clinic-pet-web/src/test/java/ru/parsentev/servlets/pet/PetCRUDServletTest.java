package ru.parsentev.servlets.pet;

import org.junit.Test;
import org.mockito.Mockito;
import ru.lessons.lesson_6.Animal;
import ru.parsentev.models.Pet;
import ru.parsentev.service.PetService;
import ru.parsentev.service.PetServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PetCRUDServletTest extends Mockito {

    @Test
    public void createPet() throws ServletException, IOException {

        final PetService cache = PetServiceImpl.getInstance();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("owner")).thenReturn("test");
        assertTrue(cache.values().isEmpty());

        new PetCreateServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("owner");
        assertFalse(cache.values().isEmpty());

        //cache.clear();

        assertTrue(cache.values().isEmpty());

    }

    @Test
    public void deletePet() throws ServletException, IOException {

        final PetService cache = PetServiceImpl.getInstance();
       // cache.add(new Pet(new Animal(1, "test", "test", true)));

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        assertFalse(cache.values().isEmpty());

        new PetDeleteServlet().doGet(request, response);
        verify(request, atLeast(1)).getParameter("id");
        assertTrue(cache.values().isEmpty());

     //   cache.clear();
    }

    @Test
    public void editPet() throws ServletException, IOException {

        final PetService cache = PetServiceImpl.getInstance();

        int id = 1;
       // Pet pet = new Pet(new Animal(id, "test", "test", true));
       // cache.add(pet);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("test2");
        when(request.getParameter("owner")).thenReturn("test2");

        PetEditServlet petEditServlet = new PetEditServlet();
        petEditServlet.doPost(request,response);
        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("owner");

      //  assertFalse(pet.equals(cache.get(id)));

    //    cache.clear();
        assertTrue(cache.values().isEmpty());

    }

    @Test
    public void viewPet ()  throws ServletException, IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher mock = mock(RequestDispatcher.class);

        when(request.getParameter("search")).thenReturn("");
    }

}