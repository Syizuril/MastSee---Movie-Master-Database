package id.syizuril.app.mastsee.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TvShowsResult implements Parcelable {

    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("origin_country")
    @Expose
    private List<String> originCountry = null;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("first_air_date")
    @Expose
    private Date firstAirDate;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Date getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(Date firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w533_and_h300_bestv2/"+backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2/"+posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalName);
        dest.writeList(this.genreIds);
        dest.writeString(this.name);
        dest.writeValue(this.popularity);
        dest.writeStringList(this.originCountry);
        dest.writeValue(this.voteCount);
        dest.writeLong(this.firstAirDate != null ? this.firstAirDate.getTime() : -1);
        dest.writeString(this.backdropPath);
        dest.writeString(this.originalLanguage);
        dest.writeValue(this.id);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
    }

    public TvShowsResult() {
    }

    protected TvShowsResult(Parcel in) {
        this.originalName = in.readString();
        this.genreIds = new ArrayList<Integer>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.name = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.originCountry = in.createStringArrayList();
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        long tmpFirstAirDate = in.readLong();
        this.firstAirDate = tmpFirstAirDate == -1 ? null : new Date(tmpFirstAirDate);
        this.backdropPath = in.readString();
        this.originalLanguage = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.overview = in.readString();
        this.posterPath = in.readString();
    }

    public static final Parcelable.Creator<TvShowsResult> CREATOR = new Parcelable.Creator<TvShowsResult>() {
        @Override
        public TvShowsResult createFromParcel(Parcel source) {
            return new TvShowsResult(source);
        }

        @Override
        public TvShowsResult[] newArray(int size) {
            return new TvShowsResult[size];
        }
    };
}
