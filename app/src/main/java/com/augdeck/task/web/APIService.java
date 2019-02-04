package com.augdeck.task.web;


import com.augdeck.task.pojos.Hit;
import com.augdeck.task.pojos.TaskPost;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @GET("search_by_date?tags=story")
    Call<TaskPost> getPosts(@Query("page") int pageNo);


}
