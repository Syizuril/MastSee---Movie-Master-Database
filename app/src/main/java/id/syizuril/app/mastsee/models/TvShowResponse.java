package id.syizuril.app.mastsee.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class TvShowResponse {
    @SerializedName("results")
    @Expose
    private List<TvShowsResult> results = null;

    public List<TvShowsResult> getResults() {
        return results;
    }
}
