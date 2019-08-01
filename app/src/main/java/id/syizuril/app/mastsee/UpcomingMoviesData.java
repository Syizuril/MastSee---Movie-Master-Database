package id.syizuril.app.mastsee;

import java.util.ArrayList;
import java.util.Collection;

import id.syizuril.app.mastsee.models.MoviesTVShows;

public class UpcomingMoviesData {
    public static String[][] data = new String[][]{
            {"Two Blue Stripes","July 11, 2019","75","Bima and Dara are lovers who are still in high school. At the age of 17, they were determined to copulate outside of marriage. Dara was pregnant. Both are then confronted with a life that is unimaginable for children of their age, life as parents.","Gina S. Noer","Released","1h 53m","Drama","https://image.tmdb.org/t/p/w600_and_h900_bestv2/mSkrNJ6xjA8rcrR0hGEJzDl8C5V.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/egF1yQttHQLbZFi2MpQalRfxTji.jpg","https://i.ibb.co/wg89D3M/dua-garis-biru.png"},
            {"The Lion King","July 12, 2019","67","The affaire of a married Palestinian man and a married Israeli woman in Jerusalem takes a dangerous political dimension when they are spotted in the wrong place at the wrong time leaving them to deal with more than their broken marriages.","Jonathan Roberts\nLinda Woolverton\nIrene Mecchi","Released","1h 58m","Adventure, Animation, Family, Drama","https://image.tmdb.org/t/p/w600_and_h900_bestv2/dzBtMocZuJbjLOXvrl4zGYigDzh.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/7Dd3YYyBhBC1PnaFXVNOhX2bSvS.jpg","https://i.ibb.co/r2wC5D4/lion-king.png"},
            {"The Reports on Sarah and Saleem","July 12, 2019","75","Bima and Dara are lovers who are still in high school. At the age of 17, they were determined to copulate outside of marriage. Dara was pregnant. Both are then confronted with a life that is unimaginable for children of their age, life as parents.","Muayad Alayan\nRami Musa Alayan","Released","2h 7m","Drama","https://image.tmdb.org/t/p/w600_and_h900_bestv2/wkIkeadOsytJfcGPDzbxge1bzJH.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/sFE7JGAWtr24hCYZpegWmSvbmU2.jpg","https://i.ibb.co/jfWR5sz/report-on.png"},
    };

    public static Collection<? extends MoviesTVShows> getListData() {
        ArrayList<MoviesTVShows> list = new ArrayList<>();
        for (String[] mData : data) {
            MoviesTVShows upcoming = new MoviesTVShows();
            upcoming.setTitle(mData[0]);
            upcoming.setDate(mData[1]);
            upcoming.setScore(mData[2]);
            upcoming.setOverview(mData[3]);
            upcoming.setCrew(mData[4]);
            upcoming.setStatus(mData[5]);
            upcoming.setRuntime(mData[6]);
            upcoming.setGenre(mData[7]);
            upcoming.setCover(mData[8]);
            upcoming.setBanner(mData[9]);
            upcoming.setAltbanner(mData[10]);

            list.add(upcoming);
        }
        return list;
    }
}

