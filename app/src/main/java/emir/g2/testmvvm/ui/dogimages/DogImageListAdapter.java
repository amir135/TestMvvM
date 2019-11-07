package emir.g2.testmvvm.ui.dogimages;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import emir.g2.testmvvm.R;

//import com.bumptech.glide.Glide;

public class DogImageListAdapter extends RecyclerView.Adapter<DogImageListAdapter.RepoViewHolder> {
    /*private DogImageSelectedListener repoSelectedListener;
    private final List<String> data = new ArrayList<>();
    public   View view;
    private  DogImageViewModel viewModel;
    public DogImageListAdapter(DogImageViewModel viewModel, LifecycleOwner lifecycleOwner, DogImageSelectedListener repoSelectedListener) {
        this.viewModel=viewModel;
        this.repoSelectedListener = repoSelectedListener;
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll( Arrays.asList(repos.getMessage()));
                notifyDataSetChanged();
            }
        });
        //setHasStableIds(true);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    static class ViewHolder {
        //@BindView(R.id.dogImage)
        ImageView repoNameTextView;

        ViewHolder(View v) {
            repoNameTextView=v.findViewById(R.id.dogImage);
           // ButterKnife.bind(this, v);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_dog_images_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);;
        Picasso.get()
                .load(data.get(i))
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .into(viewHolder.repoNameTextView);
        //viewHolder.text.setText("my text");
        //return new DogImageListAdapter.RepoViewHolder(view, repoSelectedListener);
        return view;
    }*/

    private DogImageSelectedListener repoSelectedListener;
    private final List<String> data = new ArrayList<>();
    public   View view;
    DogImageListAdapter(DogImageViewModel viewModel, LifecycleOwner lifecycleOwner, DogImageSelectedListener repoSelectedListener) {
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
    public DogImageListAdapter.RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dog_images_item, parent, false);
        return new DogImageListAdapter.RepoViewHolder(view, repoSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DogImageListAdapter.RepoViewHolder holder, int position) {
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
        @BindView(R.id.dogImage)
        ImageView repoNameTextView;
        private String url;

        RepoViewHolder(View itemView, DogImageSelectedListener repoSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                repoSelectedListener.onRepoSelected(url);
            });
        }

        void bind(String url) {
            this.url=url;
            Picasso.get().load(url)
                    .into(repoNameTextView);
        }
    }
}
