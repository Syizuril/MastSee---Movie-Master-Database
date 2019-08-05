package id.syizuril.app.mastsee.view;


import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.adapters.ListAiringTvShowsAdapter;
import id.syizuril.app.mastsee.adapters.ListPopularTvShowsAdapter;
import id.syizuril.app.mastsee.adapters.ListTopTvShowsAdapter;
import id.syizuril.app.mastsee.models.TvShowsResult;
import id.syizuril.app.mastsee.viewmodels.ListAiringTvShowsViewModel;
import id.syizuril.app.mastsee.viewmodels.ListPopularTvShowsViewModel;
import id.syizuril.app.mastsee.viewmodels.ListTopTvShowsViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment implements View.OnClickListener{
    private RecyclerView rvAiringTvShow, rvPopularTvShow, rvTopTvShow;
    private ArrayList<TvShowsResult> popularTvShowsList = new ArrayList<>();
    private ArrayList<TvShowsResult> topTvShowsList = new ArrayList<>();
    private ArrayList<TvShowsResult> airingTvShowsList = new ArrayList<>();
    private ListPopularTvShowsAdapter mPopularTvShows;
    private ListTopTvShowsAdapter mTopTvShows;
    private ListAiringTvShowsAdapter mAiringTvShows;
    private ProgressBar mProgressBar, mProgressBar2;

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
        mProgressBar = view.findViewById(R.id.progressBarPopularTvShows);
        mProgressBar2 = view.findViewById(R.id.progressBarTopTvShows);
        TextView tvSeeMorePopular = view.findViewById(R.id.tvSeeMorePopular);
        TextView tvSeeMoreTop = view.findViewById(R.id.tvSeeMoreTop);

        rvPopularTvShow.setHasFixedSize(true);
        rvAiringTvShow.setHasFixedSize(true);
        rvTopTvShow.setHasFixedSize(true);

        tvSeeMorePopular.setOnClickListener(this);
        tvSeeMoreTop.setOnClickListener(this);

        showProgressBar();

        ListPopularTvShowsViewModel mListPopularTvShowsViewModel = ViewModelProviders.of(this).get(ListPopularTvShowsViewModel.class);
        ListTopTvShowsViewModel mListTopTvShowsViewModel = ViewModelProviders.of(this).get(ListTopTvShowsViewModel.class);
        ListAiringTvShowsViewModel mListAiringTvShowsViewModel = ViewModelProviders.of(this).get(ListAiringTvShowsViewModel.class);
        mListAiringTvShowsViewModel.init();
        mListPopularTvShowsViewModel.init();
        mListTopTvShowsViewModel.init();
        mListAiringTvShowsViewModel.getTvShowsResultList().observe(this, airingTvShowResponse ->{
            List<TvShowsResult> airingTvShowResult = airingTvShowResponse.getResults();
            airingTvShowsList.addAll(airingTvShowResult);
            mAiringTvShows.notifyDataSetChanged();
        });
        mListPopularTvShowsViewModel.getTvShowsResultList().observe(this, popularTvShowResponse -> {
            assert popularTvShowResponse != null;
            List<TvShowsResult> popularTvShowResults = popularTvShowResponse.getResults();
            popularTvShowsList.addAll(popularTvShowResults);
            mPopularTvShows.notifyDataSetChanged();
        });
        mListTopTvShowsViewModel.getTvShowsResultList().observe(this, topTvShowResponse -> {
            List<TvShowsResult> topTvShowsResults = topTvShowResponse.getResults();
            topTvShowsList.addAll(topTvShowsResults);
            mTopTvShows.notifyDataSetChanged();
            hideProgressBar();
        });

        showRecyclerList();
    }

    private void showRecyclerList(){
        if(mPopularTvShows == null){
            mPopularTvShows = new ListPopularTvShowsAdapter(this.getActivity(), popularTvShowsList);
            rvPopularTvShow.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL,false));
            rvPopularTvShow.setAdapter(mPopularTvShows);
        }else {
            mPopularTvShows.notifyDataSetChanged();
        }
        if(mTopTvShows == null){
            mTopTvShows = new ListTopTvShowsAdapter(this.getActivity(), topTvShowsList);
            rvTopTvShow.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL,false));
            rvTopTvShow.setAdapter(mTopTvShows);
        }else{
            mTopTvShows.notifyDataSetChanged();
        }
        if(mAiringTvShows == null){
            mAiringTvShows = new ListAiringTvShowsAdapter(this.getActivity(), airingTvShowsList);
            rvAiringTvShow.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false));
            rvAiringTvShow.setAdapter(mAiringTvShows);
        }else{
            mAiringTvShows.notifyDataSetChanged();
        }
        mAiringTvShows.setOnItemClickCallback(this::showSelectedMovie);
        mPopularTvShows.setOnItemClickCallback(this::showSelectedMovie);
        mTopTvShows.setOnItemClickCallback(this::showSelectedMovie);
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

    private void showSelectedMovie(TvShowsResult TvShowsResult){
        Intent sendMovieTV = new Intent(this.getActivity(), DetailActivityTv.class);
        sendMovieTV.putExtra(DetailActivityTv.EXTRA_MOVIE, TvShowsResult);
        startActivity(sendMovieTV);
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar2.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
        mProgressBar2.setVisibility(View.GONE);
    }
}