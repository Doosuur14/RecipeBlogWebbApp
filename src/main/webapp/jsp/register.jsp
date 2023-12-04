<%--
  Created by IntelliJ IDEA.
  User: doosuur
  Date: 30.11.2023
  Time: 9:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Onome's Kitchen - Registration</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald&display=swap" rel="stylesheet">
    <style>
        body {
            text-align: center;
            font-family: 'Oswald', sans-serif;
            background-image: url("/images/background1.jpeg");
            background-size: 40% auto ;
            background-position: center ;
            background-repeat: no-repeat;
            margin: 0;
            height: 60vh;
            display: flex;
            flex-direction: column;
            justify-content: right;
            align-items: center;
            color: rebeccapurple;
        }

        h1 {
            color: rebeccapurple;
            font-size: xx-large;
            font-weight: bold;
        }

        #registrationForm {
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
            margin-top: 20px;
            max-width: 400px;
        }

        label {
            color: rebeccapurple;
            display: block;
            margin: 10px 0;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        button {
            background-color:rebeccapurple;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: darkgreen;
        }

        a {
            text-decoration: none;
            color: rebeccapurple;
            margin: 0 10px;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>

</head>
<body>
<h1>WELCOME TO ONOME'S KITCHEN!</h1>
<div id="registrationForm">
    <form action="/register" method="post">
        <label for="firstname">First Name:</label>
        <input type="text" id="firstname" name="firstname" required>

        <label for="surname">Last Name:</label>
        <input type="text" id="surname" name="surname" required>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Register</button>
    </form>
    <p>Already have an account? <a href="login.jsp">Login</a></p>
</div>
</body>
</html>
