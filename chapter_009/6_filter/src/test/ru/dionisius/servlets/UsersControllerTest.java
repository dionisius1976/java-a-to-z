package ru.dionisius.servlets;

import ru.dionisius.control.DbManager;
import ru.dionisius.models.User;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Created by Dionisius on 03.09.2017.
 * Class for testing UsersController.java servlet.
 */
public class UsersControllerTest {
    /**
     * When UsersController adds expected user then this user appears in cleaned before database.
     * @throws ServletException if ServletException occurs.
     * @throws IOException if IOException occurs.
     */
    @Test
    public void whenServletAddsExpectedUserThenThisUserAppearsInDatabase() throws ServletException, IOException {
        String expectedValue = "root";

        UsersController controller = new UsersController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        DbManager.getInstance().deleteAll();

        when(request.getParameter("login")).thenReturn(expectedValue);
        when(request.getParameter("name")).thenReturn(expectedValue);
        controller.doPost(request, response);

        List <User> users = DbManager.getInstance().getAllUsers();
        assertThat(users.get(0).getLogin(), is(expectedValue));
        assertThat(users.get(0).getName(), is(expectedValue));
    }
}