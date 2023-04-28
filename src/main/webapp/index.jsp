<%@ page import="java.util.List" %>
<%@ page import="dev.katsute.mal4j.anime.Anime" %>
<%@ page import="java.util.ArrayList" %><%
    String myName = (String)request.getAttribute("myName");
    List<Anime> animeList = (List<Anime>)request.getAttribute("animeList");
    if(animeList == null){
        animeList = new ArrayList<>();
    }
%>
<html>
<head>

    <jsp:include page="WEB-INF/nav.jsp"/>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<body>
<h1>Spring 2023 Anime</h1>
<div class="row">
    <div class="row row-cols-1 row-cols-md-6 g-4">
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
</div>
<div class="row">

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>

