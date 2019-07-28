package id.syizuril.app.mastsee;

import java.util.ArrayList;

public class PopularMoviesData {
    public static String [][] dataPopulerMovies = new String[][]{
            {"Spider-Man: Far from Home","June 26, 2019","78","Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.","Jon Watts\nErik Sommers\nChris McKenna","Released","2h 9m","Action, Adventure, Science Fiction","https://image.tmdb.org/t/p/w600_and_h900_bestv2/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/dihW2yTsvQlust7mSuAqJDtqW7k.jpg"},
            {"Alita: Battle Angel","February 14, 2019","67","When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.","Robert Rodriguez\nLaeta Kalogridis","Released","2h 2m","Action, Science Fiction, Thriller, Adventure","https://image.tmdb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/8RKBHHRqOMOLh5qW3sS6TSFTd8h.jpg"},
            {"John Wick: Chapter 3 – Parabellum","May 9, 2019","71","Super-assassin John Wick returns with a $14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn.","Derek Kolstad\nChad Stahelski\nMarc Abrams\nChris Collins","Released","2h 11m","Crime, Action, Thriller","https://image.tmdb.org/t/p/w600_and_h900_bestv2/ziEuG1essDuWuC5lpWUaw1uXY2O.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/vVpEOvdxVBP2aV166j5Xlvb5Cdc.jpg"},
            {"Toy Story 4","June 11, 2019","78","Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.","Josh Cooley\nAndrew Stanton\nValerie LaPointe\nRashida Jones\nMartin Hynes\nJohn Lasseter","Released","1h 40m","Adventure, Animation, Comedy, Family","https://image.tmdb.org/t/p/w600_and_h900_bestv2/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/m67smI1IIMmYzCl9axvKNULVKLr.jpg"},
            {"Hellboy","April 12, 2019","50","Hellboy comes to England, where he must defeat Nimue, Merlin's consort and the Blood Queen. But their battle will bring about the end of the world, a fate he desperately tries to turn away.","Mike Mignola\nNeil Marshall\nChristopher Golden\nAndrew Cosby\nAron Eli Coleite","Released","2h 1m","Action, Adventure, Fantasy, Horror, Science Fiction","https://image.tmdb.org/t/p/w600_and_h900_bestv2/bk8LyaMqUtaQ9hUShuvFznQYQKR.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/hMbP23EkGk6tjEjRZQXhnVAl5fW.jpg"},
            {"The Lion King","July 19, 2019","60","Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.","Jonathan Roberts\nErik Sommers\nChris McKenna","Released","2h 9m","Action, Adventure, Science Fiction","https://image.tmdb.org/t/p/w600_and_h900_bestv2/dzBtMocZuJbjLOXvrl4zGYigDzh.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/1TUg5pO1VZ4B0Q1amk3OlXvlpXV.jpg"},
            {"Shazam!","March 23, 2019","71","A boy is given the ability to become an adult superhero in times of need with a single magic word.","Henry Gayden\nBill Parker\nC.C. Beck\nDavid F. Sandberg\nDarren Lemke","Released","2h 12m","Action, Comedy, Fantasy","https://image.tmdb.org/t/p/w600_and_h900_bestv2/xnopI5Xtky18MPhK40cZAGAOVeV.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/bi4jh0Kt0uuZGsGJoUUfqmbrjQg.jpg"},
            {"Annabelle Comes Home","June 26, 2019","61","Determined to keep Annabelle from wreaking more havoc, demonologists Ed and Lorraine Warren bring the possessed doll to the locked artifacts room in their home, placing her “safely” behind sacred glass and enlisting a priest’s holy blessing. But an unholy night of horror awaits as Annabelle awakens the evil spirits in the room, who all set their sights on a new target—the Warrens' ten-year-old daughter, Judy, and her friends.","Gary Dauberman","Released","1h 46m","Horror","https://image.tmdb.org/t/p/w600_and_h900_bestv2/qWsHMrbg9DsBY3bCMk9jyYCRVRs.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/dBt0DoFfbhOI4ypUfRj1uTq623M.jpg"},
            {"Captain Marvel","March 8, 2019","70","The story follows Carol Danvers as she becomes one of the universe’s most powerful heroes when Earth is caught in the middle of a galactic war between two alien races. Set in the 1990s, Captain Marvel is an all-new adventure from a previously unseen period in the history of the Marvel Cinematic Universe.","Anna Boden\nRyan Fleck\nGeneva Robertson-Dworet\nNicole Perlman\nMeg LeFauve","Released","2h 4m","Action, Adventure, Science Fiction","https://image.tmdb.org/t/p/w600_and_h900_bestv2/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/w2PMyoyLU22YvrGK3smVM9fW1jj.jpg"},
            {"Avengers: Infinity War","April 27, 2018","83","As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.","Joe Russo\nAnthony Russo\nStephen McFeely\nChristopher Markus","Released","2h 29m","Action, Adventure, Fantasy","https://image.tmdb.org/t/p/w600_and_h900_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"},
};

    static ArrayList<MoviesTVShows> getListData(){
        ArrayList<MoviesTVShows> popularList = new ArrayList<>();
        for (String[] aData: dataPopulerMovies){
            MoviesTVShows popularMovies = new MoviesTVShows();
            popularMovies.setTitle(aData[0]);
            popularMovies.setDate(aData[1]);
            popularMovies.setScore(aData[2]);
            popularMovies.setOverview(aData[3]);
            popularMovies.setCrew(aData[4]);
            popularMovies.setStatus(aData[5]);
            popularMovies.setRuntime(aData[6]);
            popularMovies.setGenre(aData[7]);
            popularMovies.setCover(aData[8]);
            popularMovies.setBanner(aData[9]);

            popularList.add(popularMovies);
        }
        return popularList;
    }
}
