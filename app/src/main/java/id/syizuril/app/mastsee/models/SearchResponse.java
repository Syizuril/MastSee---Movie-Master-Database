package id.syizuril.app.mastsee.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("results")
    @Expose
    private List<SearchResult> results = null;

    public List<SearchResult> getResults() {
        return results;
    }
}
