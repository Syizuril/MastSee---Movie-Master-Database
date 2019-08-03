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

public class ListPopularTvShowsAdapter extends RecyclerView.Adapter<ListPopularTvShowsAdapter.ListViewHolder> {
    private ArrayList<MoviesTVShows> listPopularTvShows;
    private OnItemClickCallback onItemClickCallback;

    void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    ListPopularTvShowsAdapter(ArrayList<MoviesTVShows> listPopularTvShows) {
        this.listPopularTvShows = listPopularTvShows;
    }

    @NonNull
    @Override
    public ListPopularTvShowsAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movies,viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListPopularTvShowsAdapter.ListViewHolder holder, int position) {
        MoviesTVShows popularTV = listPopularTvShows.get(position);

        Glide.with(holder.itemView.getContext())
                .load(popularTV.getCover())
                .apply(new RequestOptions().override(500,750))
                .into(holder.imgCover);
        holder.tvTitle.setText(popularTV.getTitle());
        holder.tvDate.setText(popularTV.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listPopularTvShows.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPopularTvShows.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover, imgBanner;
        TextView tvTitle,tvDate, tvScore, tvOverview, tvCrew, tvStatus, tvRuntime, tvGenre;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            imgCover = itemView.findViewById(R.id.imgCover);
            imgBanner = itemView.findViewById(R.id.banner);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(MoviesTVShows data);
    }
}
