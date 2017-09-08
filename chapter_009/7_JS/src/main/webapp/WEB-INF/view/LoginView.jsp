<%--
  Created by IntelliJ IDEA.
  User: Suvorov
  Date: 02.09.2017
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Login page</title>
    </head>
    <body>
        <c:if test="${error != ''}">
            <div style="background-color: red">
                <c:out value="${error}"/>
            </div>
        </c:if>
        <form action='${pageContext.servletContext.contextPath}/sighin' method='post'>
            Login : <input type='text' name='login'>
            Password : <input type='password' name='password'>
            <input type='submit'>
        </form>
    </body>
</html>
