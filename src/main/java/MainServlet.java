import Data_access.AnimeAPI;
import dev.katsute.mal4j.anime.Anime;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MainServlet", value = "")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Anime> animeList = null;
        try{
            animeList = AnimeAPI.getAnimeRec();
        }catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("myName", "Your Name");
        request.setAttribute("animeList", animeList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}

