package id.syizuril.app.mastsee.adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.List;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.models.SearchResult;

public class ListSearchMoviesAdapter extends RecyclerView.Adapter<ListSearchMoviesAdapter.ListViewHolder> {
    private List<SearchResult> listSearchMovies;

    public ListSearchMoviesAdapter(List<SearchResult> listSearchMovies) {
        this.listSearchMovies = listSearchMovies;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movies, viewGroup, false);
        return new ListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        SearchResult searchMovies = listSearchMovies.get(position);
        Glide.with(holder.itemView.getContext())
                .load(searchMovies.getPosterPath())
                .apply(new RequestOptions().override(500,750))
                .into(holder.imgCover);

        try {
            holder.tvName.setText(searchMovies.getName());
        } catch (NullPointerException e) {
            holder.tvName.setText("");
        }
        try {
            holder.tvTitle.setText(searchMovies.getTitle());
        } catch (NullPointerException e) {
            holder.tvTitle.setText("");
        }

        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
            String firstDate = formatter.format(searchMovies.getFirstAirDate());
            holder.tvFirstDate.setText(firstDate);
        } catch (NullPointerException e) {
            String firstDate = "";
            holder.tvFirstDate.setText(firstDate);
        }
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
            String date = formatter.format(searchMovies.getReleaseDate());
            holder.tvDate.setText(date);
        } catch (NullPointerException e) {
            String date = "";
            holder.tvDate.setText(date);
        }

        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listSearchMovies.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return listSearchMovies.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover, imgBanner;
        TextView tvTitle,tvDate, tvScore, tvOverview, tvVoteCount, tvOriginalLanguage, tvOriginalTitle, tvPopularityPoint, tvName, tvFirstDate;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            tvVoteCount = itemView.findViewById(R.id.tvCount);
            tvOriginalLanguage = itemView.findViewById(R.id.tvOriginalLanguage);
            tvOriginalTitle = itemView.findViewById(R.id.tvOriginalTitle);
            tvPopularityPoint = itemView.findViewById(R.id.tvPopularityPoint);
            tvName = itemView.findViewById(R.id.tvName);
            tvFirstDate = itemView.findViewById(R.id.tvFirstDate);
            imgCover = itemView.findViewById(R.id.imgCover);
            imgBanner = itemView.findViewById(R.id.banner);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(SearchResult data);
    }
}
