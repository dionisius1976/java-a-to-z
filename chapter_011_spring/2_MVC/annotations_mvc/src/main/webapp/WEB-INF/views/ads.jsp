<%--
  Created by IntelliJ IDEA.
  User: Dionisius
  Date: 03.11.2017
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
                    <th>Id</th>
                    <th>Description</th>
                    <th>Sold</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Transmission</th>
                    <th>Engine</th>
                    <th>Year</th>
                    <th>Price</th>
                    <th>Create_date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ads}" var="ad">
                    <tr>
                        <td><c:out value="${ad.id}"/></td>
                        <td><c:out value="${ad.desc}"/></td>
                        <td><c:out value="${ad.sold}"/></td>
                        <td><c:out value="${ad.car.brand}"/></td>
                        <td><c:out value="${ad.car.model}"/></td>
                        <td><c:out value="${ad.car.transmission}"/></td>
                        <td><c:out value="${ad.car.engineCapacity}"/></td>
                        <td><c:out value="${ad.car.year}"/></td>
                        <td><c:out value="${ad.price}"/></td>
                        <td><c:out value="${ad.createDate}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
