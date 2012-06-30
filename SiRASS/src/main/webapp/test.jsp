<%-- 
    Document   : error
    Created on : 27-jun-2012, 18:32:45
    Author     : gomezhyuuga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="WEB-INF/jspf/header.jsp">
            <jsp:param name="title" value="My Title" />
            <jsp:param name="style" value="one" />
            <jsp:param name="style" value="two" />
        </jsp:include>
    </head>
    <body>
        <h1>Hello World error dasdas!</h1>
        <p><%= request.getRequestURI() %></p>
        <p><%= application.getContextPath() %></p>
        <p>sadas</p>
    </body>
</html>
