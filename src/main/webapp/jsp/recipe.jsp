<%--
  Created by IntelliJ IDEA.
  User: doosuur
  Date: 04.12.2023
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Models.Recipe" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recipe Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: white;
            color: #333;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: rebeccapurple;
            color: white;
            text-align: center;
            padding: 1em;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: rebeccapurple;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<header>
    <h1>Recipes</h1>
</header>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Content</th>
        <th>Author</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="recipe" items="${recipes}">
        <tr>
            <td>${recipe.id}</td>
            <td>${recipe.title}</td>
            <td>${recipe.content}</td>
            <td>${recipe.author}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
