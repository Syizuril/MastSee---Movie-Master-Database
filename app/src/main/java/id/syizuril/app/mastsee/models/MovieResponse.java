package id.syizuril.app.mastsee.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    @Expose
    private List<MovieResult> results = null;

    public List<MovieResult> getResults() {
        return results;
    }
}
