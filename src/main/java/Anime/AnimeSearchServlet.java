package Anime;

import Account.User;
import Data_access.AnimeAPI;
import dev.katsute.mal4j.anime.Anime;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AnimeSearchServlet", value = "/animesearch")
public class AnimeSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String q = request.getParameter("q");
        if(q == null || q.equals("") || q.matches("[^A-Za-z0-9 ]")){
            q = "attack on titan";
        }
        request.setAttribute("animeSearch", q);

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        List<Anime> animeList;

        if(user == null){
            animeList = AnimeAPI.searchAnime(q);
            if(isAnimelistNull(animeList)){
                response.sendRedirect(request.getContextPath());
            }
            request.setAttribute("animeList", animeList);
            request.getRequestDispatcher("WEB-INF/Anime/animesearch.jsp").forward(request, response);
            return;
        }
        if(user.getPrivileges().equals("NSFW")){
            animeList = AnimeAPI.searchAnimeNSFW(q);
            if(isAnimelistNull(animeList)){
                response.sendRedirect(request.getContextPath());
            }
            request.setAttribute("animeList", animeList);
            request.getRequestDispatcher("WEB-INF/Anime/animesearch.jsp").forward(request, response);
            return;
        }else{
            animeList = AnimeAPI.searchAnime(q);
            if(isAnimelistNull(animeList)){
                response.sendRedirect(request.getContextPath());
            }
            request.setAttribute("animeList", animeList);
            request.getRequestDispatcher("WEB-INF/Anime/animesearch.jsp").forward(request, response);
            return;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    boolean isAnimelistNull(List<Anime> animeList){
        if(animeList == null){
            return true;
        }
        return false;
    }
}
