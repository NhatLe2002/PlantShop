<%-- 
    Document   : viewPlant
    Created on : Feb 26, 2023, 5:23:29 PM
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
        <jsp:useBean id="plantObj" class="sample.dto.Plant" scope="request"/>
        <table>
            <tr><td rowspan="8"> <img src="<jsp:getProperty name="plantObj" property="imgpath"></jsp:getProperty>"> </td></tr>
            <tr><td>id: <jsp:getProperty name="plantObj" property="id"></jsp:getProperty></td></tr>
            <tr><td>product name: <jsp:getProperty name="plantObj" property="name"></jsp:getProperty></td></tr>
            <tr><td>price: <jsp:getProperty name="plantObj" property="price"></jsp:getProperty></td></tr>
            <tr><td>description: <jsp:getProperty name="plantObj" property="description"></jsp:getProperty></td></tr>
            <tr><td>status: <jsp:getProperty name="plantObj" property="status"></jsp:getProperty></td></tr>
            <tr><td>cate id: <jsp:getProperty name="plantObj" property="cateid"></jsp:getProperty></td></tr>
            <tr><td>category: <jsp:getProperty name="plantObj" property="catename"></jsp:getProperty></td></tr>
            
        </table>
    </body>
</html>
