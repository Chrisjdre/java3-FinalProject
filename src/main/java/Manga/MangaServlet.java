package Manga;

import Data_access.AnimeAPI;
import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.manga.Manga;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static java.lang.Long.parseLong;

@WebServlet(name = "MangaServlet", value = "/manga")
public class MangaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mangaid = request.getQueryString();
        if(mangaid == null){
            response.sendRedirect("mangasearch");
            return;
        }
        try {
            Manga manga = AnimeAPI.getManga(parseLong(mangaid));
            request.setAttribute("manga", manga);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Could not get manga data.");
        }
//        for(int i = 0; i < 10; i++){
//            System.out.println("manga");
//        }

        request.getRequestDispatcher("WEB-INF/Manga/manga.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
