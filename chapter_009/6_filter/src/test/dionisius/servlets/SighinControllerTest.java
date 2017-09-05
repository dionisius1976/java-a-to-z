package dionisius.servlets;

import dionisius.control.DbManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Dionisius on 03.09.2017.
 */
public class SighinControllerTest {

    private static final String rightValue = "root";
    private static final String wrongValue = "robot";
    private static final String expectedAttribute = "error";
    private static final String expectedAttributeValue = "Invalid user!";
    private static final String expectedURI = "/WEB-INF/view/LoginView.jsp";
    private static final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    private static final HttpSession session = mock(HttpSession.class);
    private final SighinController sighinController = new SighinController();

    private static String resultAttribute = null;
    private static String resultAttributeValue = null;
    private static String resultURI = null;

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    @Before
    public void init() {
        DbManager.getInstance().deleteAll();
        DbManager.getInstance().createUser(rightValue, rightValue, rightValue);
    }

    @Test (expected = RuntimeException.class)
    public void whenRightLoginAndPasswordThenServletCallsRedirectMetod() throws ServletException, IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("login")).thenReturn(rightValue);
        when(request.getParameter("name")).thenReturn(rightValue);

        sighinController.doPost(this.request, this.response);
        doThrow(RuntimeException.class).when(response).sendRedirect(any(String.class));
    }

    @Test
    public void whenWrongLoginAndPasswordThenServletSetsExpectedAttributeToRequest() throws ServletException, IOException {

        when(request.getParameter("login")).thenReturn(wrongValue);
        when(request.getParameter("name")).thenReturn(wrongValue);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                resultAttribute = null;
                resultAttributeValue = null;
                if (arguments != null && arguments.length > 1 && arguments[0] != null && arguments[1] != null) {
                    resultAttribute = (String) arguments[0];
                    resultAttributeValue = (String) arguments[1];
                }
                return null;
            }
        }).when(request).setAttribute(SighinControllerTest.expectedAttribute,
                SighinControllerTest.expectedAttributeValue);

        when(request.getRequestDispatcher("/WEB-INF/view/LoginView.jsp")).thenReturn(dispatcher);
        doNothing().when(dispatcher).forward(request, response);

        sighinController.doPost(this.request, this.response);

        assertEquals(expectedAttribute, resultAttribute);
        assertEquals(expectedAttributeValue, resultAttributeValue);
    }

    @Test
    public void whenWrongLoginAndPasswordThenServletCallsDispatcherWithExpectedURI() throws ServletException, IOException {

        when(request.getParameter("login")).thenReturn(wrongValue);
        when(request.getParameter("name")).thenReturn(wrongValue);

        doAnswer(new Answer<RequestDispatcher>() {
            @Override
            public RequestDispatcher answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                resultURI = null;
                if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                    resultURI = (String) arguments[0];
                }
                return dispatcher;
            }
        }).when(request).getRequestDispatcher(expectedURI);

        doNothing().when(dispatcher).forward(request, response);

        sighinController.doPost(this.request, this.response);

        assertEquals(expectedURI, resultURI);
    }

    @Test (expected = RuntimeException.class)
    public void whenWrongLoginAndPasswordThenServletForwardsRequest() throws ServletException, IOException {
        when(request.getParameter("login")).thenReturn(wrongValue);
        when(request.getParameter("name")).thenReturn(wrongValue);

        doThrow(RuntimeException.class).when(dispatcher).forward(request, response);
    }
}