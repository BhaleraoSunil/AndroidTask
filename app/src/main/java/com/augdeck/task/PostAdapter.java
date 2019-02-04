package com.augdeck.task;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.augdeck.task.pojos.Hit;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    List<Hit> hits;

    public PostAdapter(List<Hit> hitList) {
        this.hits = hitList;
    }

    @NonNull
    @Override
    public PostAdapter.PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new PostAdapter.PostHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostHolder holder, int position) {

        Hit obj =  hits.get(position);
        holder.postTitle.setText(obj.getTitle());
        holder.postCreatedAt.setText(obj.getCreatedAt());

    }

    @Override
    public int getItemCount() {
        return hits.size();
    }

    class PostHolder extends RecyclerView.ViewHolder {

        TextView postTitle;
        TextView postCreatedAt;
        public PostHolder(View itemView) {
            super(itemView);
            postTitle =  itemView.findViewById(R.id.text_title);
            postCreatedAt = itemView.findViewById(R.id.text_created);

        }
    }
}
