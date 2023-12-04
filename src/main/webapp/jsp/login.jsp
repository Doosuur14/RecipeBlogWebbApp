<%--
  Created by IntelliJ IDEA.
  User: doosuur
  Date: 30.11.2023
  Time: 9:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>loginpage</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Oswald', sans-serif;
            margin: 0;
            /*display: flex;*/
            /*justify-content: center;*/
            /*align-items: center;*/
            /*height: 100vh;*/
            /*background-color: #f4f4f4;*/
        }

        .background {
            background: url('/images/background4.jpeg') center/cover no-repeat;
            height: 60vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        label {
            font-weight: bold;
        }

        input {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            padding: 10px;
            background-color: rebeccapurple;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color:rebeccapurple;
        }

        p {
            margin-top: 15px;
            color: #666;
        }

        a {
            color: rebeccapurple;
            text-decoration: none;
        }

        button:hover {
            background-color: darkgreen;
        }


    </style>
</head>
<body>
<div class="background">
    <div class="login-container">
        <h2>Login</h2>
        <form action="/login" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Login</button>
        </form>
        <p>Don't have an account? <a href="register.jsp">Register here</a></p>
    </div>
</div>
</body>
</html>
