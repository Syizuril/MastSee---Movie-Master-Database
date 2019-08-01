package id.syizuril.app.mastsee.repositories;

import id.syizuril.app.mastsee.models.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDbService {

    @GET("/3/{type}/{category}")
    Call<MovieResponse> getMovies(
            @Path("type") String type,
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}
