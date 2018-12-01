package csci5115team5.com.headlines.model.network;

import csci5115team5.com.headlines.model.NewsApiResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("/v2/everything")
    public Call<NewsApiResult> getStoriesForQuery(@Query("q") String searchQuery);

    @GET("/v2/everything")
    public Call<NewsApiResult> getStoriesForQuery(
            @Query("q") String searchQuery, int pageNo);

    @GET("/v2/everything")
    public Call<NewsApiResult> getStoriesForQueryFromDate(
            @Query("q") String searchQuery, @Query("from") String from);

    @GET("/v2/everything")
    public Call<NewsApiResult> getStoriesForQueryFromDate(
            @Query("q") String searchQuery,
            @Query("from") String from,
            @Query("page") int pageNo);

    @GET("/v2/everything")
    public Call<NewsApiResult> getStoriesForQueryToDate(
            @Query("q") String searchQuery, @Query("to") String to);

    @GET("/v2/everything")
    public Call<NewsApiResult> getStoriesForQueryToDate(
            @Query("q") String searchQuery,
            @Query("to") String to,
            @Query("page") int pageNo);

    @GET("/v2/everything")
    public Call<NewsApiResult> getStoriesForQueryBetweenDates(
            @Query("q") String searchQuery,
            @Query("from") String from,
            @Query("to") String to);

    @GET("/v2/everything")
    public Call<NewsApiResult> getStoriesForQueryBetweenDates(
            @Query("q") String searchQuery,
            @Query("from") String from,
            @Query("to") String to,
            @Query("page") int pageNo);
}
