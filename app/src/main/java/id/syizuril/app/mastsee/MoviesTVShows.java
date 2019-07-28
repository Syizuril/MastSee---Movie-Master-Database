package id.syizuril.app.mastsee;

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
    private String banner;
    private String altbanner;

    String getAltbanner() {
        return altbanner;
    }

    void setAltbanner(String altbanner) {
        this.altbanner = altbanner;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getScore() {
        return score;
    }

    void setScore(String score) {
        this.score = score;
    }

    String getDate() {
        return date;
    }

    void setDate(String date) {
        this.date = date;
    }

    String getOverview() {
        return overview;
    }

    void setOverview(String overview) {
        this.overview = overview;
    }

    String getCrew() {
        return crew;
    }

    void setCrew(String crew) {
        this.crew = crew;
    }

    String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }

    String getRuntime() {
        return runtime;
    }

    void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    String getGenre() {
        return genre;
    }

    void setGenre(String genre) {
        this.genre = genre;
    }

    String getCover() {
        return cover;
    }

    void setCover(String cover) {
        this.cover = cover;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public MoviesTVShows() {
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

    protected MoviesTVShows(Parcel in) {
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
