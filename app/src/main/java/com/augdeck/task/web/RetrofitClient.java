package com.augdeck.task.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by augmentdeck04 on 12/2/2017.
 */

class RetrofitClient {

    private static Retrofit retrofit = null;


    private static final Gson gson = new GsonBuilder()
                        .setLenient()
                        .registerTypeAdapter(int.class, new IntTypeAdapter())
                        .registerTypeAdapter(Integer.class, new IntTypeAdapter())
                        .create();


    public static Retrofit getClient(String baseUrl ) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getClient())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getClient() {


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(logging);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        return builder.build();
    }

    private static class IntTypeAdapter extends TypeAdapter<Number> {

        @Override
        public void write(JsonWriter out, Number value)
                throws IOException {
            out.value(value);
        }

        @Override
        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                String result = in.nextString();
                if ("".equals(result)) {
                    return null;
                }
                return Integer.parseInt(result);
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }
    }

}
