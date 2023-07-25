<%-- 
    Document   : header_loginedAdmin
    Created on : Feb 26, 2023, 5:57:39 PM
    Author     : HNC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <%String name = (String) session.getAttribute("name");%>
            <ul>
                <li><a href="mainController?action=manageAccounts">Manage Accounts</a></li>
                <li><a href="mainController?action=manageOrder">Manage Order</a></li>
                <li><a href="mainController?action=managePlant">Manage Plants</a></li>
                <li><a href="mainController?action=manageCategories">Manage categories</a></li>
                <li>Welcome ${sessionScope.name}<a href="mainController?action=logout">Logout</a></li>

            </ul>
        </header>
    </body>
</html>
