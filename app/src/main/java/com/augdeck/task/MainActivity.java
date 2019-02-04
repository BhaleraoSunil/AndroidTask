package com.augdeck.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.augdeck.task.pojos.Hit;
import com.augdeck.task.pojos.TaskPost;
import com.augdeck.task.web.APIService;
import com.augdeck.task.web.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    List<Hit> postList = new ArrayList<>();
    private PostAdapter adpater;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerview);







        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adpater = new PostAdapter(postList);
        recyclerView.setAdapter(adpater);
        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                CallPostsApi(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);

        CallPostsApi(1);
    }

    private void CallPostsApi(int page){

        Log.e("CallPostsApi","page ---> " + page);
        APIService service = ApiUtils.getAPIService();
        service.getPosts(page).enqueue(new Callback<TaskPost>() {
            @Override
            public void onResponse(Call<TaskPost> call, Response<TaskPost> response) {


                if(response.isSuccessful() ){
                    Log.e("CallPostsApi","isSuccessful");

                    TaskPost result = response.body();
                    updateUi(result);

                }
            }

            @Override
            public void onFailure(Call<TaskPost> call, Throwable t) {

            }
        });

    }

    private void updateUi(final TaskPost result) {


        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                postList.addAll(result.getHits());
                Log.e("CallPostsApi","list size = " + postList.size());
                adpater.notifyDataSetChanged();
                getSupportActionBar().setTitle(String.valueOf(postList.size()));
            }
        });
    }
}
