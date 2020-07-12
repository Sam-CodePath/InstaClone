package com.example.instaclone.fragments;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instaclone.MainActivity;
import com.example.instaclone.Post;
import com.example.instaclone.PostView;
import com.example.instaclone.R;
import com.parse.ParseFile;

import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    public List<Post> posts;
    private RecyclerView rvPosts;



    public PostsAdapter(Context context, List<Post> posts, RecyclerView rvPosts) {
        this.context = context;
        this.posts = posts;
        this.rvPosts = rvPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int pos = rvPosts.getChildLayoutPosition(view);
                Post p = posts.get(pos);
                Date postDate = p.getCreatedAt();
                DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
                String formatDate = dateFormat.format(postDate);


                Intent i = new Intent(context, PostView.class);
                i.putExtra("author", p.getUser().getUsername());
                i.putExtra("caption", p.getDescription());
                i.putExtra("createdAt", formatDate);
                i.putExtra("position", Integer.toString(pos));
                i.putExtra("posturl", p.getImage().getUrl());
                i.putExtra("profileUrl", p.getUser().getParseFile("profile").getUrl());
                context.startActivity(i);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Post post = posts.get(position);
    holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private ImageView profileImage;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            profileImage = itemView.findViewById(R.id.profile_image);

        }

        public void bind(Post post) {
             // Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());

            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }

            ParseFile profile = post.getUser().getParseFile("profile");
            if (profile != null){
                Glide.with(context).load(profile.getUrl()).into(profileImage);
            }

        }
    }
}
