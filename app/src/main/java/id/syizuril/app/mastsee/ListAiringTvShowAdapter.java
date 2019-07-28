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

import java.util.ArrayList;

import id.syizuril.app.mastsee.models.MoviesTVShows;
import id.syizuril.app.mastsee.R;

public class ListAiringTvShowAdapter extends RecyclerView.Adapter<ListAiringTvShowAdapter.ListViewHolder> {
    private ArrayList<MoviesTVShows> listAiringTvShow;
    private OnItemClickCallback onItemClickCallback;

    void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    ListAiringTvShowAdapter(ArrayList<MoviesTVShows> listAiringTvShow) {
        this.listAiringTvShow = listAiringTvShow;
    }

    @NonNull
    @Override
    public ListAiringTvShowAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_upcoming_movies, viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAiringTvShowAdapter.ListViewHolder holder, int position) {
        MoviesTVShows moviesTVShows = listAiringTvShow.get(position);
        Glide.with(holder.itemView.getContext())
                .load(moviesTVShows.getAltbanner())
                .apply(new RequestOptions().override(400,190))
                .into(holder.imgAltBanner);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listAiringTvShow.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAiringTvShow.size();
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
            tvCrew = itemView.findViewById(R.id.tvCrew);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvRuntime = itemView.findViewById(R.id.tvRuntime);
            tvGenre = itemView.findViewById(R.id.tvGenre);
            imgCover = itemView.findViewById(R.id.imgCover);
            imgBanner = itemView.findViewById(R.id.banner);
            imgAltBanner = itemView.findViewById(R.id.banner_upcoming_movies);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(MoviesTVShows data);
    }
}