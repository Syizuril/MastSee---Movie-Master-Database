package id.syizuril.app.mastsee.requests;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static String BASE_URL = "https://api.themoviedb.org";
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @SuppressLint("SimpleDateFormat")
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                @Override
                public Date deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    try {
                        return df.parse(json.getAsString());
                    } catch (ParseException e) {
                        return null;
                    }
                }
            })
            .create();
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}
