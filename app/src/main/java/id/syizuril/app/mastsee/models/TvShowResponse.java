package id.syizuril.app.mastsee.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
    @SerializedName("results")
    @Expose
    private List<TvShowsResult> results = null;

    public List<TvShowsResult> getResults() {
        return results;
    }
}
