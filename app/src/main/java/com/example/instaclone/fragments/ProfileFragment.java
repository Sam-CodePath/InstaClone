package com.example.instaclone.fragments;

import android.util.Log;

import com.example.instaclone.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;
import java.util.List;

public class ProfileFragment extends PostsFragment {
    @Override
    protected void queryPosts(Date mostRecent) {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        if (mostRecent != null) {
            query.whereLessThan("createdAt", mostRecent);
            Log.i("PG", "Only getting older posts!");
        } else {
            Log.i("PG", "Resetting");
        }

        final boolean shouldInsert = mostRecent != null;

        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                }

                for (Post post : posts){
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }

                if (!shouldInsert) {
                    adapter.clear();
                    adapter.addAll(posts);
//                allPosts.addAll(posts);
//                adapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);
                } else {
                    adapter.addAll(posts);
                }
            }
        });
    }

}
