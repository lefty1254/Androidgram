package com.example.androidgram;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseFile;

import java.io.File;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private final String TAG = "PostsAdapter";

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Post post = posts.get(i);


        viewHolder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }
    public void addPosts(List<Post> postList) {
        posts.addAll(postList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivPostImage;
        public TextView tvPostUsername;
        public TextView tvPostDescription;


        public ViewHolder(View itemView) {
            super(itemView);
            ivPostImage = itemView.findViewById(R.id.ivPostTimeImage);
            tvPostUsername = itemView.findViewById(R.id.tvPostUsername);
            tvPostDescription = itemView.findViewById(R.id.tvPostDescription);
        }

        public void bind(Post post) {
            tvPostDescription.setText(post.getDescription());
            tvPostUsername.setText(post.getUser().getUsername());

            ParseFile image = post.getImage();

            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivPostImage);
            }
        }
    }

}
