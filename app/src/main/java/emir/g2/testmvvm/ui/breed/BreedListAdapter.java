package emir.g2.testmvvm.ui.breed;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import emir.g2.testmvvm.R;
import emir.g2.testmvvm.data.model.Breed;

public class BreedListAdapter extends RecyclerView.Adapter<BreedListAdapter.RepoViewHolder>{

    private BreedSelectedListener repoSelectedListener;
    private final List<Breed> data = new ArrayList<>();

    BreedListAdapter(BreedViewModel viewModel, LifecycleOwner lifecycleOwner, BreedSelectedListener repoSelectedListener) {
        this.repoSelectedListener = repoSelectedListener;
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos.getMessage());
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_repo_list_item, parent, false);
        return new RepoViewHolder(view, repoSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
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
//        @BindView(R.id.tv_repo_description) TextView repoDescriptionTextView;
//        @BindView(R.id.tv_forks) TextView forksTextView;
//        @BindView(R.id.tv_stars) TextView starsTextView;

        private Breed repo;

        RepoViewHolder(View itemView, BreedSelectedListener repoSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(repo != null) {
                    repoSelectedListener.onRepoSelected(repo);
                }
            });
        }

        void bind(Breed repo) {
            this.repo = repo;
            repoNameTextView.setText(repo.getType());
            //repoDescriptionTextView.setText(repo.getSubType().length);
            //forksTextView.setText(String.valueOf(repo.forks));
            //starsTextView.setText(String.valueOf(repo.stars));
        }
    }
}
