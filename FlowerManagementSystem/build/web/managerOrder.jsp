<%-- 
    Document   : managerOrder
    Created on : Mar 21, 2023, 8:24:00 AM
    Author     : HNC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>

    </head>
    <body>
        <header>
            <c:import url="header_loginedAdmin.jsp"></c:import>
            </header>
            <section>
                <form action="mainController" method="post">
                    <div>Enter name of order: <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch") == null) ? "" : request.getParameter("txtsearch")%>"></div>
                    <div>from <input type="date" name="from"> to <input type="date" name="to"></div>
                    <div><input type="submit" value="searchOrder" name="action"></div>
                </form>
            <c:if test="${requestScope.list!=null}">
                <c:forEach var="ord" items="${requestScope.list}">
                    <table class="order" style="">
                        <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>
                        <tr><td>${ord.getOrderID()}</td>
                            <td>${ord.getOrderDate()}</td>
                            <td>${ord.getShipDate()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${ord.getStatus() eq 1}">
                                        <p>${"Processing"}</p>
                                    </c:when>    
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${ord.getStatus() eq 3}">
                                                <p>${"Canceled"}</p>
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
            </c:if>
        </section>
    </body>
</html>
