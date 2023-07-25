<%-- 
    Document   : AdminIndex
    Created on : Feb 26, 2023, 5:52:51 PM
    Author     : HNC
--%>

<%@page import="sample.dto.Account"%>
<%@page import="sample.dao.AccountDAO"%>
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
        <%
            String name = (String) session.getAttribute("txtname");
            String email = (String) session.getAttribute("txtemail");

            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                if (c!=null) {
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
                }

            } else {
                login = true;
            }

            if (login) {
        %>
        <header>
            <c:import url="header_loginedAdmin.jsp"></c:import>
            </header>
            <section class="adminSection">
                <img src="images/background.jpg" width="100%" height="100%"/>
            </section>
            <footer>
            <c:import url="footer.jsp"></c:import>
            </footer>
        <%} else {
        %>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <P><font color="red" >you must login to view admin page</font></P>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%
            }%>
    </body>
</html>
