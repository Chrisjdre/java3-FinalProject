package Data_access;

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.anime.property.time.Season;
import dev.katsute.mal4j.manga.Manga;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class AnimeAPI {
    private static String clientID;

    public AnimeAPI() {

    }

    public static String getClientID() {
        Dotenv dotenv = Dotenv.load();
        clientID = dotenv.get("CLIENT_ID");
        return clientID;
    }

    public static List<Anime> searchAnime(String q) {
        MyAnimeList mal = MyAnimeList.withClientID(getClientID());

//        System.out.println(clientID);
        try {
            List<Anime> search = mal.getAnime()
                    .withQuery(q)
                    .withLimit(10)
                    .withOffset(1)
                    .includeNSFW(false)
                    .search();
            return search;
        } catch (Exception e) {
//            System.out.println("Error: " + e.getCause().getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static Anime getAnime(long id) {
        MyAnimeList mal = MyAnimeList.withClientID(getClientID());
        try {
            Anime anime = mal.getAnime(id);
            return anime;
        } catch (Exception e) {
//            System.out.println("Error: " + e.getCause().getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Anime> getAnimeRec() {
        MyAnimeList mal = MyAnimeList.withClientID(getClientID());
        Season season = Season.fromMonth(LocalDate.now().getMonth().toString());
        try {
            List<Anime> anime = mal.getAnimeSeason(Year.now().getValue(), season)
                    .withLimit(12)
                    .withOffset(1)
                    .includeNSFW(false)
                    .search();
            return anime;
        } catch (Exception e) {
//            System.out.println("Error: " + e.getCause().getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Manga> searchManga(String q) {
        MyAnimeList mal = MyAnimeList.withClientID(getClientID());
        try {
            List<Manga> search = mal.getManga()
                    .withQuery(q)
                    .withLimit(10)
                    .withOffset(1)
                    .includeNSFW(false)
                    .search();
            return search;
        } catch (Exception e) {
//            System.out.println("Error: " + e.getCause().getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static Manga getManga(long id) {
        MyAnimeList mal = MyAnimeList.withClientID(getClientID());
        try {
            Manga manga = mal.getManga(id);
            return manga;
        } catch (Exception e) {
//            System.out.println("Error: " + e.getCause().getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Anime> searchAnimeNSFW(String q) {
        MyAnimeList mal = MyAnimeList.withClientID(getClientID());

//        System.out.println(clientID);
        try {
            List<Anime> search = mal.getAnime()
                    .withQuery(q)
                    .withLimit(10)
                    .withOffset(1)
                    .includeNSFW(true)
                    .search();
            return search;
        } catch (Exception e) {
//            System.out.println("Error: " + e.getCause().getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    public static List<Manga> searchMangaNSFW(String q) {
        MyAnimeList mal = MyAnimeList.withClientID(getClientID());
        try {
            List<Manga> search = mal.getManga()
                    .withQuery(q)
                    .withLimit(10)
                    .withOffset(1)
                    .includeNSFW(true)
                    .search();
            return search;
        } catch (Exception e) {
//            System.out.println("Error: " + e.getCause().getMessage());
            e.printStackTrace();
            throw e;
        }
    }



}
