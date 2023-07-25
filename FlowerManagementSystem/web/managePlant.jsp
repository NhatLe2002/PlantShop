<%-- 
    Document   : managePlant
    Created on : Mar 21, 2023, 12:43:34 PM
    Author     : HNC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <c:import url="header_managePlant.jsp"></c:import>
            </header>
            <section>
                <p>${requestScope.message}</p>
            <c:choose>
                <c:when test="${sessionScope.txtname!=null&& sessionScope.acc.getRole()==1}">
                    <c:if test="${requestScope.list!=null}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <td>PID</th>
                                    <th>PName</th>
                                    <th>price</th>
                                    <th>image</th>
                                    <th>description</th>
                                    <th>status</th>
                                    <th>CateID</th>
                                    <th>CateName</th>
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <c:forEach var="p" items="${requestScope.list}">

                                <tbody>

                                    <tr>
                                        <td>${p.getId()}</td>
                                        <td>${p.getName()}</td>
                                        <td>${p.getPrice()}</td>
                                        <td><img src="${p.getImgpath()}" class="planting" /></td>
                                        <td>${p.getDescription()}</td>
                                        <td>${p.getStatus()}</td>
                                        <td>${p.getCateid()}</td>
                                        <td>${p.getCatename()}</td>
                                        <td><form action="mainController" method="post">
                                                <details>
                                                    <summary>Click to input update</summary>
                                                    <ul>
                                                        <li><input type="hidden" name="PID" value="${p.getId()}"></li>
                                                        
                                                        <li>New name: <input type="text" name="newName" required=""></li>
                                                        <li>New price: <input type="number" name="newPrice" required=""></li>
                                                        <li><input type="hidden" name="imgpath" value="${p.getImgpath()}"></li>
                                                        
                                                        <li>New description: <input type="text" name="newDescription:" required=""></li>
                                                        <li>New status: <select name="newStatus">
                                                                <option value="1">enable</option>
                                                                <option value="0">disable</option>
                                                            </select></li>
                                                            
                                                        <li>New category: <select name="newategory">
                                                                <c:forEach items="${requestScope.listCate}" var="c">
                                                                    <option value="${c.getCateName()}">${c.getCateName()}</option>
                                                                </c:forEach>
                                                                
                                                                
                                                            </select></li>
                                                    </ul></details>
                                                <input type="submit" value="updatePlant" name="action"></td>
                                            </form>
                                    </tr>
                                </tbody>
                            </c:forEach>
                        </table>


                    </c:if>
                    <footer>
                        <c:import url="footer.jsp"></c:import>
                        </footer>
                </c:when>
                <c:otherwise >
                    <p>You must login admin page to do....</p>
                </c:otherwise>
            </c:choose>
        </section>
            <footer>
                        <c:import url="footer.jsp"></c:import>
                        </footer>
    </body>
</html>
