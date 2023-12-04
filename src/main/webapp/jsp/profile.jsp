
<%--
  Created by IntelliJ IDEA.
  User: doosuur
  Date: 01.12.2023
  Time: 12:35 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="java.util.List" %>
<%@ page import="Models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Onome's Kitchen - user profile</title>
    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }

        #profileImage {
            max-width: 200px;
            max-height: 200px;
            border-radius: 50%;
            margin-bottom: 16px;
        }

        header {
            background-color: rebeccapurple;
            color: white;
            text-align: center;
            padding: 1em;
        }

        h1 {
            color: #333;
        }

        form {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        textarea,
        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: rebeccapurple;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: mediumpurple;
        }
        a {
            text-decoration: none;
            color: black;
            margin: 0 10px;
        }
    </style>

</head>
<body>
<h2>Welcome, ${user.firstName} ${user.lastName}!</h2>

<form action="profile" method="post" enctype="multipart/form-data">


<%--    <img src="http://localhost:8080/Users/doosuur/IdeaProjects/ProjectWork/src/main/webapp/files/${user.userProfile.fileInfo.storageFileName}" alt="User Profile Image"><br>--%>
    <img id="profileImage" src="http://localhost:8080/profile-image?fileName=${user.userProfile.fileInfo.storageFileName}" alt="User Profile Image"><br>
    <label for="profilePhoto">Profile Photo:</label>
    <input type="file" id="profilePhoto" name="profilePhoto"><br>

    <label for="bio">Bio:</label>
    <textarea id="bio" name="bio">${user.getUserProfile().getBio()}</textarea><br>

    <label for="favFoodCategory">Favorite Food Category:</label>
<%--    <input type="text" id="favfoodcategory" name="favfoodcategory" value="${user.getUserProfile().getFavFoodCategory()}"><br>--%>
    <textarea id="favFoodCategory" name="favFoodCategory">${user.getUserProfile().getFavFoodCategory()}</textarea><br>

<%--    <input type="submit" value="Update Profile">--%>
</form>
<a href="jsp/recipe.jsp">Go to Recipe Page</a>

<script>

    function updateProfileImage() {
        var input = document.getElementById('profilePhoto');
        var image = document.getElementById('profileImage');

        input.addEventListener('change', function () {

            var file = input.files[0];
            var objectURL = URL.createObjectURL(file);
            image.src = objectURL;
        });
    }

    window.onload = function () {
        updateProfileImage();
    };
</script>
</body>
</html>
