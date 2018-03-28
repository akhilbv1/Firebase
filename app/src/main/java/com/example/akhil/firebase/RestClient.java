package com.example.akhil.firebase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
  Created by akhil on 28/3/18.
 */

public class RestClient {

    private static String BaseUrl_Login = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/";

    private static String BaseUrl_getEvents = "https://sampledatabase-5cb82.firebaseio.com/";

    private Retrofit retrofit = null;

    private final RestApis loginService;

    private final RestApis eventsService;



    public RestClient(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        httpClient.addInterceptor(logging);

       Retrofit retrofitLogin = new Retrofit.Builder()
                   .baseUrl(BaseUrl_Login)
                   .addConverterFactory(GsonConverterFactory.create())
                   .client(httpClient.build())
                   .build();

       Retrofit retrofitgetEvents = new Retrofit.Builder()
                                    .baseUrl(BaseUrl_getEvents)
                                    .client(httpClient.build())

                                     .addConverterFactory(createGsonConverter())
                                     .build();

       loginService = retrofitLogin.create(RestApis.class);

       eventsService = retrofitgetEvents.create(RestApis.class);

    }

    public RestApis getLoginService(){
        return loginService;
    }

    public RestApis getEventsService(){
        return  eventsService;
    }
    private Converter.Factory createGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(EventKeyModel.class, new EventDetailsDeserializer());
        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }
}
