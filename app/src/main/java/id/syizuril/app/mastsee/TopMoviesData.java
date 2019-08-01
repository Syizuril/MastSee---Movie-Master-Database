package id.syizuril.app.mastsee;

import java.util.ArrayList;
import java.util.Collection;

import id.syizuril.app.mastsee.models.MoviesTVShows;

public class TopMoviesData {
    public static String [][] data = new String[][]{
            {"Dilwale Dulhania Le Jayenge","October 20, 1995","90","Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.","Aditya Chopra","Released","3h 10m","Comedy, Drama, Romance","https://image.tmdb.org/t/p/w600_and_h900_bestv2/uC6TTUhPpQCmgldGyYveKRAu8JN.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/mMaxGuQKuH4WUHBwUNhJOetDYE9.jpg"},
            {"Parasite","October 11, 2019","88","All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.","Bong Joon-ho\nHan Jin-won","Released","2h 12m","Drama, Thriller","https://image.tmdb.org/t/p/w600_and_h900_bestv2/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/ny5aCtglk2kceGAuAdiyqbhBBAf.jpg"},
            {"The Shawshank Redemption","June 3, 1995","87","Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.","Frank Darabont\nStephen King","Released","2h 22m","Drama, Crime","https://image.tmdb.org/t/p/w600_and_h900_bestv2/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/j9XKiZrVeViAixVRzCta7h1VU9W.jpg"},
            {"The Godfather","June 30, 2016","86","Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.","Francis Ford Coppola\nMario Puzo","Released","2h 55m","Drama, Crime","https://image.tmdb.org/t/p/w600_and_h900_bestv2/rPdtLWNsZmAtoZl9PK7S2wE3qiS.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/6xKCYgH16UuwEGAyroLU6p8HLIn.jpg"},
            {"Your Name.","August 26, 2016","86","High schoolers Mitsuha and Taki are complete strangers living separate lives. But one night, they suddenly switch places. Mitsuha wakes up in Taki’s body, and he in hers. This bizarre occurrence continues to happen randomly, and the two must adjust their lives around each other.","Makoto Shinkai","Released","1h 46m","Romance, Animation, Drama","https://image.tmdb.org/t/p/w600_and_h900_bestv2/xq1Ugd62d23K2knRUx6xxuALTZB.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/nvsdKYPKwf51EAgC0xLJMB9kUZM.jpg"},
            {"Schindler's List","February 26, 1994","85","The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.","Steven Spielberg\nThomas Keneally\nSteven Zaillian","Released","3h 15m","Drama, History, War","https://image.tmdb.org/t/p/w600_and_h900_bestv2/yPisjyLweCl1tbgwgtzBCNCBle.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/af98P1bc7lJsFjhHOVWXQgNNgSQ.jpg"},
            {"The Godfather: Part II","December 20, 1974","85","In the continuing saga of the Corleone crime family, a young Vito Corleone grows up in Sicily and in 1910s New York. In the 1950s, Michael Corleone attempts to expand the family business into Las Vegas, Hollywood and Cuba.","Francis Ford Coppola\nMario Puzo","Released","3h 22m","Drama, Crime","https://image.tmdb.org/t/p/w600_and_h900_bestv2/bVq65huQ8vHDd1a4Z37QtuyEvpA.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/gLbBRyS7MBrmVUNce91Hmx9vzqI.jpg"},
            {"Spirited Away","July 20, 2001","85","A young girl, Chihiro, becomes trapped in a strange new world of spirits. When her parents undergo a mysterious transformation, she must call upon the courage she never knew she had to free her family.","Hayao Miyazaki","Released","2h 5m","Animation, Family, Fantasy","https://image.tmdb.org/t/p/w600_and_h900_bestv2/oRvMaJOmapypFUcQqpgHMZA6qL9.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/djgM2d3e42p9GFQObg6lwK2SVw2.jpg"},
            {"The Green Mile","March 25, 2000","85","A supernatural tale set on death row in a Southern prison, where gentle giant John Coffey possesses the mysterious power to heal people's ailments. When the cell block's head guard, Paul Edgecomb, recognizes Coffey's miraculous gift, he tries desperately to help stave off the condemned man's execution.","Frank Darabont\nStephen King","Released","3h 9m","Fantasy, Drama Crime","https://image.tmdb.org/t/p/w600_and_h900_bestv2/sOHqdY1RnSn6kcfAHKu28jvTebE.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/Rlt20sEbOQKPVjia7lUilFm49W.jpg"},
            {"Life Is Beautiful","October 22, 1998","85","A touching story of an Italian book seller of Jewish ancestry who lives in his own little fairy tale. His creative and happy life would come to an abrupt halt when his entire family is deported to a concentration camp during World War II. While locked up he tries to convince his son that the whole thing is just a game.","Roberto Benigni\nVincenzo Cerami","Released","1h 56m","Comedy, Drama","https://image.tmdb.org/t/p/w600_and_h900_bestv2/f7DImXDebOs148U4uPjI61iDvaK.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/bORe0eI72D874TMawOOFvqWS6Xe.jpg"},
    };

    public static Collection<? extends MoviesTVShows> getListData(){
        ArrayList<MoviesTVShows> listTop = new ArrayList<>();
        for(String[] aData : data){
            MoviesTVShows topMovies = new MoviesTVShows();
            topMovies.setTitle(aData[0]);
            topMovies.setDate(aData[1]);
            topMovies.setScore(aData[2]);
            topMovies.setOverview(aData[3]);
            topMovies.setCrew(aData[4]);
            topMovies.setStatus(aData[5]);
            topMovies.setRuntime(aData[6]);
            topMovies.setGenre(aData[7]);
            topMovies.setCover(aData[8]);
            topMovies.setBanner(aData[9]);

            listTop.add(topMovies);
        }
        return listTop;
    }
}
