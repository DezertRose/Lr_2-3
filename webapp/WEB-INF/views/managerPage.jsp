<%--
  Created by IntelliJ IDEA.
  User: Dasha
  Date: 2022-01-20
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manager data</title>
</head>
<body>
<h1>Modify data</h1>
<h2>Create new event:</h2>
<form action="${pageContext.request.contextPath}/manager" method="post">
    <label for="event">Event title: </label>
    <input type="text" name="event" id="event" value="${event}">
    <label for="data">Day of new event:</label>
    <input type="text" name="data" id="data" value="${data}">
    <input type="submit" name="signup" value="Add Event">
</form>

</body>
</html>
