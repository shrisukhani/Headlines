package csci5115team5.com.headlines.model.network;

import csci5115team5.com.headlines.model.NewsApiResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("/v2/everything")
    public Call<NewsApiResult> getStoriesForQuery(@Query("q") String searchQuery);

    @GET("/v2/everything")
    public Call<NewsApiResult> getStoriesForQueryFromDate(@Query("from") String from);

    @GET("/v2/everything")
    public Call<NewsApiResult> getStoriesForQueryToDate(@Query("to") String to);

    @GET("/v2/everything")
    public Call<NewsApiResult> getStoriesForQueryBetweenDates(@Query("from") String from, @Query("to") String to);
}
