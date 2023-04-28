package Anime;

import Data_access.AnimeAPI;
import dev.katsute.mal4j.anime.Anime;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static java.lang.Long.parseLong;

@WebServlet(name = "AnimeServlet", value = "/anime")
public class AnimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String animeid = request.getQueryString();
        if(animeid == null){
            response.sendRedirect("animesearch");
            return;
        }
        try {
            Anime anime = AnimeAPI.getAnime(parseLong(animeid));
            request.setAttribute("anime", anime);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Could not get anime data.");
        }

        request.getRequestDispatcher("WEB-INF/Anime/anime.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
