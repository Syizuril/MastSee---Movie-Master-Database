package id.syizuril.app.mastsee.adapters;

import android.content.Context;
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
import id.syizuril.app.mastsee.models.TvShowsResult;

public class ListAiringTvShowsAdapter extends RecyclerView.Adapter<ListAiringTvShowsAdapter.ListViewHolder> {
    private Context mContext;
    private List<TvShowsResult> listAiringTvShows;

    public ListAiringTvShowsAdapter(Context mContext, List<TvShowsResult> listAiringTvShows) {
        this.mContext = mContext;
        this.listAiringTvShows = listAiringTvShows;
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
        TvShowsResult airingTvShows = listAiringTvShows.get(position);
        Glide.with(holder.itemView.getContext())
                .load(airingTvShows.getBackdropPath())
                .apply(new RequestOptions().override(400,190))
                .into(holder.imgAltBanner);
        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listAiringTvShows.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        if (listAiringTvShows.size()>3){
            return 3;
        }
        return listAiringTvShows.size();
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
        void onItemClicked(TvShowsResult data);
    }
}
