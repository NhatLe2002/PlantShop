<%-- 
    Document   : header_loginedUser
    Created on : Feb 16, 2023, 1:30:31 PM
    Author     : HNC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="mycss.css" type="text/css"/>

    </head>
    <body>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="mainController?action=changeProfile">Change profile</a></li>
                <li><a href="mainController?action=completedOrders">Completed orders</a></li>
                <li><a href="mainController?action=canceledOrder">Canceled order</a></li>
                <li><a href="mainController?action=processingOrder">Processing orders</a></li>
                <li><a href="mainController?action=viewcart">Your Cart</a></li>
                <form action="mainController" method="post">
                    <li>from <input type="date" name="from"> to <input type="date" name="to">
                        <input type="submit" value="searchOrderByDay" name="action" >
                    </li>
                </form>
            </ul>
        </nav>
    </body>
</html>
