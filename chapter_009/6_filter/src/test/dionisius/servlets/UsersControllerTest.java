package dionisius.servlets;

import dionisius.control.DbManager;
import dionisius.models.User;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Created by Dionisius on 03.09.2017.
 * Class for testing UsersController.java servlet.
 */
public class UsersControllerTest {
    /**
     * Tests UsersController.java servlet.
     * @throws ServletException if ServletException occurs.
     * @throws IOException if IOException occurs.
     */
    @Test
    public void whenServletAddsUserThenItIsExpectedUser() throws ServletException, IOException {
        String expectedValue = "root";
        UsersController controller = new UsersController();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        DbManager.getInstance().deleteAll();

        when(request.getParameter("login")).thenReturn("root");
        when(request.getParameter("name")).thenReturn("root");
        controller.doPost(request, response);

        List <User> users = DbManager.getInstance().getAllUsers();
        assertThat(users.get(0).getLogin(), is("root"));
    }
}