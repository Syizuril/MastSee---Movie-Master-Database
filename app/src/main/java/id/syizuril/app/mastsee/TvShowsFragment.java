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

import id.syizuril.app.mastsee.models.MoviesTVShows;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment implements View.OnClickListener{
    private RecyclerView rvAiringTvShow, rvPopularTvShow, rvTopTvShow;
    private ArrayList<MoviesTVShows> listTVShows = new ArrayList<>();
    private ArrayList<MoviesTVShows> listPopularTv = new ArrayList<>();
    private ArrayList<MoviesTVShows> listTopTv = new ArrayList<>();

    public TvShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        rvAiringTvShow = view.findViewById(R.id.rvAiringTV);
        rvPopularTvShow = view.findViewById(R.id.rvPopularTV);
        rvTopTvShow = view.findViewById(R.id.rvTopTV);
        TextView tvSeeMorePopular = view.findViewById(R.id.tvSeeMorePopular);
        TextView tvSeeMoreTop = view.findViewById(R.id.tvSeeMoreTop);

        rvAiringTvShow.setHasFixedSize(true);
        rvPopularTvShow.setHasFixedSize(true);
        rvTopTvShow.setHasFixedSize(true);

        tvSeeMorePopular.setOnClickListener(this);
        tvSeeMoreTop.setOnClickListener(this);

        listTVShows.addAll(AiringTvShowsData.getListData());
        listPopularTv.addAll(PopularTvShowsData.getListData());
        listTopTv.addAll(TopTvShowsData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvAiringTvShow.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvPopularTvShow.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvTopTvShow.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ListAiringTvShowAdapter listAiringTvShowAdapter= new ListAiringTvShowAdapter(listTVShows);
        ListPopularTvShowsAdapter listPopularTvShowsAdapter = new ListPopularTvShowsAdapter(listPopularTv);
        ListTopTvShowsAdapter listTopTvShowsAdapter = new ListTopTvShowsAdapter(listTopTv);
        rvAiringTvShow.setAdapter(listAiringTvShowAdapter);
        rvPopularTvShow.setAdapter(listPopularTvShowsAdapter);
        rvTopTvShow.setAdapter(listTopTvShowsAdapter);

        listAiringTvShowAdapter.setOnItemClickCallback(new ListAiringTvShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MoviesTVShows data) {
                showSelectedMovie(data);
            }
        });
        listPopularTvShowsAdapter.setOnItemClickCallback(new ListPopularTvShowsAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MoviesTVShows data) {
                showSelectedMovie(data);
            }
        });
        listTopTvShowsAdapter.setOnItemClickCallback(new ListTopTvShowsAdapter.OnItemClickCallback() {
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
            case R.id.tvSeeMorePopular:
                moveIntent.putExtra(SeeMoreActivity.EXTRA_CATEGORY,"listPopularTv");
                startActivity(moveIntent);
                break;
            case R.id.tvSeeMoreTop:
                moveIntent.putExtra(SeeMoreActivity.EXTRA_CATEGORY,"listTopTv");
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