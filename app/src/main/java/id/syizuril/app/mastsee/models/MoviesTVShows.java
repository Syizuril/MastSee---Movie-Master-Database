package id.syizuril.app.mastsee.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MoviesTVShows implements Parcelable {
    private String title;
    private String score;
    private String date;
    private String overview;
    private String crew;
    private String status;
    private String runtime;
    private String genre;
    private String cover;

    public MoviesTVShows(String title, String date, String score, String overview, String crew, String status, String runtime, String genre, String cover, String banner) {
        this.title = title;
        this.score = score;
        this.date = date;
        this.overview = overview;
        this.crew = crew;
        this.status = status;
        this.runtime = runtime;
        this.genre = genre;
        this.cover = cover;
        this.banner = banner;
        this.altbanner = altbanner;
    }

    public MoviesTVShows(){

    }
    private String banner;
    private String altbanner;

    public String getAltbanner() {
        return altbanner;
    }

    public void setAltbanner(String altbanner) {
        this.altbanner = altbanner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.score);
        dest.writeString(this.date);
        dest.writeString(this.overview);
        dest.writeString(this.crew);
        dest.writeString(this.status);
        dest.writeString(this.runtime);
        dest.writeString(this.genre);
        dest.writeString(this.cover);
        dest.writeString(this.banner);
        dest.writeString(this.altbanner);
    }

    public MoviesTVShows(Parcel in) {
        this.title = in.readString();
        this.score = in.readString();
        this.date = in.readString();
        this.overview = in.readString();
        this.crew = in.readString();
        this.status = in.readString();
        this.runtime = in.readString();
        this.genre = in.readString();
        this.cover = in.readString();
        this.banner = in.readString();
        this.altbanner = in.readString();
    }

    public static final Creator<MoviesTVShows> CREATOR = new Creator<MoviesTVShows>() {
        @Override
        public MoviesTVShows createFromParcel(Parcel source) {
            return new MoviesTVShows(source);
        }

        @Override
        public MoviesTVShows[] newArray(int size) {
            return new MoviesTVShows[size];
        }
    };
}
