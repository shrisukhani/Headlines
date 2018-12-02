package csci5115team5.com.headlines.model.network;

import android.util.Log;

import com.facebook.litho.EventHandler;

import csci5115team5.com.headlines.model.NewsApiResult;
import csci5115team5.com.headlines.model.NewsApiResultEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsApiService {
    private static final String TAG = NewsApiService.class.getSimpleName();

    private final NewsApiRetrofit mNewsApiRetrofit;

    private EventHandler<NewsApiResultEvent> mEventHandler;

    NewsApiService(NewsApiRetrofit newsApiRetrofit) {
        mNewsApiRetrofit = newsApiRetrofit;
    }

    public void registerEventHandler(EventHandler<NewsApiResultEvent> eventHandler) {
        mEventHandler = eventHandler;
    }

    public void unregisterEventHandler() {
        mEventHandler = null;
    }

    public void getStoriesForQuery(String searchQuery) {
        handleCall(mNewsApiRetrofit.getStoriesForQuery(searchQuery));
    }

    public void getStoriesForQuery(String searchQuery, int pageNo) {
        handleCall(mNewsApiRetrofit.getStoriesForQuery(searchQuery, pageNo));
    }

    public void getStoriesForQueryFromDate(String searchQuery, String from) {
        handleCall(mNewsApiRetrofit.getStoriesForQueryFromDate(searchQuery, from));
    }

    public void getStoriesForQueryFromDate(
            String searchQuery, String from, int pageNo) {
        handleCall(mNewsApiRetrofit.getStoriesForQueryFromDate(searchQuery, from, pageNo));
    }

    public void getStoriesForQueryToDate(String searchQuery, String to) {
        handleCall(mNewsApiRetrofit.getStoriesForQueryToDate(searchQuery, to));
    }

    public void getStoriesForQueryToDate(
            String searchQuery, String to, int pageNo) {
        handleCall(mNewsApiRetrofit.getStoriesForQueryToDate(searchQuery, to, pageNo));
    }

    public void getStoriesForQueryBetweenDates(
            String searchQuery, String from, String to) {
        handleCall(mNewsApiRetrofit.getStoriesForQueryBetweenDates(searchQuery, from, to));
    }

    public void getStoriesForQueryBetweenDates(
            String searchQuery, String from, String to, int pageNo) {
        handleCall(mNewsApiRetrofit.getStoriesForQueryBetweenDates(searchQuery, from, to, pageNo));
    }

    private void handleCall(Call<NewsApiResult> resultCall) {
        resultCall.enqueue(new Callback<NewsApiResult>() {
            @Override
            public void onResponse(Call<NewsApiResult> call, Response<NewsApiResult> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Call was Successful");
                    mEventHandler.dispatchEvent(new NewsApiResultEvent(response.body()));
                } else {
                    Log.e(TAG, response.message());
                    mEventHandler.dispatchEvent(new NewsApiResultEvent(null));
                }
            }

            @Override
            public void onFailure(Call<NewsApiResult> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
