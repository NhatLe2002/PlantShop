<%-- 
    Document   : index
    Created on : Feb 16, 2023, 12:53:22 PM
    Author     : HNC
--%>

<%@page import="sample.dao.PlantDAO"%>
<%@page import="sample.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <% String keyword = request.getParameter("txtsearch");
                String searchby = request.getParameter("searchby");
                ArrayList<Plant> list;
                String[] tmp = {"out of stock", "avaible"};
                if (keyword == null && searchby == null) {
                    list = PlantDAO.getPlants("", "");
                } else {
                    list = PlantDAO.getPlants(keyword, searchby);
                }
                if (list != null && !list.isEmpty()) {
                    for (Plant p : list) {%>

            <table>
                <tr>
                    <td><img src="<%= p.getImgpath()%>" class="planting" /></td>
                    <td>Product ID:<%= p.getId()%> </td>
                    <td>Product name: <%= p.getName()%></td>
                    <td>Price: <%= p.getPrice()%> </td>
                    <td>Status: <%= tmp[p.getStatus()]%> </td>
                    <td>Category: <%= p.getCatename()%> </td>
                    <td><a href="mainController?action=addtocart&pid=<%=p.getId() %>">add to cart</a> </td>
                </tr>
            </table>
            <%        }
                }%>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
