<%@ page import="dev.katsute.mal4j.anime.Anime" %>
<%@ page import="java.util.List" %>
<%@ page import="dev.katsute.mal4j.property.Picture" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Anime> animeList = (List<Anime>) request.getAttribute("animeList");
    String q = (String)request.getAttribute("animeSearch");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Search for Anime</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
  <jsp:include page="../nav.jsp"></jsp:include>
</head>
<body>
<form class="d-flex m-4" role="search">
    <input class="form-control mx-auto p-2" style="width: 30%;" type="search" placeholder="Search" aria-label="Search" name="q" value="<%=q%>">
</form>
<div class="d-flex justify-content-center m-4">
    <h1>Search for Manga</h1>
    <button class="btn btn-outline-success mx-auto" type="submit">Search</button>
</div>

<div class="row row-cols-1 row-cols-md-4 g-4">
    <% for (Anime anime: animeList) {%>
    <a href="anime?<%=anime.getID()%>">
        <div class="col">
            <div class="card h-50">
                <img src="<%= anime.getMainPicture().getLargeURL() %>" class="card-img-top" alt="<%=anime.getTitle()%>">
                <div class="card-body">
                    <h5 class="card-title"><%=anime.getTitle()%></h5>
                    <%--                <p class="card-text"><%= anime.getSynopsis() %></p>--%>
                </div>
            </div>
        </div>
    </a>

    <%}%>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
</body>
</html>