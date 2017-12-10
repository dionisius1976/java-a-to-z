<%@ page import="ru.dionisius.ru.dionisius.ru.dionisius.data.pojo.User" %>
<%@ page import="ru.dionisius.control.DbManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset=\"UTF-8\">
            <title>JSP</title>
        </head>
        <body>
            <table border="1">
                        <td>Name</td><td>Login</td><td>Email</td><td>Creation_Date</td>
                    <%for (User user : DbManager.getInstance().getAllUsers()) {%>
                <tr>
                    <td><%=user.getName()%></td>
                    <td><%=user.getLogin()%></td>
                    <td><%=user.getEmail()%></td>
                    <td><%=user.getCreateDate()%></td>
                </tr>
                <% } %>
            </table>
                <p>Create new user:</p>
                <form action='<%=request.getContextPath()%>/create' method='post'>
                User name : <input type='text' name='name'>
                    User login : <input type='text' name='login'>
                    User email : <input type='text' name='email'>
                    <input type='submit'>
                </form>
                <br/>
                <p>Edit user:</p>
                <form action='<%=request.getContextPath()%>/edit' method='post'>
                    User name : <input type='text' name='name'>
                    User login : <input type='text' name='login'>
                    User new user login : <input type='text' name='newName'>
                    User new user email: <input type='text' name='newLogin'>
                    <input type='submit'>
                    </form>
                    <br/>
                <p>Delete user:</p>
                <form action='<%=request.getContextPath()%>/delete' method='post'>
                    User name : <input type='text' name='name'>
                    User login : <input type='text' name='login'>
                    <input type='submit'>
                </form>
        </body>
    </html>
