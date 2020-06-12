package id.syizuril.app.mastsee.requests;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class RetrofitService {
    private static String BASE_URL = "https://api.themoviedb.org";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }

    public static MovieApi getMovieApi(){
        return retrofit.create(MovieApi.class);
    }
}
