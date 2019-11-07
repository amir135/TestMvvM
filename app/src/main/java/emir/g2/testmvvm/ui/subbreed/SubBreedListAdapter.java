package emir.g2.testmvvm.ui.subbreed;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import emir.g2.testmvvm.R;

public class SubBreedListAdapter extends RecyclerView.Adapter<SubBreedListAdapter.RepoViewHolder>{

    private SubBreedSelectedListener repoSelectedListener;
    private final List<String> data = new ArrayList<>();

    SubBreedListAdapter(SubBreedViewModel viewModel, LifecycleOwner lifecycleOwner, SubBreedSelectedListener repoSelectedListener) {
        this.repoSelectedListener = repoSelectedListener;
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll( Arrays.asList(repos.getMessage()));
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public SubBreedListAdapter.RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_repo_list_item, parent, false);
        return new SubBreedListAdapter.RepoViewHolder(view, repoSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SubBreedListAdapter.RepoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_repo_name) TextView repoNameTextView;
        private String repo;

        RepoViewHolder(View itemView, SubBreedSelectedListener repoSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                repoSelectedListener.onRepoSelected(repo);
            });
        }

        void bind(String repo) {
            this.repo=repo;
            repoNameTextView.setText(repo);
        }
    }
}
