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
import id.syizuril.app.mastsee.models.MovieResult;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class ListSearchMoviesAdapter extends RecyclerView.Adapter<ListSearchMoviesAdapter.ListViewHolder> {
    private List<MovieResult> listSearchMovies;

    public ListSearchMoviesAdapter(List<MovieResult> listSearchMovies) {
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
        MovieResult popularMovies = listSearchMovies.get(position);
        Glide.with(holder.itemView.getContext())
                .load(popularMovies.getPosterPath())
                .apply(new RequestOptions().override(500,750))
                .into(holder.imgCover);
        holder.tvTitle.setText(popularMovies.getTitle());
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
            String date = formatter.format(popularMovies.getReleaseDate());
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
        void onItemClicked(MovieResult data);
    }
}
