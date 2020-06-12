package id.syizuril.app.mastsee.requests;

import id.syizuril.app.mastsee.models.MovieResponse;
import id.syizuril.app.mastsee.models.TvShowResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public interface MovieApi {

    @GET("/3/{type}/{category}")
    Call<MovieResponse> getMovies(
            @Path("type") String type,
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("/3/{type}/{category}")
    Call<TvShowResponse> getTvShows(
            @Path("type") String type,
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("/3/{type}/{category}")
    Call<MovieResponse> getSearchMovies(
            @Path("type") String type,
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query
    );

    @GET("/3/{type}/{category}")
    Call<TvShowResponse> getSearchTvShows(
            @Path("type") String type,
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query
    );

    @GET("/3/{type}/{category}")
    Call<MovieResponse> getMovieRelease(
            @Path("type") String type,
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("primary_release_date.gte") String releaseDate,
            @Query("primary_release_date.lte") String reReleaseDate
    );
}
