<%--<html>--%>
<%--<body>--%>
<%--<h2>Hello World!</h2>--%>
<%--</body>--%>
<%--</html>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title> Online food ordering system </title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald&display=swap" rel="stylesheet">
    <style>
        body {
            text-align: center;
            font-family: 'Oswald', sans-serif;
            /*background: linear-gradient(to right, powderblue,aliceblue);*/
            background-image: url("/images/background5.jpeg");
            background-size: cover ;
            background-position: center ;
            background-repeat: no-repeat;
            margin: 0;
            height: 40vh;
            display: flex;
            flex-direction: column;
            justify-content: right;
            align-items: center;
            color: mediumseagreen;
        }
        .circle-background {
            width: 400px;
            height: 400px;
            border-radius: 50%;
            background-size: cover;
            background-position: left;
            background-repeat: no-repeat;
            margin-left: 100px;
        }

        .circle-background:nth-child(3) {
            background-image: url("/images/background3.jpg");
        }

        #navigation {
            position: absolute;
            top: 70px;
            left: 70px;
            z-index: 1;
            color: white;
            background-color: rgba(0, 0, 0, 0.4);
            padding: 10px;
            border-radius: 10px;
            max-width: 200px;
            text-align: center;
            font-size: 30px;
        }
        p {
            color: black;
            font-size: 3em;
            font-weight: lighter;
            font-family: 'Oswald', sans-serif;
            padding: 10px;
        }

        h1 {
            color: black;
            font-size: 5em;
            font-weight: bold;
            font-family: 'Oswald', sans-serif;
            text-align: center;
            margin-top: 200px;
        }
        #loginContainter {
            font-size: 1.2em;
            margin-top: 20px;
            color: white;
        }
        #links {
            margin-top: 30px;
            color: white;
            background-color: rgba(0,0,0,0.4);
            padding: 20px;
            border-radius: 10px;
            max-width: 400px;

        }

        a {
            text-decoration: none;
            color: white;
            margin: 0 10px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%--<h1>WELCOME TO ONOME'S KITCHEN!</h1>--%>
<%--<p>"Every bite is a masterpiece"</p>--%>
<div class="circle-background"></div>
<div id="navigation">
    <a href="jsp/login.jsp">Login</a>
    <a href="jsp/register.jsp">Register</a>
</div>
<%--<div id = "loginContainer" >--%>
<%--    <p>Already have an account? <a href="jsp/login.jsp">Login</a></p>--%>
<%--    <p>Are you a new user? <a href="jsp/register.jsp">Register</a></p>--%>
<%--</div>--%>
</body>
</html>

