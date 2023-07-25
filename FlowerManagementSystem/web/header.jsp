<%-- 
    Document   : header
    Created on : Feb 16, 2023, 12:24:05 PM
    Author     : HNC
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="wihth=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <nav>
                <li><a href=""><img src="images/logo.jpg" id="logo"></a></li>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="registration.jsp">Register</a></li>
                    <c:if test="${sessionScope.name==null}">
                    <li><a href="login.jsp">Login</a></li>
                    </c:if>
                <li><a href="mainController?action=viewcart">View Cart</a></li>
                <li><a href="personalPage.jsp">Personal Page</a></li>

                <li>
                    <form action="mainController" method="post" class="formsearch">
                        <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch") == null) ? "" : request.getParameter("txtsearch")%>">
                        <select name="searchby">
                            <option value="byname">by name</option>
                            <option value="bycate">by category</option>
                        </select>
                        <input type="submit" value="search" name="action">
                    </form>
                </li>
            </nav>
        </header>
    </body>
</html>
