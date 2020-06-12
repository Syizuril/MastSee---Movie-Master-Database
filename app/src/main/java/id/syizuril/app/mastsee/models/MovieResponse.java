package id.syizuril.app.mastsee.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class MovieResponse {
    @SerializedName("results")
    @Expose
    private List<MovieResult> results = null;

    public List<MovieResult> getResults() {
        return results;
    }
}
