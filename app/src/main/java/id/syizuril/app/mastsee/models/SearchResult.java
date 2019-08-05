package id.syizuril.app.mastsee.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchResult implements Parcelable {
    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("id")
    @Expose
    private Double id;
    @SerializedName("media_type")
    @Expose
    private String mediaType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("vote_count")
    @Expose
    private Double voteCount;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("first_air_date")
    @Expose
    private Date firstAirDate;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("genre_ids")
    @Expose
    private List<Double> genreIds = null;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("origin_country")
    @Expose
    private List<String> originCountry = null;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("release_date")
    @Expose
    private Date releaseDate;

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null)
        this.name = name;
    }

    public Double getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Double voteCount) {
        this.voteCount = voteCount;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2/"+posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Date getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(Date firstAirDate) {
        if(releaseDate != null)
        this.firstAirDate = firstAirDate;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public List<Double> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Double> genreIds) {
        this.genreIds = genreIds;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w533_and_h300_bestv2/"+backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title != null)
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        if(releaseDate != null)
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalName);
        dest.writeValue(this.id);
        dest.writeString(this.mediaType);
        dest.writeString(this.name);
        dest.writeValue(this.voteCount);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.posterPath);
        dest.writeLong(this.firstAirDate != null ? this.firstAirDate.getTime() : -1);
        dest.writeValue(this.popularity);
        dest.writeList(this.genreIds);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.backdropPath);
        dest.writeString(this.overview);
        dest.writeStringList(this.originCountry);
        dest.writeValue(this.video);
        dest.writeString(this.title);
        dest.writeString(this.originalTitle);
        dest.writeValue(this.adult);
        dest.writeLong(this.releaseDate != null ? this.releaseDate.getTime() : -1);
    }

    public SearchResult() {
    }

    protected SearchResult(Parcel in) {
        this.originalName = in.readString();
        this.id = (Double) in.readValue(Double.class.getClassLoader());
        this.mediaType = in.readString();
        this.name = in.readString();
        this.voteCount = (Double) in.readValue(Double.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.posterPath = in.readString();
        long tmpFirstAirDate = in.readLong();
        this.firstAirDate = tmpFirstAirDate == -1 ? null : new Date(tmpFirstAirDate);
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.genreIds = new ArrayList<Double>();
        in.readList(this.genreIds, Double.class.getClassLoader());
        this.originalLanguage = in.readString();
        this.backdropPath = in.readString();
        this.overview = in.readString();
        this.originCountry = in.createStringArrayList();
        this.video = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.title = in.readString();
        this.originalTitle = in.readString();
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        long tmpReleaseDate = in.readLong();
        this.releaseDate = tmpReleaseDate == -1 ? null : new Date(tmpReleaseDate);
    }

    public static final Creator<SearchResult> CREATOR = new Creator<SearchResult>() {
        @Override
        public SearchResult createFromParcel(Parcel source) {
            return new SearchResult(source);
        }

        @Override
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };
}
