<%-- 
    Document   : login
    Created on : Feb 16, 2023, 12:58:06 PM
    Author     : HNC
--%>

<%@page import="sample.dto.Account"%>
<%@page import="sample.dto.Account"%>
<%@page import="sample.dao.AccountDAO"%>
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
            <%@include file="header.jsp" %>
        </header>
        <section>
            
            <form action="mainController" method="post" class="formlogin">
                <font style="color: red"> <%= (request.getAttribute("WARNING")==null)?"":(String)request.getAttribute("WARNING") %> </font>
                <table>
                    <tr><td>email</td><td><input type="text"  name="txtemail" value=""></td></tr>
                    <tr><td>password</td><td><input type="password" name="txtpassword"></td></tr>
                    <tr><td colspan="2"><input type="submit" value="login" name="action"></td></tr>
                    <tr><td colspan="2"><input type="checkbox" value="savelogin" name="savelogin">Stayed singed in</td></tr>
                </table>

            </form>
        </section>
                <p><font color="red" >${requestScope.message}</p>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
