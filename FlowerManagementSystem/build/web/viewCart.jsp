<%-- 
    Document   : viewCart
    Created on : Feb 24, 2023, 11:54:57 PM
    Author     : HNC
--%>

<%@page import="sample.dao.PlantDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%
                int money = 0;
                String name = (String) session.getAttribute("name");
                if (name != null) {


            %>
            <h3>Welcome <%= session.getAttribute("name")%> come back</h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
            <h3><a href="personalPage.jsp">Personal Page</a></h3>
            
            <%
                }
            %>

            <font style="color: red;"><%= (request.getAttribute("UploadOke") == null) ? "" : (String) request.getAttribute("UploadOke")%></font>

            <font style="color: red;"><%= (request.getAttribute("WARNING") == null) ? "" : (String) request.getAttribute("WARNING")%></font>

            <table with="100%" class="tableOfCart">
                <tr><td>Product id</td><td>Image</td><td>Price</td> <td>Quantity</td><td>action</td></tr>
                <%
                    HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
                    if (cart != null) {
                        Set<String> pids = cart.keySet();
                        for (String pid : pids) {
                            int quantity = cart.get(pid);

                %>
                <form action="mainController" method="post">
                    <tr><td> <input type="hidden" value="<%= pid%>" name="pid"><a href="getPlantServlet?pid=<%= pid%>">View detail</a></td>
                        <td><img src="<%= PlantDAO.getPlants(Integer.parseInt(pid)).getImgpath()%>" class="planting" /></td>
                        <td><%= PlantDAO.getPlants(Integer.parseInt(pid)).getPrice() %></td>
                        <td><input type="number" value="<%= quantity%>" min="1" max="100" name="quantity" required=""></td>
                        <td><input type="submit" value="update" name="action">
                            <input type="submit" value="delete" name="action"></td>
                    </tr>
                </form>
                <%
                      money = money +  PlantDAO.getPlants(Integer.parseInt(pid)).getPrice() * quantity;
                    }
                } else {
                %>    
                <tr><td>Your cart is empty</td></tr>
                <%
                    }
                %>
                <tr><td>Total money: <%= money %></td></tr>
                <tr><td>Order Date </td></tr>
                <tr><td>Ship Date: N/A </td></tr>
            </table>
        </section>
        <section>
            <form action="mainController" method="post">
                <input type="submit" value="saveOrder" name="action" class="submitorder">
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
