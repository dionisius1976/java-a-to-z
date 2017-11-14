<%--
  Created by IntelliJ IDEA.
  User: Dionisius
  Date: 03.11.2017
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <h3 style="text-align: center">List of orders</h3>
        <div class="col-md-12">
            <table class="table table-bordered">
                <thead style="background: blue">
                <tr style="color: white">
                    <th>Car brand</th>
                    <th>Car model</th>
                    <th>Color</th>
                    <th>Year</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Create date</th>
                    <th>User</th>
                    <th>Sold</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ads}" var="el">
                    <tr>
                        <td><c:out value="${el.car.model.brand.name}"/></td>
                        <td><c:out value="${el.car.model.name}"/></td>
                        <td><c:out value="${el.car.color}"/></td>
                        <td><c:out value="${el.car.year}"/></td>
                        <td><c:out value="${el.description}"/></td>
                        <td><c:out value="${el.price}"/></td>
                        <td><c:out value="${el.date}"/></td>
                        <td><c:out value="${el.user.login}"/></td>
                        <td><c:out value="${el.sold}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
