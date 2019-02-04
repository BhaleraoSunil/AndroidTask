package com.augdeck.task.web;

/**
 * Created by augmentdeck04 on 12/2/2017.
 */

public class ApiUtils {

    private ApiUtils() {}

    //https://hn.algolia.com/api/v1/search_by_date?tags=story&page=1

    private static final String BASE_URL = "https://hn.algolia.com/api/v1/";



    public static APIService getAPIService( ) {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
