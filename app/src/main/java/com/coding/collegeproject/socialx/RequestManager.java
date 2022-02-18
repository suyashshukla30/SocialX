package com.coding.collegeproject.socialx;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.coding.collegeproject.socialx.models.newsapiresponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit=new Retrofit.Builder().baseUrl("https://newsapi.org/v2/").
            addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getNewsHeadlines(OnFetchDataListener listener, String category,String query)
    {
        CallNewsApi callNewsApi=retrofit.create(CallNewsApi.class);
        Call<newsapiresponse> call=callNewsApi.callHeadline("in",category,query,context.getString(R.string.api_key));
        try
        {
            call.enqueue(new Callback<newsapiresponse>() {
                @Override
                public void onResponse(@NonNull Call<newsapiresponse> call, @NonNull Response<newsapiresponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
                    }
                    assert response.body() != null;
                    listener.onFetchData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(@NonNull Call<newsapiresponse> call, @NonNull Throwable t) {
                    listener.onError("Request failed");
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public RequestManager(Context context) {
        this.context = context;
    }
    public interface CallNewsApi{
        @GET("top-headlines")
        Call<newsapiresponse> callHeadline(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String api_key
        );
    }
}
