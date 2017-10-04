package ru.krotov.servlets.pet;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PetSearchServletTest extends Mockito {

    @Test
    public void searchPet() throws ServletException, IOException {

     //   final PetServiceImpl cache = PetServiceImpl.getInstance();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("owner")).thenReturn("test");
       // assertTrue(cache.getOwner() == null);

        new PetSearchServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("owner");

    //    assertFalse(cache.getOwner() == null);

    //    cache.setOwner(null);

    //    assertTrue(cache.getOwner() == null);

    }


}