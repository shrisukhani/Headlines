package csci5115team5.com.headlines.model.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApiServiceFactory {
    private NewsApiServiceFactory() {}

    private static final String NEWS_API_KEY = "e11407b4da2d4f7d80466e0f2dc859b1";
    private static final String SOURCE_IDS = "associated-press,bbc-news,buzzfeed," +
            "cnbc,cnn,daily-mail,business-insider,financial-post,fox-news,hacker-news,recode" +
            ",reuters,techcrunch,the-new-york-times,the-wall-street-journal,usa-today,wired," +
            "the-washington-post,nbc-news,time";

    private static OkHttpClient sNewsApiClient;
    private static NewsApiRetrofit sNewsApi;
    private static NewsApiService sNewsApiService;

    public static NewsApiService getNewsApiService() {
        if (sNewsApiService == null) {
            sNewsApiService = new NewsApiService(getNewsApiRetrofit());
        }
        return sNewsApiService;
    }

    private static OkHttpClient getClientForNewsApiService() {
        if (sNewsApiClient == null) {
            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    HttpUrl modifiedURL = original.url().newBuilder()
                            .addQueryParameter("sources", SOURCE_IDS)
                            .addQueryParameter("language", "en")
                            .build();

                    Request modifiedRequest = original.newBuilder()
                            .url(modifiedURL)
                            .addHeader("X-Api-Key", NEWS_API_KEY)
                            .build();

                    return chain.proceed(modifiedRequest);
                }
            };
            sNewsApiClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
        }
        return sNewsApiClient;
    }

    private static NewsApiRetrofit getNewsApiRetrofit() {
        if (sNewsApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClientForNewsApiService())
                    .build();
            sNewsApi = retrofit.create(NewsApiRetrofit.class);
        }
        return sNewsApi;
    }

}
