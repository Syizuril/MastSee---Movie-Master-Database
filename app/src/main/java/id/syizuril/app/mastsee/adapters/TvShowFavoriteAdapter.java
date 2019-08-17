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
import java.util.ArrayList;
import java.util.List;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.models.TvShowsResult;

public class TvShowFavoriteAdapter extends RecyclerView.Adapter<TvShowFavoriteAdapter.MovieHolder> {
    private List<TvShowsResult> listTvShows = new ArrayList<>();

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movies, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieHolder holder, int position) {
        TvShowsResult tvShows = listTvShows.get(position);
        Glide.with(holder.itemView.getContext())
                .load(tvShows.getPosterPathAlt())
                .apply(new RequestOptions().override(500,750))
                .into(holder.imgCover);
        holder.tvTitle.setText(tvShows.getName());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        String date = formatter.format(tvShows.getFirstAirDate());
        holder.tvDate.setText(date);
        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listTvShows.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return listTvShows.size();
    }

    public void setTvShows(List<TvShowsResult> movieResult){
        this.listTvShows = movieResult;
        notifyDataSetChanged();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        ImageView imgCover, imgBanner;
        TextView tvTitle,tvDate, tvScore, tvOverview, tvVoteCount, tvOriginalLanguage, tvOriginalTitle, tvPopularityPoint;

        MovieHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            tvVoteCount = itemView.findViewById(R.id.tvCount);
            tvOriginalLanguage = itemView.findViewById(R.id.tvOriginalLanguage);
            tvOriginalTitle = itemView.findViewById(R.id.tvOriginalTitle);
            tvPopularityPoint = itemView.findViewById(R.id.tvPopularityPoint);
            imgCover = itemView.findViewById(R.id.imgCover);
            imgBanner = itemView.findViewById(R.id.banner);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(TvShowsResult data);
    }
}
