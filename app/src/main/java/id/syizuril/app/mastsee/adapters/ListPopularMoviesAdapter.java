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

import java.text.SimpleDateFormat;
import java.util.List;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.models.MovieResult;

public class ListPopularMoviesAdapter extends RecyclerView.Adapter<ListPopularMoviesAdapter.ListViewHolder> {
    private Context mContext;
    private List<MovieResult> listPopularMovies;

    public ListPopularMoviesAdapter(Context mContext, List<MovieResult> listPopularMovies) {
        this.mContext = mContext;
        this.listPopularMovies = listPopularMovies;
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

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        MovieResult popularMovies = listPopularMovies.get(position);
        Glide.with(holder.itemView.getContext())
                .load(popularMovies.getPosterPath())
                .apply(new RequestOptions().override(500,750))
                .into(holder.imgCover);
        holder.tvTitle.setText(popularMovies.getOriginalTitle());
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        String date = formatter.format(popularMovies.getReleaseDate());
        holder.tvDate.setText(date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listPopularMovies.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPopularMovies.size();
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
            tvCrew = itemView.findViewById(R.id.tvCrew);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvRuntime = itemView.findViewById(R.id.tvRuntime);
            tvGenre = itemView.findViewById(R.id.tvGenre);
            imgCover = itemView.findViewById(R.id.imgCover);
            imgBanner = itemView.findViewById(R.id.banner);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(MovieResult data);
    }
}
