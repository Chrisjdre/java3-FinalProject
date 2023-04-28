<%@ page import="Account.User" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getSession().getAttribute("user");
    boolean userNSFW = user.getPrivileges().equals("NSFW");

    Map<String, String> results = (Map<String, String>)request.getAttribute("results");
    if(results == null) {
        results = new HashMap<>();
    }

    String firstNameError = results.containsKey("firstNameError") ? results.get("firstNameError") : "";
    String lastNameError = results.containsKey("lastNameError") ? results.get("lastNameError") : "";
    String emailError = results.containsKey("emailError") ? results.get("emailError") : "";

    String firstNameInvalid = results.containsKey("firstNameError") ? "is-invalid" : "";
    String lastNameInvalid = results.containsKey("lastNameError") ? "is-invalid" : "";
    String emailInvalid = results.containsKey("emailError") ? "is-invalid" : "";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Account</title>
    <jsp:include page="../nav.jsp"></jsp:include>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h1 class="mb-4">User Account Settings</h1>
            <form action="account" method="post">
                <div class="mb-3">
                    <label for="fname" class="form-label">First Name</label>
                    <input type="text" class="form-control <%= firstNameInvalid %>"  id="fname" name="fname" placeholder="Enter your first name" value="<%= user.getFirst_name()%>" >
                    <div class="invalid-feedback"><%= firstNameError %></div>
                </div>
                <div class="mb-3">
                    <label for="lname" class="form-label">Last Name</label>
                    <input type="text" class="form-control <%= lastNameInvalid %>" id="lname" name="lname" placeholder="Enter your last name"value="<%= user.getLast_name()%>">
                    <div class="invalid-feedback"><%= lastNameError %></div>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email address</label>
                    <input type="email" class="form-control <%= emailInvalid %>" id="email" name="email" placeholder="Enter your email" value="<%= user.getEmail()%>">
                    <div class="invalid-feedback"><%= emailError %></div>
                </div>
                <div class="form-check mb-3">
                    <input type="checkbox" class="form-check-input" id="nsfw" name="nsfw" <%= (userNSFW ? "checked" : "") %>  >
                    <label for="nsfw" class="form-check-label">Enable NSFW mode</label>
                </div>
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">Update Settings</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
</body>
</html>