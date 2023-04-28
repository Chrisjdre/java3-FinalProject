package Manga;

import Account.User;
import Data_access.AnimeAPI;
import dev.katsute.mal4j.manga.Manga;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MangaSearchServlet", value = "/mangasearch")
public class MangaSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String q = request.getParameter("q");
        if(q == null || q.equals("") || q.matches("[^A-Za-z0-9 ]")){
            q = "attack on titan";
        }

        request.setAttribute("mangaSearch", q);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        List<Manga> mangaList = null;

        if(user == null){
            try{
                mangaList = AnimeAPI.searchManga(q);
            }catch (Exception e){
                request.setAttribute("error", "No results found");
                return;
            }
            request.setAttribute("mangaList", mangaList);
            request.getRequestDispatcher("WEB-INF/Manga/mangasearch.jsp").forward(request, response);
        }
        if(user.getPrivileges().equals("NSFW")) {
            mangaList = AnimeAPI.searchMangaNSFW(q);
            request.setAttribute("mangaList", mangaList);
            request.getRequestDispatcher("WEB-INF/Manga/mangasearch.jsp").forward(request, response);
        }else{
            mangaList = AnimeAPI.searchManga(q);
            request.setAttribute("mangaList", mangaList);
            request.getRequestDispatcher("WEB-INF/Manga/mangasearch.jsp").forward(request, response);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
