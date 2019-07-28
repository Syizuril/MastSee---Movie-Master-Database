package id.syizuril.app.mastsee;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements View.OnClickListener {
    private RecyclerView rvUpcomingMovies, rvPopularMovies, rvTopMovies;
    private ArrayList<MoviesTVShows> listUpcomingMovies = new ArrayList<>();
    private ArrayList<MoviesTVShows> listPopularMogies = new ArrayList<>();
    private ArrayList<MoviesTVShows> listTopMovies = new ArrayList<>();
    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        rvUpcomingMovies = view.findViewById(R.id.rv_upcoming_movie);
        rvPopularMovies = view.findViewById(R.id.rv_populer_movie);
        rvTopMovies = view.findViewById(R.id.rv_top_movies);
        TextView tvSeeMorePopular = view.findViewById(R.id.seeMorePopular);
        TextView tvSeeMoreTop = view.findViewById(R.id.seeMoreTop);

        rvUpcomingMovies.setHasFixedSize(true);
        rvPopularMovies.setHasFixedSize(true);
        rvTopMovies.setHasFixedSize(true);
        tvSeeMorePopular.setOnClickListener(this);
        tvSeeMoreTop.setOnClickListener(this);

        listUpcomingMovies.addAll(UpcomingMoviesData.getListData());
        listPopularMogies.addAll(PopularMoviesData.getListData());
        listTopMovies.addAll(TopMoviesData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        rvUpcomingMovies.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvPopularMovies.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvTopMovies.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ListUpcomingMoviesAdapter listUpcomingMoviesAdapter = new ListUpcomingMoviesAdapter(listUpcomingMovies);
        ListPopularMoviesAdapter listPopularMoviesAdapter = new ListPopularMoviesAdapter(listPopularMogies);
        ListTopMoviesAdapter listTopMoviesAdapter = new ListTopMoviesAdapter(listTopMovies);
        rvUpcomingMovies.setAdapter(listUpcomingMoviesAdapter);
        rvPopularMovies.setAdapter(listPopularMoviesAdapter);
        rvTopMovies.setAdapter(listTopMoviesAdapter);

        listPopularMoviesAdapter.setOnItemClickCallback(new ListPopularMoviesAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MoviesTVShows data) {
                showSelectedMovie(data);
            }
        });
        listTopMoviesAdapter.setOnItemClickCallback(new ListTopMoviesAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MoviesTVShows data) {
                showSelectedMovie(data);
            }
        });
        listUpcomingMoviesAdapter.setOnItemClickCallback(new ListUpcomingMoviesAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MoviesTVShows data) {
                showSelectedMovie(data);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent moveIntent = new Intent(getActivity(), SeeMoreActivity.class);
        switch (v.getId()){
            case R.id.seeMorePopular:
                moveIntent.putExtra(SeeMoreActivity.EXTRA_CATEGORY,"listPopular");
                startActivity(moveIntent);
                break;
            case R.id.seeMoreTop:
                moveIntent.putExtra(SeeMoreActivity.EXTRA_CATEGORY,"listTop");
                startActivity(moveIntent);
                break;
        }
    }

    private void showSelectedMovie(MoviesTVShows moviesTVShows){
        Intent sendDataMovieTV = new Intent(this.getActivity(), DetailActivity.class);
        sendDataMovieTV.putExtra(DetailActivity.EXTRA_MOVIE, moviesTVShows);
        startActivity(sendDataMovieTV);
    }
}
