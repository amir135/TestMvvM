package emir.g2.testmvvm.ui.breed;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import emir.g2.testmvvm.R;
import emir.g2.testmvvm.base.BaseFragment;
import emir.g2.testmvvm.data.model.Breed;
import emir.g2.testmvvm.ui.dogimages.DogImageFragment;
import emir.g2.testmvvm.ui.dogimages.DogImageViewModel;
import emir.g2.testmvvm.ui.subbreed.SubBreedFragment;
import emir.g2.testmvvm.ui.subbreed.SubBreedViewModel;
import emir.g2.testmvvm.util.ViewModelFactory;


public class BreedFragment extends BaseFragment implements BreedSelectedListener {

    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.tv_error) TextView errorTextView;
    @BindView(R.id.loading_view) View loadingView;

    @Inject ViewModelFactory viewModelFactory;
    private BreedViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.screen_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BreedViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(new BreedListAdapter(viewModel, this, this));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        observableViewModel();
    }

    @Override
    public void onRepoSelected(Breed repo) {
        if(repo.getSubType()!=null && repo.getSubType().length>0) {
            SubBreedViewModel detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(SubBreedViewModel.class);
            detailsViewModel.setSelectedRepo(repo);
            getBaseActivity().getSupportFragmentManager().beginTransaction().replace(R.id.screenContainer, new SubBreedFragment())
                    .addToBackStack(null).commit();
        }
        else{
            DogImageViewModel detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DogImageViewModel.class);
            detailsViewModel.setSelectedRepo(null,repo);
            getBaseActivity().getSupportFragmentManager().beginTransaction().replace(R.id.screenContainer, new DogImageFragment())
                    .addToBackStack(null).commit();

        }
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
