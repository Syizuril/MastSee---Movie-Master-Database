package id.syizuril.app.mastsee.requests;

import id.syizuril.app.mastsee.models.MovieResponse;
import id.syizuril.app.mastsee.models.SearchResponse;
import id.syizuril.app.mastsee.models.TvShowResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<SearchResponse> getSearchMovies(
            @Path("type") String type,
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") int page,
            @Query("include_adult") boolean includeAdult
    );
}
