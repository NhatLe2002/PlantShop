<%-- 
    Document   : changeProfile
    Created on : Mar 16, 2023, 10:43:25 AM
    Author     : HNC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update profile</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>

    </head>
    <body>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <p> Wellcome ${sessionScope.name}</p>
                
            
            <form action="mainController" method="post" class="formUpdateUser">
                <h1>Update Profile</h1>
                <p> ${requestScope.update} <p>
                <table>
                    
                    
                    <tr><td>new full name</td><td><input type="text" name="txtnewfullname" required="" value="   <%= (request.getAttribute("name") == null) ? "" : request.getAttribute("name")%>"></td></tr>

                    <tr><td>new phone</td><td><input type="text" name="txtnewphone" required="" value="<%= (request.getAttribute("newphone") == null) ? "" : request.getAttribute("newphone")%>">
                        <td><%= (request.getAttribute("update") == null) ? "" : request.getAttribute("update")%></td></td></tr>

                    <tr><td colspan="2"><input type="submit" value="updateProfileUser" name="action"></td></tr>
                </table>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
