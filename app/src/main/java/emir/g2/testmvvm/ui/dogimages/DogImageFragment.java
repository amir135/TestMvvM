package emir.g2.testmvvm.ui.dogimages;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;

import emir.g2.testmvvm.R;
import emir.g2.testmvvm.base.BaseFragment;
import emir.g2.testmvvm.ui.fullscreenimage.fullscreenFragment;
import emir.g2.testmvvm.ui.fullscreenimage.fullscreenViewModel;
import emir.g2.testmvvm.util.ViewModelFactory;

public class DogImageFragment extends BaseFragment implements DogImageSelectedListener {

    @BindView(R.id.recyclerView2)
    RecyclerView listView;
    @BindView(R.id.tv_error2) TextView errorTextView;
    @BindView(R.id.loading_view2) View loadingView;

    @Inject ViewModelFactory viewModelFactory;
    private DogImageViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.dogimages_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DogImageViewModel.class);;
        viewModel.getSubBreeds();
        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(new DogImageListAdapter(viewModel, this, this));
        //listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        observableViewModel();
    }

    @Override
    public void onRepoSelected(String url) {
        fullscreenViewModel detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(fullscreenViewModel.class);
        detailsViewModel.setUrl(url);
        getBaseActivity().getSupportFragmentManager().beginTransaction().replace(R.id.screenContainer, new fullscreenFragment())
                .addToBackStack(null).commit();
    }

    private void observableViewModel() {
        viewModel.getRepos().observe(this, repos -> {
            if(repos != null) listView.setVisibility(View.VISIBLE);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if(isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText("An Error Occurred While Loading Data!");
            }else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }
            }
        });
    }
}
