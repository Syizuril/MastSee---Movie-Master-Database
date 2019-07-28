package id.syizuril.app.mastsee;

import java.util.ArrayList;

public class AiringTvShowsData {
    public static String[][] data = new String [][]{
            {"Fear the Walking Dead","2015","63","What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.","Dave Erickson\nRobert Kirkman","Returning Series","43m, 60m","Drama, Horror","https://image.tmdb.org/t/p/w600_and_h900_bestv2/aOdTWn8dXlS0tA5xl0ZBr8Ws15R.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/7r4FieFH6Eh6S55hi8c9LOiFENg.jpg","https://i.ibb.co/GVcwZcS/altbanner-fear.png"},
            {"One Piece","1999","76","Years ago, the fearsome pirate king Gol D. Roger was executed, leaving a huge pile of treasure and the famous \"One Piece\" behind. Whoever claims the \"One Piece\" will be named the new pirate king. Monkey D. Luffy, a boy who consumed one of the \"Devil's Fruits\", has it in his head that he'll follow in the footsteps of his idol, the pirate Shanks, and find the One Piece. It helps, of course, that his body has the properties of rubber and he's surrounded by a bevy of skilled fighters and thieves to help him along the way. Monkey D. Luffy brings a bunch of his crew followed by, Roronoa Zoro, Nami, Usopp, Sanji, Tony-Tony Chopper, Nico Robin, Franky, and Brook. They will do anything to get the One Piece and become King of the Pirates!","Toshinori Fukuzawa\nMiho Shiraishi\nYoshihiro Suzuki","Returning Series","25m","Action, Adventure, Comedy, Animation","https://image.tmdb.org/t/p/w600_and_h900_bestv2/gJI77i79KnRuc9mGPKADPZWAE8O.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/xgs7zAF5VSGAK0QstH1Q0ivybXz.jpg","https://i.ibb.co/4FNTctZ/altbanner-op.png"},
            {"Fairy Tail","2009","64","Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.","Shinji Ishihira\nHiroyuki Fukushima\nKiichi Suzuno","Returning Series","25m","Action, Adventure, Animation, Comedy, Sci-Fi, Fantasy","https://image.tmdb.org/t/p/w600_and_h900_bestv2/58GKcwFV3lpVOGzybeMrrNOjHpz.jpg","https://image.tmdb.org/t/p/w533_and_h300_bestv2/m2lugAG39sO0yCcuxEAu4fY5u1o.jpg","https://i.ibb.co/868cmzf/altbanner-ft.png"},
    };

    static ArrayList<MoviesTVShows> getListData(){
        ArrayList<MoviesTVShows> list = new ArrayList<>();
        for(String[] aData : data){
            MoviesTVShows moviesTVShows = new MoviesTVShows();
            moviesTVShows.setTitle(aData[0]);
            moviesTVShows.setDate(aData[1]);
            moviesTVShows.setScore(aData[2]);
            moviesTVShows.setOverview(aData[3]);
            moviesTVShows.setCrew(aData[4]);
            moviesTVShows.setStatus(aData[5]);
            moviesTVShows.setRuntime(aData[6]);
            moviesTVShows.setGenre(aData[7]);
            moviesTVShows.setCover(aData[8]);
            moviesTVShows.setBanner(aData[9]);
            moviesTVShows.setAltbanner(aData[10]);

            list.add(moviesTVShows);
        }

        return list;
    }
}
