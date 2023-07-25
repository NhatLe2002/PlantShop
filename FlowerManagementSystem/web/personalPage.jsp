<%-- 
    Document   : personalPage
    Created on : Feb 16, 2023, 1:37:32 PM
    Author     : HNC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.Date"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="sample.dao.AccountDAO"%>
<%@page import="sample.dto.Account"%>
<%@page import="sample.dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.dao.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>

    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");

            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie cookie : c) {
                    if (cookie.getName().equals("selector")) {
                        token = cookie.getValue();
                        Account acc = AccountDAO.getAccount(token);
                        if (acc != null) {
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }
                    }
                }
            } else {
                login = true;
            }

            if (login) {
        %>

        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name%> come back </h3>
            <h3> <%= (request.getAttribute("update")) == null? "" : request.getAttribute("update")%> </h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
        </section>
        <section id="tableOfOder"> <!-- load all order of the user at here-->
            
            <%
                ArrayList<Order> list = OrderDAO.getOrders(email);
                String[] status = {"", "processing", "completed", "canceled"};
                String from = (request.getParameter("from")==""||request.getParameter("from")==null) ?"2021-01-01":request.getParameter("from");
                String to = (request.getParameter("to")==""||request.getParameter("to")==null)?"2030-01-01":request.getParameter("to");
                if (list != null && !list.isEmpty()) {
                    LocalDate dateFrom = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate dateTo = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate datecheck = null;
                    for (Order ord : list) {
                        if (dateFrom != null && dateTo != null) {
                            datecheck = LocalDate.parse(ord.getOrderDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            if (datecheck.isAfter(dateFrom) && datecheck.isBefore(dateTo)) {
            %>
            <table class="order" style="">
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>
                <tr><td><%= ord.getOrderID()%></td>
                    <td><%= ord.getOrderDate()%></td>
                    <td><%= ord.getShipDate()%></td>
                    <td><%= status[ord.getStatus()]%>
                        <br/><% if (ord.getStatus() == 1)%><a href="#">cancel order</a>
                    </td>
                    <td><a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>">detail</a></td>
                </tr>
            </table>        
                            <%
                                                    } 
                            } else {
                            %>
            <table class="order" style="">
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>
                <tr><td><%= ord.getOrderID()%></td>
                    <td><%= ord.getOrderDate()%></td>
                    <td><%= ord.getShipDate()%></td>
                    <td><%= status[ord.getStatus()]%>
                        <br/><% if (ord.getStatus() == 1)%><a href="#">cancel order</a>
                    </td>
                    <td><a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>">detail</a></td>
                </tr>
            </table>
            <%}

                            }
            
                    }else{ %>
            <p>You don't have any order</p>
            <%}%>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%}else{
                %>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <P><font color="red" >you must login to view personal page</font></P>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%
            }%>
    </body>
</html>
