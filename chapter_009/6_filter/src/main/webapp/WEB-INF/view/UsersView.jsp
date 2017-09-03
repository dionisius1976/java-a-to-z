<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset=\"UTF-8\">
            <title>JSTL</title>
        </head>
        <body>

            <table border="1">
                <td>Name</td><td>Login</td><td>Email</td><td>Creation_Date</td>
                <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.createDate}"/></td>
                </tr>
                </c:forEach>
            </table>

            <p>Create a new user:</p>

            <form action='${pageContext.servletContext.contextPath}/' method='post'>
                User name : <input type='text' name='name'>
                User login : <input type='text' name='login'>
                User email : <input type='text' name='email'>
                <input type='submit'>
            </form>

        </body>
    </html>
