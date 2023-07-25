<%-- 
    Document   : createPlant
    Created on : Mar 21, 2023, 2:10:53 PM
    Author     : HNC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <c:import url="header_managePlant.jsp"></c:import>
        </header>
        <h1>Create Plant</h1>
        <form action="mainController" method="post">
            <p>${requestScope.message}</p>
            <div>Name: <input type="text" name="name" required></div>
            <div>price: <input type="number" name="price" min="0" step="0.01" required></div>
            <div>description: <textarea name="description" rows="4" cols="50" required></textarea></div>
            <div>category: <select name="category">
                            <option value="orchid">orchid</option>
                            <option value="roses">roses</option>
                            <option value="others">others</option>
                        </select>
            </div>
            <div>image url:<input type="text" name="image" required></div>
            <div><input type="submit" value="createPlant" name="action"></div>
        </form>
            <footer>
                <c:import url="footer.jsp"></c:import>
            </footer>
    </body>
</html>
