package com.example.myapplication.Api.clients;
import com.example.myapplication.caches.RetrofitCacheing;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    public static final String BASE_URL = "https://api.themoviedb.org";
    public static final String key="32c913288edad9470662db02b7263518";
    public static String LANGUAGE = "en-US";
    public static RetrofitCacheing retrofitCacheing;
    private static ApiService instance;


    public ApiService() {
        retrofitCacheing=new RetrofitCacheing();
    }
    public static ApiService GetInstance(){
        if(instance == null){
            instance = new ApiService();
        }
        return instance;
    }

    private static Retrofit retrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(retrofitCacheing.okHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static WebServices getAPI() {
            return retrofit().create(WebServices.class);
    }
}
