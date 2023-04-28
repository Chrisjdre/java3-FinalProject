<%@ page import="Account.User" %><%

User user = (User)session.getAttribute("user");
%>

<nav class="navbar navbar-expand-lg bg-body-tertiary bg-dark" data-bs-theme="dark" >
    <div class="container-fluid">
        <a class="navbar-brand" href="<%= request.getContextPath()%>">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="animesearch">Anime</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="mangasearch">Manga</a>
                </li>
                <% if(user == null) { %>
                <% } else if(user.getPrivileges().equals("admin")) { %>
                <a class="nav-link" href="view-users">Users</a>
                <% } %>

            </ul>
            <form class="d-flex" role="search">
                <%
                    if(user != null){%>
                        <a class="btn btn-light m-1" href="account">Account</a>
                        <a class="btn btn-light m-1" href="logout">Logout</a>
                <%  }else{ %>
                    <a class="btn btn-light m-1" href="login">Login</a>
                    <a class="btn btn-light m-1" href="register">Register</a>
                <%  } %>
            </form>

        </div>
    </div>
</nav>