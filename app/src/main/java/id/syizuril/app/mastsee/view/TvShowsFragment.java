package id.syizuril.app.mastsee.view;


import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.adapters.ListAiringTvShowsAdapter;
import id.syizuril.app.mastsee.adapters.ListPopularTvShowsAdapter;
import id.syizuril.app.mastsee.adapters.ListTopTvShowsAdapter;
import id.syizuril.app.mastsee.models.TvShowsResult;
import id.syizuril.app.mastsee.viewmodels.ListAiringTvShowsViewModel;
import id.syizuril.app.mastsee.viewmodels.ListPopularTvShowsViewModel;
import id.syizuril.app.mastsee.viewmodels.ListTopTvShowsViewModel;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class TvShowsFragment extends Fragment implements View.OnClickListener{
    private RecyclerView rvAiringTvShow, rvPopularTvShow, rvTopTvShow;
    private ArrayList<TvShowsResult> popularTvShowsList = new ArrayList<>();
    private ArrayList<TvShowsResult> topTvShowsList = new ArrayList<>();
    private ArrayList<TvShowsResult> airingTvShowsList = new ArrayList<>();
    private ListPopularTvShowsAdapter mPopularTvShows;
    private ListTopTvShowsAdapter mTopTvShows;
    private ListAiringTvShowsAdapter mAiringTvShows;
    private ProgressBar mProgressBar;
    private ImageView mConnectionError;
    private TextView tvPopularTitle, tvTopTitle, tvConnectionError, tvSeeMorePopular, tvSeeMoreTop;

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
        mProgressBar = view.findViewById(R.id.progressBarTopTvShows);
        tvPopularTitle = view.findViewById(R.id.tvPopularTV);
        tvTopTitle = view.findViewById(R.id.tvTopTV);
        mConnectionError = view.findViewById(R.id.ivConnectionError);
        tvConnectionError = view.findViewById(R.id.tvConnectionError);
        tvSeeMorePopular = view.findViewById(R.id.tvSeeMorePopular);
        tvSeeMoreTop = view.findViewById(R.id.tvSeeMoreTop);

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
            assert airingTvShowResponse != null;
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
            assert topTvShowResponse != null;
            List<TvShowsResult> topTvShowsResults = topTvShowResponse.getResults();
            topTvShowsList.addAll(topTvShowsResults);
            mTopTvShows.notifyDataSetChanged();
            hideProgressBar();
        });
        mListPopularTvShowsViewModel.getIsConnected().observe(this, this::onChanged);
        showRecyclerList();
        setHasOptionsMenu(true);
    }

    private void showRecyclerList(){
        if(mPopularTvShows == null){
            mPopularTvShows = new ListPopularTvShowsAdapter(popularTvShowsList);
            rvPopularTvShow.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL,false));
            rvPopularTvShow.setAdapter(mPopularTvShows);
        }else {
            mPopularTvShows.notifyDataSetChanged();
        }
        if(mTopTvShows == null){
            mTopTvShows = new ListTopTvShowsAdapter(topTvShowsList);
            rvTopTvShow.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL,false));
            rvTopTvShow.setAdapter(mTopTvShows);
        }else{
            mTopTvShows.notifyDataSetChanged();
        }
        if(mAiringTvShows == null){
            mAiringTvShows = new ListAiringTvShowsAdapter(airingTvShowsList);
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
        tvConnectionError.setVisibility(View.GONE);
        tvTopTitle.setVisibility(View.GONE);
        tvPopularTitle.setVisibility(View.GONE);
        tvSeeMorePopular.setVisibility(View.GONE);
        tvSeeMoreTop.setVisibility(View.GONE);

    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
        tvTopTitle.setVisibility(View.VISIBLE);
        tvPopularTitle.setVisibility(View.VISIBLE);
        tvSeeMorePopular.setVisibility(View.VISIBLE);
        tvSeeMoreTop.setVisibility(View.VISIBLE);
    }

    private void onChanged(Boolean aBoolean) {
        if (aBoolean) {
            mConnectionError.setVisibility(View.GONE);
            tvConnectionError.setVisibility(View.GONE);
        } else {
            hideProgressBar();
            mConnectionError.setVisibility(View.VISIBLE);
            tvConnectionError.setVisibility(View.VISIBLE);
            tvTopTitle.setVisibility(View.GONE);
            tvPopularTitle.setVisibility(View.GONE);
            tvSeeMorePopular.setVisibility(View.GONE);
            tvSeeMoreTop.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_bar, menu);
        super.onCreateOptionsMenu(menu,inflater);

        SearchManager searchManager = (SearchManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint_tv));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Intent moveIntent = new Intent(getContext(), SeeMoreActivity.class);
                    moveIntent.putExtra(SeeMoreActivity.EXTRA_SEARCH,query);
                    moveIntent.putExtra(SeeMoreActivity.EXTRA_CATEGORY,"searchTv");
                    startActivity(moveIntent);
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
    }
}