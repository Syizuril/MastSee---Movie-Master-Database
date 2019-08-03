package id.syizuril.app.mastsee;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import id.syizuril.app.mastsee.models.MoviesTVShows;

public class ListUpcomingMoviesAdapter extends RecyclerView.Adapter<ListUpcomingMoviesAdapter.ListViewHolder>{
    private ArrayList<MoviesTVShows> listUpcomingMovies;
    private OnItemClickCallback onItemClickCallback;

    void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    ListUpcomingMoviesAdapter(ArrayList<MoviesTVShows> listUpcomingMovies) {
        this.listUpcomingMovies = listUpcomingMovies;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_upcoming_movies, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        MoviesTVShows upcomingMovies = listUpcomingMovies.get(position);

        Glide.with(holder.itemView.getContext())
                .load(upcomingMovies.getAltbanner())
                .apply(new RequestOptions().override(400,190))
                .into(holder.imgAltBanner);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listUpcomingMovies.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUpcomingMovies.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover, imgBanner, imgAltBanner;
        TextView tvTitle,tvDate, tvScore, tvOverview, tvCrew, tvStatus, tvRuntime, tvGenre;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            imgCover = itemView.findViewById(R.id.imgCover);
            imgBanner = itemView.findViewById(R.id.banner);
            imgAltBanner = itemView.findViewById(R.id.banner_upcoming_movies);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(MoviesTVShows data);
    }
}

