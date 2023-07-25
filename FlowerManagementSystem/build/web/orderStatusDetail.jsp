<%-- 
    Document   : statusOrder
    Created on : Mar 14, 2023, 7:37:09 AM
    Author     : HNC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>

    </head>
    <body>
        <header>
            <c:import url="header_loginedUser.jsp"></c:import>
        </header>

        <% String[] status = {"", "processing", "completed", "canceled"};%>
        <p> ${requestScope.succesfully}</p>
        <p> ${requestScope.WARNING}</p>
        <c:forEach var="ord" items="${requestScope.listCompleted}">
            <table class="order" style="">
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>
                <tr><td>${ord.getOrderID()}</td>
                    <td>${ord.getOrderDate()}</td>
                    <td>${ord.getShipDate()}</td>
                    <td>
                        <c:choose>
                            <c:when test="${ord.getStatus() eq 1}">
                                <p>${"Processing"}</p>
                                <br/> <a href="cancelOrderServlet?orderID=${ord.getOrderID()}">Cancel order</a>
                            </c:when>    
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${ord.getStatus() eq 3}">
                                        <p>${"Canceled"}</p>
                                        <br/> <a href="OrderAgainServlet?orderID=${ord.getOrderID()}">Order again</a>
                                    </c:when>    
                                    <c:otherwise>
                                        <p>${"Completed"}</p>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>


                    </td>
                    <td><a href="orderDetail.jsp?orderid=${ord.getOrderID()}">detail</a></td>
                </tr>
            </table>
        </c:forEach>

        <c:import url="footer.jsp"/>
    </body>
</html>
