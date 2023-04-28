<%@ page import="dev.katsute.mal4j.anime.Anime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
Anime anime = (Anime) request.getAttribute("anime");
if(anime == null) {
    response.sendRedirect(request.getContextPath());
    return;
}
String error =  request.getParameter("error");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%=anime.getTitle()%>></title>
    <jsp:include page="../nav.jsp"></jsp:include>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1><%= anime.getTitle()%></h1>
    <div class="row">
        <div class="col-md-4">
            <img src="<%=anime.getMainPicture().getLargeURL()%>" alt="Example Image" class="img-fluid">
        </div>
        <div class="col-md-8">
            <div class="row mb-3">
                <div class="col-4">
                    <h3 class="fw-bold mb-0">Rank: <%=anime.getRank()%></h3>
                </div>
                <div class="col-4">
                    <h3 class="fw-bold mb-0">Popularity: <%=anime.getPopularity()%></h3>
                </div>
                <div class="col-4">
                    <h3 class="fw-bold mb-0">Episodes: <%=anime.getEpisodes()%></h3>
                </div>
            </div>
            <p class="lead"><%=anime.getSynopsis()%></p>
            <div class="d-flex justify-content-end">
<%--                <button type="button" class="btn btn-primary me-3">Add to favorites</button>--%>
<%--                <button type="button" class="btn btn-secondary">Action 2</button>--%>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
</body>
</html>