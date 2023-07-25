<%-- 
    Document   : manageCategories
    Created on : Mar 22, 2023, 8:58:44 AM
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
        <c:choose >
            <c:when test="${sessionScope.acc.getRole()==1}">
                <header>
                    <c:import url="header_loginedAdmin.jsp"></c:import>
                    </header>


                    <section>
                        <form action="mainController" method="post">
                            <details>
                                <summary>Create new categories</summary>
                                <div>New Cate Name: <input type="txt" name="newCateName" required=""></div>
                                <input type="submit" value="createCate" name="action">  
                            </details>

                        </form>

                        <table border="1">
                            <thead>
                                <tr>
                                    <th>CateID</th>
                                    <th>CateName</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                        <c:forEach items="${requestScope.listCate}" var="c">
                            <tbody>
                                <tr>
                                    <td>${c.getCateID()}</td>
                                    <td>${c.getCateName()}</td>
                                    <td><form action="mainController" method="post">
                                            <details>

                                                <summary>Click to input update categories</summary>
                                                <ul>

                                                    <li>New Cate Name: <input type="txt" name="newCateNameUpdate" required=""></li>
                                                    <div><input type="hidden" name="CateID" value="${c.getCateID()}">
                                                    </div>
                                                </ul>
                                                <input type="submit" value="Update" name="action">  

                                            </details>
                                        </form>
                                    </td>
                                </tr>
                            </tbody>
                        </c:forEach>

                    </table>
                    <p>${requestScope.message}</p>
                </section>
                <footer>
                    <c:import url="footer.jsp"></c:import>
                    </footer>
            </c:when>
            <c:otherwise>
                <header>
                    <c:import url="header_loginedAdmin.jsp"></c:import>
                    </header>
                    <section>
                    ${requestScope.message}
                </section>
                <footer>
                    <c:import url="footer.jsp"></c:import>
                    </footer>
            </c:otherwise>
        </c:choose>

    </body>
</html>
