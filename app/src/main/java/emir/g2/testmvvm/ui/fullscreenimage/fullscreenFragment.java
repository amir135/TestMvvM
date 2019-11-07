package emir.g2.testmvvm.ui.fullscreenimage;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import emir.g2.testmvvm.R;
import emir.g2.testmvvm.base.BaseFragment;
import emir.g2.testmvvm.util.ViewModelFactory;

public class fullscreenFragment extends BaseFragment  {

    @BindView(R.id.singleImage2)
    ImageView imageView;
    @BindView(R.id.tv_error2) TextView errorTextView;
    @BindView(R.id.loading_view2) View loadingView;

    @Inject ViewModelFactory viewModelFactory;
    private fullscreenViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.dogimages_fullscreen;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(fullscreenViewModel.class);
         viewModel.getUrl();
        loadingView.setVisibility(View.GONE);
        Picasso.get().load(viewModel.getUrl())
                .into(imageView);
    }

    private void observableViewModel() {
        viewModel.getRepos().observe(this, repos -> {
            if(repos != null) imageView.setVisibility(View.VISIBLE);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if(isError) {
                errorTextView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
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
                    imageView.setVisibility(View.GONE);
                }
            }
        });
    }
}
