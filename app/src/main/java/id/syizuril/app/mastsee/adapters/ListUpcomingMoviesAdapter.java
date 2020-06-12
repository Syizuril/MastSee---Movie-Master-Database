package id.syizuril.app.mastsee.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.models.MovieResult;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class ListUpcomingMoviesAdapter extends RecyclerView.Adapter<ListUpcomingMoviesAdapter.ListViewHolder> {
    private List<MovieResult> listUpcomingMovies;

    public ListUpcomingMoviesAdapter(List<MovieResult> listUpcomingMovies) {
        this.listUpcomingMovies = listUpcomingMovies;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_upcoming_movies, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        MovieResult upcomingMovies = listUpcomingMovies.get(position);
        Glide.with(holder.itemView.getContext())
                .load(upcomingMovies.getBackdropPath())
                .apply(new RequestOptions().override(400,190))
                .into(holder.imgAltBanner);
        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listUpcomingMovies.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        if (listUpcomingMovies.size()>3){
            return 3;
        }
        return listUpcomingMovies.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover, imgBanner, imgAltBanner;
        TextView tvTitle,tvDate, tvScore, tvOverview, tvVoteCount, tvOriginalLanguage, tvOriginalTitle, tvPopularityPoint;

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
            imgCover = itemView.findViewById(R.id.imgCover);
            imgBanner = itemView.findViewById(R.id.banner);
            imgAltBanner = itemView.findViewById(R.id.banner_upcoming_movies);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(MovieResult data);
    }
}
