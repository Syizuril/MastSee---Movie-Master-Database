package id.syizuril.app.mastsee.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = MovieResult.TABLE_NAME)
public class MovieResult implements Parcelable {
    public static final String TABLE_NAME = "movie_table";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_VOTE_COUNT = "vote_count";
    public static final String COLUMN_VOTE_AVERAGE = "vote_average";
    public static final String COLUMN_TILE = "title";
    public static final String COLUMN_POPULARITY = "popularity";
    public static final String COLUMN_POSTER = "poster";
    public static final String COLUMN_ORI_LANG = "ori_lang";
    public static final String COLUMN_ORI_TITLE = "ori_title";
    public static final String COLUMN_BACKDROP = "backdrop";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_RELEASE = "release_date";


    @SerializedName("vote_count")
    @Expose
    @ColumnInfo(name = COLUMN_VOTE_COUNT)
    public Integer voteCount;
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(index = true,name = COLUMN_ID)
    public Long id;
    @SerializedName("vote_average")
    @Expose
    @ColumnInfo(name = COLUMN_VOTE_AVERAGE)
    public Double voteAverage;
    @SerializedName("title")
    @Expose
    @ColumnInfo(name = COLUMN_TILE)
    public String title;
    @SerializedName("popularity")
    @Expose
    @ColumnInfo(name = COLUMN_POPULARITY)
    public Double popularity;
    @SerializedName("poster_path")
    @Expose
    @ColumnInfo(name = COLUMN_POSTER)
    public String posterPath;
    @SerializedName("original_language")
    @Expose
    @ColumnInfo(name = COLUMN_ORI_LANG)
    public String originalLanguage;
    @SerializedName("original_title")
    @Expose
    @ColumnInfo(name = COLUMN_ORI_TITLE)
    public String originalTitle;
    @SerializedName("backdrop_path")
    @Expose
    @ColumnInfo(name = COLUMN_BACKDROP)
    public String backdropPath;
    @SerializedName("overview")
    @Expose
    @ColumnInfo(name = COLUMN_OVERVIEW)
    public String overview;
    @SerializedName("release_date")
    @Expose
    @ColumnInfo(name = COLUMN_RELEASE)
    public Date releaseDate;

    @Ignore
    public MovieResult() {
    }

    public static MovieResult fromContentValues(ContentValues values){
        final MovieResult movieResult = new MovieResult();
        if(values.containsKey(COLUMN_ID)){
            movieResult.id = values.getAsLong(COLUMN_ID);
        }
        if(values.containsKey(COLUMN_VOTE_COUNT)){
            movieResult.voteCount = values.getAsInteger(COLUMN_VOTE_COUNT);
        }
        if(values.containsKey(COLUMN_VOTE_AVERAGE)){
            movieResult.voteAverage = values.getAsDouble(COLUMN_VOTE_AVERAGE);
        }
        if(values.containsKey(COLUMN_TILE)){
            movieResult.title = values.getAsString(COLUMN_TILE);
        }
        if(values.containsKey(COLUMN_POPULARITY)){
            movieResult.popularity = values.getAsDouble(COLUMN_POPULARITY);
        }
        if(values.containsKey(COLUMN_POSTER)){
            movieResult.posterPath = values.getAsString(COLUMN_POSTER);
        }
        if(values.containsKey(COLUMN_ORI_LANG)){
            movieResult.originalLanguage = values.getAsString(COLUMN_ORI_LANG);
        }
        if(values.containsKey(COLUMN_ORI_TITLE)){
            movieResult.originalTitle = values.getAsString(COLUMN_ORI_TITLE);
        }
        if(values.containsKey(COLUMN_BACKDROP)){
            movieResult.backdropPath = values.getAsString(COLUMN_BACKDROP);
        }
        if(values.containsKey(COLUMN_OVERVIEW)){
            movieResult.overview = values.getAsString(COLUMN_OVERVIEW);
        }
        if(values.containsKey(COLUMN_RELEASE)){
            movieResult.releaseDate = (Date) values.get(COLUMN_RELEASE);
        }
        return movieResult;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2/"+posterPath;
    }

    public String getPosterPathAlt(){
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w533_and_h300_bestv2/"+backdropPath;
    }

    public String getBackdropPathAlt(){
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.voteCount);
        dest.writeValue(this.id);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.title);
        dest.writeValue(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalTitle);
        dest.writeString(this.backdropPath);
        dest.writeString(this.overview);
        dest.writeLong(this.releaseDate != null ? this.releaseDate.getTime() : -1);
    }

    protected MovieResult(Parcel in) {
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Long) in.readValue(Integer.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.posterPath = in.readString();
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.backdropPath = in.readString();
        this.overview = in.readString();
        long tmpReleaseDate = in.readLong();
        this.releaseDate = tmpReleaseDate == -1 ? null : new Date(tmpReleaseDate);
    }

    public static final Parcelable.Creator<MovieResult> CREATOR = new Parcelable.Creator<MovieResult>() {
        @Override
        public MovieResult createFromParcel(Parcel source) {
            return new MovieResult(source);
        }

        @Override
        public MovieResult[] newArray(int size) {
            return new MovieResult[size];
        }
    };

    public MovieResult(Integer voteCount, Long id, Double voteAverage, String title, Double popularity, String posterPath, String originalLanguage, String originalTitle, String backdropPath, String overview, Date releaseDate) {
        this.voteCount = voteCount;
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }
}
