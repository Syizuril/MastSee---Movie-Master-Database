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
import id.syizuril.app.mastsee.models.MovieResult;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.MovieHolder> {
    private List<MovieResult> listMovies = new ArrayList<>();

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
        MovieResult movies = listMovies.get(position);
        Glide.with(holder.itemView.getContext())
                .load(movies.getPosterPathAlt())
                .apply(new RequestOptions().override(500,750))
                .into(holder.imgCover);
        holder.tvTitle.setText(movies.getTitle());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        String date = formatter.format(movies.getReleaseDate());
        holder.tvDate.setText(date);
        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listMovies.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public void setMovies(List<MovieResult> movieResult){
        this.listMovies = movieResult;
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
        void onItemClicked(MovieResult data);
    }
}
